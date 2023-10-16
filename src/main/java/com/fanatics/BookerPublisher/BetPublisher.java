package com.fanatics.BookerPublisher;

import com.fanatics.model.BookerBet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
@Component
public class BetPublisher {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    public RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BetPublisher.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String postUrl ="http://localhost:8080/booker_bets";

    private static final Random randomizer = new Random();

    RestTemplate restTemplate = new RestTemplate();



    List<String> bookers =  List.of("VegasPlay", "NYC", "BetMax", "RenoBetsOnline");
    List<Long> gameIds =  List.of(1L,2L,3L);
    @Scheduled(fixedRate = 5000)
    public void generateNewOdds() throws JsonProcessingException {
        String booker = bookers.get(randomizer.nextInt(bookers.size()));
        Long game = gameIds.get(randomizer.nextInt(gameIds.size()));
        Long effectiveDate = System.currentTimeMillis();
        BookerBet bookerBet = new BookerBet(booker, game, effectiveDate,randomizer.nextInt(50), randomizer.nextInt(50));
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        circuitBreaker.run(()-> upsertOdds(bookerBet), throwable -> upsertOddsFallback(bookerBet));

    }
    public Boolean upsertOdds(BookerBet bookerBet)  {
        rabbitTemplate.convertAndSend("bookerBetsQueue",  bookerBet);
        try {
            logger.info("updated bet odds sent via AMQP : " + mapper.writeValueAsString(bookerBet));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public Boolean upsertOddsFallback(BookerBet bookerBet)  {
        logger.info("AMQP exchange failed using REST service");
        ResponseEntity<BookerBet> bookerBetResponse =  restTemplate.postForEntity(postUrl, bookerBet, BookerBet.class);
        try {
            logger.info("updated bet odds sent via REST: " + mapper.writeValueAsString(bookerBet));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}

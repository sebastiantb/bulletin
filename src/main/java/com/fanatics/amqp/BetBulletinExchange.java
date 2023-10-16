package com.fanatics.amqp;

import com.fanatics.model.BookerBet;
import com.fanatics.service.BetBulletinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BetBulletinExchange {

    @Autowired
    private BetBulletinService betBulletinService;

    private static final Logger logger = LoggerFactory.getLogger(BetBulletinExchange.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    @RabbitListener(queues = {"bookerBetsQueue"})
    public void onUserRegistration(BookerBet bookerBet) throws JsonProcessingException {
        logger .info("updated bet odds received via AMQP: {}", mapper.writeValueAsString(bookerBet));
        betBulletinService.saveBookerBet(bookerBet);
    }
}

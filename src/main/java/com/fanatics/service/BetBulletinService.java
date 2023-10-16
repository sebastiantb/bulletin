package com.fanatics.service;

import com.fanatics.model.BookerBet;
import com.fanatics.model.Game;
import com.fanatics.persistence.BookerBetsRepository;
import com.fanatics.persistence.GameRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class BetBulletinService {


    @Autowired
    private Validator validator;

    @Autowired
    public BookerBetsRepository bookerBetsRepository;

    @Autowired
    public GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<BookerBet> getAvailableBookerBets() {
        return bookerBetsRepository.findAll();
    }

    public List<BookerBet> getBetsByBookerName(String bookerName) {
        return bookerBetsRepository.findByBookerName(bookerName);
    }

    public BookerBet saveBookerBet(BookerBet bookerBet){
        Set<ConstraintViolation<BookerBet>> violations = validator.validate(bookerBet);
        if(!violations.isEmpty()){
            throw new IllegalArgumentException("Invalid Booker Bet: " + violations);
        }
        if (!gameRepository.existsById(bookerBet.getGameId())){
            throw new IllegalArgumentException("Game does not exist: " + bookerBet.getGameId());
        }
       return bookerBetsRepository.save(bookerBet);
    }

    @PostConstruct
    public void preSeedData(){
        gameRepository.save(new Game(1L,"Panthers","Bears"));
        gameRepository.save(new Game(2L,"Spartans","Pirates"));
        gameRepository.save(new Game(3L,"Switzerland","Germany"));

    }
}

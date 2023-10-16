package com.fanatics.service;

import com.fanatics.model.BookerBet;
import com.fanatics.persistence.BookerBetsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BetBulletinServiceTest {
    @Autowired
    BetBulletinService betBulletinService;
    @Test
    void saveBookerBet_good() {
        BookerBet bookerBet = new BookerBet("booker", 1l, System.currentTimeMillis(), 34, 54);
        betBulletinService.saveBookerBet(bookerBet);
    }

    @Test
    void saveBookerBet_bad_game() {
        BookerBet bookerBet = new BookerBet();
        Exception e =  assertThrows(IllegalArgumentException.class, ()-> betBulletinService.saveBookerBet(bookerBet));
        assertTrue(e.getMessage().contains("Invalid Booker Bet"));
    }
    @Test
    void saveBookerBet_bad_payload() {
        BookerBet bookerBet = new BookerBet("booker", 7l, System.currentTimeMillis(), 34, 54);
        Exception e =  assertThrows(IllegalArgumentException.class, ()-> betBulletinService.saveBookerBet(bookerBet));
        assertTrue(e.getMessage().contains("Game does not exist"));
    }
}
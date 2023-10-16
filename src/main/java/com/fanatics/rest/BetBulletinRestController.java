package com.fanatics.rest;

import com.fanatics.model.BookerBet;
import com.fanatics.model.Game;
import com.fanatics.service.BetBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BetBulletinRestController {


    @Autowired
    BetBulletinService betBulletinService;

    @GetMapping("/games")
    public List<Game> getGames() {
        return betBulletinService.getAllGames();
    }

    @GetMapping("/booker_bets")
    public List<BookerBet> getBookersBet() {
        return betBulletinService.getAvailableBookerBets();
    }

    @GetMapping("/booker_bets/{bookerName}")
    public List<BookerBet> getStudentsRest(@PathVariable String bookerName) {
        return betBulletinService.getBetsByBookerName(bookerName);
    }

    @PostMapping("/booker_bets")
    public BookerBet upsertBookerBet(@RequestBody BookerBet bookerBet) {
                return betBulletinService.saveBookerBet(bookerBet);
    }
}

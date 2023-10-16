package com.fanatics.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@IdClass(BookerBetID.class)
@Table(name = "booker_bets")
public class BookerBet implements Serializable {
    @Id
    @NotEmpty
    @Column(nullable = false)
    private String bookerName;
    @Id
    @NotNull
    @Column(nullable = false)
    private Long gameId;
    @NotNull
    @Column(nullable = false)
    private Long effectiveDate;
    @NotNull
    @Column(nullable = false)
    private Integer homeTeamOdds;
    @NotNull
    @Column(nullable = false)
    private Integer AwayTeamOdds;

    public BookerBet() {
    }

    public BookerBet(String bookerName, Long gameId, Long effectiveDate, Integer homeTeamOdds, Integer awayTeamOdds) {
        this.bookerName = bookerName;
        this.gameId = gameId;
        this.effectiveDate = effectiveDate;
        this.homeTeamOdds = homeTeamOdds;
        AwayTeamOdds = awayTeamOdds;
    }

    public String getBookerName() {
        return bookerName;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Long effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getHomeTeamOdds() {
        return homeTeamOdds;
    }

    public void setHomeTeamOdds(Integer homeTeamOdds) {
        this.homeTeamOdds = homeTeamOdds;
    }

    public Integer getAwayTeamOdds() {
        return AwayTeamOdds;
    }

    public void setAwayTeamOdds(Integer awayTeamOdds) {
        AwayTeamOdds = awayTeamOdds;
    }
}

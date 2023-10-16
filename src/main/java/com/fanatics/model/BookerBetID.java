package com.fanatics.model;


import java.io.Serializable;

public class BookerBetID implements Serializable {

    private String bookerName;

    private Long gameId;

    public BookerBetID() {
    }

    public BookerBetID(String bookerName, Long gameId) {
        this.bookerName = bookerName;
        this.gameId = gameId;
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
}

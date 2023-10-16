package com.fanatics.persistence;

import com.fanatics.model.BookerBet;
import com.fanatics.model.BookerBetID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookerBetsRepository extends JpaRepository<BookerBet, BookerBetID> {
    @Query("SELECT bb FROM BookerBet bb WHERE bb.bookerName = ?1")
    List<BookerBet> findByBookerName(String bookerName);

}

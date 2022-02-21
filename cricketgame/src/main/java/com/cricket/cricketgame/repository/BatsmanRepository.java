package com.cricket.cricketgame.repository;

import com.cricket.cricketgame.entity.Batsman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatsmanRepository extends JpaRepository<Batsman,Long> {
    public Batsman findByBatsmanName(String batsmanName);
    public Batsman findByBatsmanNameIgnoreCase(String batsmanName);
}

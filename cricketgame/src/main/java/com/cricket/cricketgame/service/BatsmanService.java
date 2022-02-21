package com.cricket.cricketgame.service;

import com.cricket.cricketgame.entity.Batsman;

import java.util.List;

public interface BatsmanService {


    public List<Batsman> fetchBatsman();

    public Batsman fetchBatsmanById(Long batsmanId);

    public Batsman fetchBatsmanbyName(String batsmanName);

}

package com.cricket.cricketgame.service;

import com.cricket.cricketgame.entity.Batsman;
import com.cricket.cricketgame.repository.BatsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatsmanServiceImpl implements BatsmanService{
    @Autowired
    private BatsmanRepository batsmanRepository;

    @Override
    public List<Batsman> fetchBatsman() {
        return batsmanRepository.findAll();
    }

    @Override
    public Batsman fetchBatsmanById(Long batsmanId) {
        return batsmanRepository.findById(batsmanId).get();
    }

    @Override
    public Batsman fetchBatsmanbyName(String batsmanName) {
        return batsmanRepository.findByBatsmanNameIgnoreCase(batsmanName);
    }

}

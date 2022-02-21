package com.cricket.cricketgame.service;

import com.cricket.cricketgame.entity.Baller;
import com.cricket.cricketgame.repository.BallerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BallerServiceImpl implements BallerService {
    @Autowired
    private BallerRepository ballerRepository;
    @Override
    public List<Baller> fetchBatsman() {
        return ballerRepository.findAll();
    }

    @Override
    public Baller fetchBallerById(Long ballerId) {
        return ballerRepository.findById(ballerId).get();
    }

    @Override
    public Baller fetchBallerbyName(String ballerName) {
        return ballerRepository.findByBallerNameIgnoreCase(ballerName);
    }
}

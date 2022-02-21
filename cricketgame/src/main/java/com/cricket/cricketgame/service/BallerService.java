package com.cricket.cricketgame.service;

import com.cricket.cricketgame.entity.Baller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BallerService {
    public List<Baller> fetchBatsman();

    public Baller fetchBallerById(Long ballerId);

    public Baller fetchBallerbyName(String ballerName);
}

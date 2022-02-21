package com.cricket.cricketgame.controller;

import com.cricket.cricketgame.entity.Batsman;
import com.cricket.cricketgame.service.BatsmanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BatsmanController {

    private BatsmanService batsmanService;
    @Autowired
    public BatsmanController(BatsmanService batsmanService) {
        this.batsmanService = batsmanService;
    }

    @GetMapping("/batsman")
    public List<Batsman> fetchBatsman() {
        return batsmanService.fetchBatsman();
    }

    @GetMapping("/batsman/{id}")
    public Batsman fetchBatsmanById(@PathVariable("id") Long batsmanId) {
        return batsmanService.fetchBatsmanById(batsmanId);
    }

    @GetMapping("/batsman/name/{name}")
    public Batsman fetchBatsmanByName(@PathVariable("name") String batsmanName) {
        return batsmanService.fetchBatsmanbyName(batsmanName);
    }
}

package com.cricket.cricketgame.controller;

import com.cricket.cricketgame.entity.Baller;
import com.cricket.cricketgame.service.BallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BallerController {
    @Autowired
    private BallerService ballerService;

    @GetMapping("/baller")
    public List<Baller> fetchBaller() {
        return ballerService.fetchBatsman();
    }

    @GetMapping("/baller/{id}")
    public Baller fetchBBallerById(@PathVariable("id") Long ballerId) {
        return ballerService.fetchBallerById(ballerId);
    }

    @GetMapping("/baller/name/{name}")
    public Baller fetchBallerByName(@PathVariable("name") String ballerName) {
        return ballerService.fetchBallerbyName(ballerName);
    }

}

package com.cricket.cricketgame.controller;

import com.cricket.cricketgame.entity.MatchDetails;
import com.cricket.cricketgame.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {
    @Autowired
    private MatchService matchService;

    @PostMapping("/match")
    public MatchDetails saveMatchInformation(@RequestBody MatchDetails matchDetails) {
        return matchService.saveMatchInformation(matchDetails);
    }

    @PostMapping("/startMatch")
    public void startMatch() {
        matchService.startMatch();
    }
}

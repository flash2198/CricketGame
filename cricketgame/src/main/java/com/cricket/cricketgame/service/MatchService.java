package com.cricket.cricketgame.service;

import com.cricket.cricketgame.entity.MatchDetails;

public interface MatchService {


    public void startMatch();

    public MatchDetails saveMatchInformation(MatchDetails matchDetails);
}

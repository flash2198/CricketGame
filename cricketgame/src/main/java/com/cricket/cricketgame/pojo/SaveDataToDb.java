package com.cricket.cricketgame.pojo;

import com.cricket.cricketgame.entity.Baller;
import com.cricket.cricketgame.entity.Batsman;
import com.cricket.cricketgame.repository.BallerRepository;
import com.cricket.cricketgame.repository.BatsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class SaveDataToDb {
    @Autowired
    private BatsmanRepository batsmanRepository;
    @Autowired
    private BallerRepository ballerRepository;

    public void setBatsmanDetailsToDb(Map<Integer,String> idToPlayer,Map<Integer,BattingInformation> idToInformation){
        for(int id:idToPlayer.keySet()){
            Batsman batsman=batsmanRepository.findByBatsmanNameIgnoreCase(idToPlayer.get(id));
            batsman.setBallsPlayed(idToInformation.get(id).getBallsPlayed());
            batsman.setRunsScored(idToInformation.get(id).getRunsScored());
            batsman.setStrikeRate(idToInformation.get(id).getStrikeRate());
            batsmanRepository.save(batsman);
        }
    }
    public void setBallerDetailsToDb(Map<Integer,String> idToPlayer,Map<Integer,BallingInformation> idToInformation){
        for(int id:idToPlayer.keySet()){
            Baller baller=ballerRepository.findByBallerNameIgnoreCase(idToPlayer.get(id));
            baller.setOversBalled(idToInformation.get(id).getOversBalled());
            baller.setWicketsTaken(idToInformation.get(id).getWickets());
            baller.setRunsGiven(idToInformation.get(id).getRunsGiven());
            ballerRepository.save(baller);
        }
    }
}

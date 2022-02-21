package com.cricket.cricketgame.pojo;

import com.cricket.cricketgame.entity.Baller;
import com.cricket.cricketgame.entity.Batsman;
import com.cricket.cricketgame.repository.BallerRepository;
import com.cricket.cricketgame.repository.BatsmanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TeamInformation {
    @Autowired
    private BallerRepository ballerRepository;
    @Autowired
    private BatsmanRepository batsmanRepository;
    private final Scanner sc=new Scanner(System.in);
    private final Map<Integer, String> playerIdToName = new HashMap<>();
    private final Map<Integer, String> ballersIdToName = new HashMap<>();

    public static TeamInformation of() {
        return new TeamInformation();
    }

    private TeamInformation() {}

    public Map<Integer, BattingInformation> getBatters() {
        Map<Integer, BattingInformation> battersList = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            String batsmanName = sc.nextLine();
            playerIdToName.put(i, batsmanName);
            battersList.put(i, null);
            Batsman batsman=new Batsman();
            batsman.setBatsmanName(batsmanName);
            batsmanRepository.save(batsman);
        }

        return battersList;
    }

    public Map<Integer, BallingInformation> getBallers() {
        Map<Integer, BallingInformation> ballersList = new HashMap<>();
        System.out.println("Enter the number of ballers");
        int ballerCount = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the baller names");
        for (int i = 0; i < ballerCount; i++) {
            String ballerName = sc.nextLine();
            ballersIdToName.put(i,ballerName);
            ballersList.put(i, null);
            Baller baller=new Baller();
            baller.setBallerName(ballerName);
            ballerRepository.save(baller);
        }
        return ballersList;
    }

    public Map<Integer, String> getPlayerIdToName() {
        return playerIdToName;
    }

    public Map<Integer, String> getBallersIdToName() {
        return ballersIdToName;
    }
}
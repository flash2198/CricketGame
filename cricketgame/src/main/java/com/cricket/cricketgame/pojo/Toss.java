package com.cricket.cricketgame.pojo;

import java.util.Random;

public class Toss {
    private final Random random=new Random();
    private final String firstTeamName;
    private final String secondTeamName;
    private String tossWinner;
    private String tossLoser;

    public static Toss of(String firstTeamName, String secondTeamName) {
        return new Toss(firstTeamName, secondTeamName);
    }

    public Toss(String firstTeamName, String secondTeamName) {
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
    }

    public void tossTheCoin() {
        int toss = random.nextInt(2);
        if (toss == 0) {
            tossWinner = firstTeamName;
            tossLoser = secondTeamName;
            return;
        }
        tossWinner = secondTeamName;
        tossLoser = firstTeamName;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public String getTossLoser() {
        return tossLoser;
    }

    public String batOrBowl() {
        int batBowl = random.nextInt(2);
        if (batBowl == 0) {
            return "bat";
        }
        return "ball";
    }
}

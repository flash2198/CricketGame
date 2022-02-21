package com.cricket.cricketgame.pojo;

public class BallingInformation {
    private int oversBalled;
    private int runsGiven;
    private int wickets;

    public void setOversBalled(int over) {
        oversBalled += over;
    }

    public void setRunsGiven(int runs) {
        runsGiven += runs;
    }

    public void setWickets(int wicket) {
        wickets += wicket;
    }

    public int getOversBalled() {
        return oversBalled;
    }

    public int getWickets() {
        return wickets;
    }

    public int getRunsGiven() {
        return runsGiven;
    }

    @Override
    public String toString() {
        return oversBalled + " " + wickets + " " + runsGiven;
    }
}

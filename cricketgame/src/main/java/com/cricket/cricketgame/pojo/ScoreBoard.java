package com.cricket.cricketgame.pojo;

import java.util.Map;

public class ScoreBoard {

    private String manOfTheMatch;
    int highestRunsScored = 0;

    public static ScoreBoard of() {
        return new ScoreBoard();
    }

    private ScoreBoard() {}

    public void getBattersPerformance(
            String teamName,
            Map<Integer, BattingInformation> battersList,
            Map<Integer, String> idToNames,
            int totalRunsScored) {

        System.out.println("Total runs scored by the team:" + totalRunsScored);
        System.out.println(teamName + " scoreboard:");
        for (Integer id : battersList.keySet()) {
            if (battersList.get(id) != null) {

                if (highestRunsScored < battersList.get(id).getRunsScored()) {
                    highestRunsScored = battersList.get(id).getRunsScored();
                    manOfTheMatch = idToNames.get(id);
                }

                System.out.println(
                        idToNames.get(id)
                                + ":"
                                + " runsscored:"
                                + battersList.get(id).getRunsScored()
                                + " ballsplayed:"
                                + battersList.get(id).getBallsPlayed()
                                + " strikerate:"
                                + battersList.get(id).getStrikeRate());
            } else {
                System.out.println(idToNames.get(id) + ": not played");
            }
        }
    }

    public void getBallersPerformance(
            String teamName,
            Map<Integer, BallingInformation> ballersList,
            Map<Integer, String> idToNames) {
        System.out.println("Balling details of:" + teamName);
        for (Integer id : ballersList.keySet()) {
            if (ballersList.get(id) != null) {
                System.out.println(
                        idToNames.get(id)
                                + ":"
                                + " runsGiven:"
                                + ballersList.get(id).getRunsGiven()
                                + " oversBalled:"
                                + ballersList.get(id).getOversBalled()
                                + " wickets taken:"
                                + ballersList.get(id).getWickets());
            }
        }
    }

    public String getResultOfMatch(
            int firstTeamRuns, String firstTeamName, int secondTeamRuns, String secondTeamName) {
        if (firstTeamRuns > secondTeamRuns) {
            System.out.println(
                    firstTeamName + " team's wins by " + (firstTeamRuns - secondTeamRuns) + " runs");
            return firstTeamName;
        }

        if (firstTeamRuns == secondTeamRuns) {
            System.out.println("The match is draw");
            return "draw";
        }

        System.out.println(secondTeamName + " team's wins the match");
        return secondTeamName;
    }

    public String showManOfTheMatch() {
        System.out.println("Man ofthe match is:" + manOfTheMatch);
        return manOfTheMatch;
    }
}

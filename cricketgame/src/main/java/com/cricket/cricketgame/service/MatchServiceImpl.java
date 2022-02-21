package com.cricket.cricketgame.service;

import com.cricket.cricketgame.entity.MatchDetails;
import com.cricket.cricketgame.pojo.*;
import com.cricket.cricketgame.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService{
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private BatsmanService batsmanService;
    @Autowired
    private BallerService ballerService;

    @Override
    public void startMatch() {
        MatchDetails matchDetails=matchRepository.findById(1L).get();
        int numberOfOvers=matchDetails.getNumberOfOvers();
        TeamInformation teamFirst = TeamInformation.of();
        System.out.println("enter the player names for first team");
        Map<Integer, BattingInformation> firstTeamPlayerList = teamFirst.getBatters();
        Map<Integer, String> firstTeamPlayerIds = teamFirst.getPlayerIdToName();
        System.out.println("enter the baller details");
        Map<Integer, BallingInformation> firstTeamBallers = teamFirst.getBallers();
        Map<Integer, String> firstTeamBallerId = teamFirst.getBallersIdToName();

        TeamInformation teamSecond = TeamInformation.of();
        System.out.println("enter the player names for second team");
        Map<Integer, BattingInformation> secondTeamPlayerList = teamSecond.getBatters();
        Map<Integer, String> secondTeamPlayerIds = teamSecond.getPlayerIdToName();
        System.out.println("enter the baller details");
        Map<Integer, BallingInformation> secondTeamBallers = teamSecond.getBallers();
        Map<Integer, String> secondTeamBallerId = teamSecond.getBallersIdToName();

        String firstTeamName=matchDetails.getFirstTeamName();
        String secondTeamName=matchDetails.getSecondTeamName();
        Map<String, Map<Integer, BattingInformation>> teamNameToBatters = new HashMap<>();
        teamNameToBatters.put(firstTeamName, firstTeamPlayerList);
        teamNameToBatters.put(secondTeamName, secondTeamPlayerList);


        Map<String, Map<Integer, BallingInformation>> teamNameToBallers = new HashMap<>(); // rename
        teamNameToBallers.put(firstTeamName, firstTeamBallers);
        teamNameToBallers.put(secondTeamName, secondTeamBallers);

        Toss toss=new Toss(firstTeamName,secondTeamName);
        toss.tossTheCoin();
        String tossWinner = toss.getTossWinner();
        String tossLoser = toss.getTossLoser();
        String tossWinnerDecision = toss.batOrBowl();

        System.out.println(
                tossWinner + " wins the toss and chooses to " + tossWinnerDecision + " first");
        String firstBattingTeam;
        String secondBattingTeam;
        String firstBallingTeam;
        String secondBallingTeam;
        if (tossWinnerDecision.equals("bat")) {
            firstBattingTeam = tossWinner;
            firstBallingTeam = tossLoser;
            secondBattingTeam = tossLoser;
            secondBallingTeam = tossWinner;
        } else {
            secondBattingTeam = tossWinner;
            firstBallingTeam = tossWinner;
            firstBattingTeam = tossLoser;
            secondBallingTeam = tossLoser;
        }

        Innings firstInnings =
                Innings.of(
                        teamNameToBatters.get(firstBattingTeam), teamNameToBallers.get(firstBallingTeam),numberOfOvers);
        firstInnings.startBatting();
        int firstInningsScore = firstInnings.getTotalScore();

        ScoreBoard scoreBoard = ScoreBoard.of();
        scoreBoard.getBattersPerformance(
                firstBattingTeam,
                teamNameToBatters.get(firstBattingTeam),
                firstTeamPlayerIds,
                firstInningsScore);
        Map<String, Map<Integer, String>> teamNameToBallerIds = new HashMap<>();
        teamNameToBallerIds.put(firstTeamName, firstTeamBallerId);
        teamNameToBallerIds.put(secondTeamName, secondTeamBallerId);
        scoreBoard.getBallersPerformance(
                firstBallingTeam,
                teamNameToBallers.get(firstBallingTeam),
                teamNameToBallerIds.get(firstBallingTeam));


        Innings secondInnings =
                Innings.of(
                        teamNameToBatters.get(secondBattingTeam),
                        teamNameToBallers.get(secondBallingTeam),numberOfOvers,
                        firstInningsScore);
        secondInnings.startBatting();
        int secondInningsScore = secondInnings.getTotalScore();
        scoreBoard.getBattersPerformance(
                secondBattingTeam,
                teamNameToBatters.get(secondBattingTeam),
                secondTeamPlayerIds,
                secondInningsScore);
        scoreBoard.getBallersPerformance(
                secondBallingTeam,
                teamNameToBallers.get(secondBallingTeam),
                teamNameToBallerIds.get(secondBallingTeam));

        Map<String,Map<Integer,String>> teamNameToBatsmanIds=new HashMap<>();
        teamNameToBatsmanIds.put(firstTeamName,firstTeamPlayerIds);
        teamNameToBatsmanIds.put(secondTeamName,secondTeamPlayerIds);
        SaveDataToDb saveDataToDb=new SaveDataToDb();
        saveDataToDb.setBatsmanDetailsToDb(teamNameToBatsmanIds.get(firstBattingTeam),teamNameToBatters.get(firstBattingTeam));
        saveDataToDb.setBatsmanDetailsToDb(teamNameToBatsmanIds.get(secondBattingTeam),teamNameToBatters.get(secondBattingTeam));
        saveDataToDb.setBallerDetailsToDb(teamNameToBallerIds.get(firstBallingTeam),teamNameToBallers.get(firstBallingTeam));
        saveDataToDb.setBallerDetailsToDb(teamNameToBallerIds.get(secondBallingTeam),teamNameToBallers.get(secondBallingTeam));

        String winnerTeam=scoreBoard.getResultOfMatch(
                firstInningsScore, firstBattingTeam, secondInningsScore, secondBattingTeam);
        String manOfTheMatch= scoreBoard.showManOfTheMatch();
        matchDetails.setWinnerOfTheMatch(winnerTeam);
        matchDetails.setManOfTheMatch(manOfTheMatch);
        matchRepository.save(matchDetails);

    }

    @Override
    public MatchDetails saveMatchInformation(MatchDetails matchDetails) {
        return matchRepository.save(matchDetails);
    }
}

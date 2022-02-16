package cricket;

import java.util.*;

public class GameController {
  static final Scanner SC = new Scanner(System.in);
  static final Random RANDOM = new Random();

  static {
    System.out.println("Enter the number of overs");
  }

  static final int OVER = SC.nextInt();

  public static void main(String[] args) {
    System.out.println("Enter the name of first team");
    SC.nextLine();
    String firstTeamName = Objects.requireNonNull(SC.nextLine());
    TeamInformation teamFirst = TeamInformation.of();
    System.out.println("enter the player names for first team");
    Map<Integer, BattingInformation> firstTeamPlayerList = teamFirst.getTeamMembers(); //
    Map<Integer, String> firstTeamPlayerIds = teamFirst.getPlayerIdToName();
    System.out.println("enter the baller details");
    Map<Integer, BallingInformation> firstTeamBallers = teamFirst.getBallers(); //
    Map<Integer, String> firstTeamBallerId = teamFirst.getBallersIdToName();

    System.out.println("Enter the name of second team");
    String secondTeamName = Objects.requireNonNull(SC.nextLine());
    TeamInformation teamSecond = TeamInformation.of();
    System.out.println("enter the player names for second team");
    Map<Integer, BattingInformation> secondTeamPlayerList = teamSecond.getTeamMembers();
    Map<Integer, String> secondTeamPlayerIds = teamSecond.getPlayerIdToName();
    System.out.println("enter the baller details");
    Map<Integer, BallingInformation> secondTeamBallers = teamSecond.getBallers();
    Map<Integer, String> secondTeamBallerId = teamSecond.getBallersIdToName();

    Map<String, Map<Integer, BattingInformation>> teamNameToBatters = new HashMap<>();
    teamNameToBatters.put(firstTeamName, firstTeamPlayerList);
    teamNameToBatters.put(secondTeamName, secondTeamPlayerList);

    Map<String, Map<Integer, BallingInformation>> teamNameToBallers = new HashMap<>(); // rename
    teamNameToBallers.put(firstTeamName, firstTeamBallers);
    teamNameToBallers.put(secondTeamName, secondTeamBallers);

    Map<String, Map<Integer, String>> teamNameToBallerIds = new HashMap<>();
    teamNameToBallerIds.put(firstTeamName, firstTeamBallerId);
    teamNameToBallerIds.put(secondTeamName, secondTeamBallerId);

    Toss toss = Toss.of(firstTeamName, secondTeamName);
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
            teamNameToBatters.get(firstBattingTeam), teamNameToBallers.get(firstBallingTeam));
    firstInnings.startBatting();
    int firstInningsScore = firstInnings.getTotalScore();

    ScoreBoard scoreBoard = ScoreBoard.of();
    scoreBoard.getBattersPerformance(
        firstBattingTeam,
        teamNameToBatters.get(firstBattingTeam),
        firstTeamPlayerIds,
        firstInningsScore);
    scoreBoard.getBallersPerformance(
        firstBallingTeam,
        teamNameToBallers.get(firstBallingTeam),
        teamNameToBallerIds.get(firstBallingTeam));

    Innings secondInnings =
        Innings.of(
            teamNameToBatters.get(secondBattingTeam),
            teamNameToBallers.get(secondBallingTeam),
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
    scoreBoard.getResultOfMatch(
        firstInningsScore, firstBattingTeam, secondInningsScore, secondBattingTeam);
    scoreBoard.showManOfTheMatch();
  }
}

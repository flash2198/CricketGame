package cricket;

import java.util.ArrayList;

import java.util.Map;

public class Innings {
  private final Map<Integer, BattingInformation> battersList;
  private final Map<Integer, BallingInformation> ballersList;
  private int totalScore = 0;
  private int target;
  private boolean isSecondInning = false;

  public static Innings of(
      Map<Integer, BattingInformation> battersList, Map<Integer, BallingInformation> ballersList) {
    return new Innings(battersList, ballersList);
  }

  private Innings(
      Map<Integer, BattingInformation> map, Map<Integer, BallingInformation> ballersMap) {
    this.battersList = map;
    this.ballersList = ballersMap;
  }

  public static Innings of(
      Map<Integer, BattingInformation> battersList,
      Map<Integer, BallingInformation> ballersList,
      int target) {
    return new Innings(battersList, ballersList, target);
  }

  private Innings(
      Map<Integer, BattingInformation> map,
      Map<Integer, BallingInformation> ballersMap,
      int target) {
    this.battersList = map;
    this.ballersList = ballersMap;
    this.target = target;
    this.isSecondInning = true;
  }

  public void startBatting() {
    ArrayList<Integer> battersId = new ArrayList<>(battersList.keySet());
    ArrayList<Integer> ballersId = new ArrayList<>(ballersList.keySet());
    int wicketsLeft = 10;
    int ballsPlayed = 0;
    int battersIndex = 0;
    int ballersIndex = 0;
    int runsScoredInOver = 0;
    int wicketsInOver = 0;
    int numberOfBallers = ballersId.size();
    int runsScoredByPlayer = 0;
    for (int i = 1; i < (GameController.OVER * 6) + 1; i++) {
      if (isSecondInning && totalScore > target) {
        BattingInformation obj =
            BattingInformation.of(
                runsScoredByPlayer, ballsPlayed, (runsScoredByPlayer / (double) ballsPlayed) * 100);
        battersList.put(battersId.get(battersIndex), obj);
        ballersList.get(ballersId.get(ballersIndex)).setOversBalled(1);
        ballersList.get(ballersId.get(ballersIndex)).setRunsGiven(runsScoredInOver);
        ballersList.get(ballersId.get(ballersIndex)).setWickets(wicketsInOver);
        return;
      }

      int runsWickets = GameController.RANDOM.nextInt(8);
      ballsPlayed += 1;

      if (runsWickets == 7) {
        wicketsInOver += 1;
        BattingInformation obj =
            BattingInformation.of(
                runsScoredByPlayer, ballsPlayed, (runsScoredByPlayer / (double) ballsPlayed) * 100);
        battersList.put(battersId.get(battersIndex), obj);
        battersIndex += 1;
        wicketsLeft = wicketsLeft - 1;
        runsScoredByPlayer = 0;
        ballsPlayed = 0;
      } else {
        runsScoredInOver += runsWickets;
        runsScoredByPlayer += runsWickets;
        totalScore += runsWickets;
      }

      if (wicketsLeft == 0) {
        ballersList.get(ballersId.get(ballersIndex)).setOversBalled(1);
        ballersList.get(ballersId.get(ballersIndex)).setRunsGiven(runsScoredInOver);
        ballersList.get(ballersId.get(ballersIndex)).setWickets(wicketsInOver);
        return;
      }

      if (i % 6 == 0) {
        if (ballersList.get(ballersId.get(ballersIndex)) == null) {
          BallingInformation obj = new BallingInformation();
          ballersList.put(ballersId.get(ballersIndex), obj);
        }
        ballersList.get(ballersId.get(ballersIndex)).setOversBalled(1);
        ballersList.get(ballersId.get(ballersIndex)).setRunsGiven(runsScoredInOver);
        ballersList.get(ballersId.get(ballersIndex)).setWickets(wicketsInOver);
        runsScoredInOver = 0;
        wicketsInOver = 0;
        ballersIndex = (ballersIndex + 1) % numberOfBallers;
      }
    }

    if (runsScoredByPlayer != 0) {
      BattingInformation obj =
          BattingInformation.of(
              runsScoredByPlayer, ballsPlayed, (runsScoredByPlayer / (double) ballsPlayed) * 100);
      battersList.put(battersId.get(battersIndex), obj);
      ballersList.get(ballersId.get(ballersIndex)).setWickets(wicketsInOver);
      ballersList.get(ballersId.get(ballersIndex)).setRunsGiven(runsScoredInOver);
    }
  }

  public int getTotalScore() {
    return totalScore;
  }
}

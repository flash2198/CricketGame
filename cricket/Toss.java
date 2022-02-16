package cricket;

public class Toss {
  private final String firstTeamName;
  private final String secondTeamName;
  private String tossWinner;
  private String tossLoser;

  public static Toss of(String firstTeamName, String secondTeamName) {
    return new Toss(firstTeamName, secondTeamName);
  }

  private Toss(String firstTeamName, String secondTeamName) {
    this.firstTeamName = firstTeamName;
    this.secondTeamName = secondTeamName;
  }

  public void tossTheCoin() {
    int toss = GameController.RANDOM.nextInt(2);
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
    int batBowl = GameController.RANDOM.nextInt(2);
    if (batBowl == 0) {
      return "bat";
    }
    return "ball";
  }
}

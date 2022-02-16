package cricket;

public class BattingInformation {
  private final int runsScored;
  private final int ballsPlayed;
  private final double strikeRate;

  public static BattingInformation of(int runsScored, int ballsPlayed, double strikeRate) {
    return new BattingInformation(runsScored, ballsPlayed, strikeRate);
  }

  private BattingInformation(int runsScored, int ballsPlayed, double strikeRate) {
    this.runsScored = runsScored;
    this.ballsPlayed = ballsPlayed;
    this.strikeRate = strikeRate;
  }

  public int getRunsScored() {
    return runsScored;
  }

  public int getBallsPlayed() {
    return ballsPlayed;
  }

  public double getStrikeRate() {
    return strikeRate;
  }

  @Override
  public String toString() {
    return runsScored + " " + ballsPlayed + " " + strikeRate;
  }
}

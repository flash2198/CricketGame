package cricket;

import java.util.HashMap;
import java.util.Map;

public class TeamInformation {

  private final Map<Integer, String> playerIdToName = new HashMap<>();
  private final Map<Integer, String> ballersIdToName = new HashMap<>();

  public static TeamInformation of() {
    return new TeamInformation();
  }

  private TeamInformation() {}

  public Map<Integer, BattingInformation> getBatters() {
    Map<Integer, BattingInformation> battersList = new HashMap<>();

    for (int i = 0; i < 10; i++) {
      String name = GameController.SC.nextLine();
      playerIdToName.put(i, name);
      battersList.put(i, null);
    }

    return battersList;
  }

  public Map<Integer, BallingInformation> getBallers() {
    Map<Integer, BallingInformation> ballersList = new HashMap<>();
    System.out.println("Enter the number of ballers");
    int ballerCount = GameController.SC.nextInt();
    GameController.SC.nextLine();
    System.out.println("Enter the baller names");
    for (int i = 0; i < ballerCount; i++) {
      String name = GameController.SC.nextLine();
      ballersIdToName.put(i, name);
      ballersList.put(i, null);
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

package com.ganxus.nthu;
import java.io.IOException;
import java.util.*;

/**
 * A class for UI
 */
public class UI {
  private List<String> commands;
  private GradeSystems system;

  /**
   * Class constructor
   */
  public UI() throws IOException {
    commands = Arrays.asList(new String[]{"G", "R", "W", "E"});
    system = new GradeSystems();
  }

  /**
   * Class constructor
   *
   * @param filename filename for data
   */
  public UI(String filename) throws IOException {
    commands = Arrays.asList(new String[]{"G", "R", "W", "E"});
    system = new GradeSystems(filename);
  }

  public void run() {
  }

  public String promptId() {
    System.out.print("輸入ID或 Q (結束使用)? ");
    Scanner sc = new Scanner(System.in);
    return sc.next();
  }

  public String promptCommand() throws NoCommandException {
    System.out.println("G 顯示成績 (Grade)");
    System.out.println("R 顯示排名 (Rank)");
    System.out.println("A 顯示平均 (Average)");
    System.out.println("W 更新配分 (Weight)");
    System.out.println("E 離開選單 (Exit)");
    Scanner sc = new Scanner(System.in);
    String command = sc.next();
    if (!commands.contains(command))
      throw new NoCommandException(command);
    return command;
  }

  public void showWelcomeMessage(String id) {
    System.out.println("Welcome " + id);
  }

  public void showEndMessage() {
  }

  public void showGrade() {
  }

  public void showRank() {
  }

  public void updateWeights() {
  }

  public static class NoCommandException extends Exception {
    public NoCommandException(String command) {
      super("No command: " + command);
    }
  }
}


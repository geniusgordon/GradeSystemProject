package com.ganxus.nthu;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class for UI
 */
public class UI {
  private GradeSystems system;

  /**
   * Class constructor
   */
  public UI() throws IOException {
    system = new GradeSystems();
  }

  /**
   * Class constructor
   *
   * @param filename filename for data
   */
  public UI(String filename) throws IOException {
    system = new GradeSystems(filename);
  }

  public void run() {
  }

  public String promptId() {
    System.out.print("輸入ID或 Q (結束使用)? ");
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

  public String promptCommand() {
    return "";
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
}


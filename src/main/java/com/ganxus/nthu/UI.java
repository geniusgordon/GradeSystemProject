package com.ganxus.nthu;
import java.io.IOException;
import java.util.*;

/**
 * A class for UI
 */
public class UI {
  private List<String> commands;
  private String id;
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

  public String getGradeString(int grade) {
    return grade >= 60 ? String.valueOf(grade) : String.valueOf(grade) + "*";
  }

  public void showGrades(String id) {
    Grades s = system.getGradeById(id);
    int[] grades = s.getGrades();
    System.out.println(s.getName() + "成績:");
    System.out.println("lab1: " + getGradeString(grades[Grades.LAB1]));
    System.out.println("lab2: " + getGradeString(grades[Grades.LAB2]));
    System.out.println("lab3: " + getGradeString(grades[Grades.LAB3]));
    System.out.println("mid-term: " + getGradeString(grades[Grades.MID]));
    System.out.println("final exam: " + getGradeString(grades[Grades.FINAL]));
    System.out.println("total grade: " + getGradeString(s.calculateTotalGrade(system.getWeights())));
  }

  public void showRank(String id) {
  }

  public void updateWeights() {
  }

  public static class NoCommandException extends Exception {
    public NoCommandException(String command) {
      super("No command: " + command);
    }
  }
}


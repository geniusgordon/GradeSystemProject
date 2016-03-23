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
    Scanner sc = new Scanner(System.in);
    while(true) {
      String i = promptId();
      if (system.getGradeById(i) != null) {
        id = i;
        break;
      }
      if (i.equals("Q")) {
        showEndMessage();
        return;
      }
      System.out.println("沒有這個人");
    }
    showWelcomeMessage(id);
    while(true) {
      try {
        String command = promptCommand();
        if (command.equals("G")) {
          showGrades(id);
        } else if (command.equals("R")) {
          showRank(id);
        } else if (command.equals("W")) {
          System.out.println("舊配分");
          showWeights(system.getWeights());
          float[] newWeights = updateWeights();
          System.out.println("請確認新配分");
          showWeights(newWeights);
          String confirm = sc.next();
          if (confirm.equals("Y")) {
            try {
              system.updateWeights(newWeights);
            } catch(IllegalArgumentException e) {
              System.out.println("配分沒打好");
            }
          } else {
            System.out.println("配分未更新");
          }
        } else if (command.equals("E")) {
          showEndMessage();
          return;
        } 
      } catch(NoCommandException e) {
        System.out.println("沒有這個指令");
      }
    }
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
    Grades s = system.getGradeById(id);
    System.out.println("Welcome " + s.getName());
  }

  public void showEndMessage() {
    System.out.println("Bye~");
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
    Grades s = system.getGradeById(id);
    int rank = system.getRank(id);
    System.out.println(String.format("%s排名第%d", s.getName(), rank));
  }

  public void showWeights(float[] weight) {
    System.out.println("lab1: " + (weight[0]*100) + "%");
    System.out.println("lab2: " + (weight[1]*100) + "%");
    System.out.println("lab3: " + (weight[2]*100) + "%");
    System.out.println("mid-term: " + (weight[3]*100) + "%");
    System.out.println("final exam: " + (weight[4]*100) + "%");
  }

  public float[] updateWeights() {
    float[] newWeights = new float[5];
    float total = 0f;
    Scanner sc = new Scanner(System.in);
    System.out.print("lab1: ");
    newWeights[0] = sc.nextFloat();
    System.out.print("lab2: ");
    newWeights[1] = sc.nextFloat();
    System.out.print("lab3: ");
    newWeights[2] = sc.nextFloat();
    System.out.print("mid-term: ");
    newWeights[3] = sc.nextFloat();
    System.out.print("final exam: ");
    newWeights[4] = sc.nextFloat();
    for (float w: newWeights)
      total += w;
    for (int i = 0; i < newWeights.length; i++)
      newWeights[i] /= total;
    return newWeights;
  }

  public static class NoCommandException extends Exception {
    public NoCommandException(String command) {
      super("No command: " + command);
    }
  }
}


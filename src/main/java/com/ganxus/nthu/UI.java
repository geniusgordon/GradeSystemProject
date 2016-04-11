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
    commands = Arrays.asList(new String[]{"G", "R","A", "W", "E"});
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

  /**
   * This is the main function of UI.
   */
  public void run() {
    String id = getStudentId();
    if (id == null) {
        return;
    }
    showWelcomeMessage(id);
    while(true) {
      try {
        String command = promptCommand();
        if (!executeCommand(command,id)) {
            return;
        }   
      } catch(NoCommandException e) {
        System.out.println("沒有這個指令");
      }
    }
  }
  /**
   * This function is used to determine 
   * which function should execute from user's input 
   *
   * @param command user's input 
   */

  private boolean executeCommand(String command, String id){
      if (command.equals("G")) {
          showGrades(id);
          return true;
      } else if (command.equals("R")) {
          showRank(id);
          return true;
      } else if (command.equals("W")) {
          updateWeights();
          return true;
      } else if (command.equals("A")) {
          showAverageGrade(id);
          return true;
      } else if (command.equals("E")) {
          showEndMessage();
          return false;
      }
        else {
          return true;
      } 
  }
  /**
  * This function get user's input id,
  * check if exist in system.
  */
  private String getStudentId() {
    while(true) {
      String id = promptId();
      if (system.getGradeById(id) != null)
        return id;
      if (id.equals("Q")) {
        showEndMessage();
        return null;
      }
      System.out.println("沒有這個人");
    }
  }

  /**
   * This method prompts the user for student ID or quit.
   *
   * @return the ID user inputs
   */
  public String promptId() {
    System.out.print("輸入ID或 Q (結束使用)? ");
    Scanner sc = new Scanner(System.in);
    return sc.next();
  }

  /**
   * This method prompts the user for a command.
   *
   * @return the command user inputs
   */
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

  /**
   * This method prints Welcome NAME on the console
   * 
   * @param id the student's id
   *
   */
  public void showWelcomeMessage(String id) {
    Grades s = system.getGradeById(id);
    System.out.println("Welcome " + s.getName());
  }

  /**
   * This method prints Bye~ on the console
   */
  public void showEndMessage() {
    System.out.println("Bye~");
  }

  /**
   * Show Average Grade 
   */
  public void showAverageGrade(String id) {
    Grades s = system.getGradeById(id);
    float average = system.getAverage(id);
    System.out.println(s.getName() + "平均成績: " + String.valueOf(average));
  }

  /**
   * This method returns the string of the grade
   * and adds * at the end if grade is below 60.
   *
   * @param grade grade
   * @return the string of the grade.
   */
  public String getGradeString(int grade) {
    return grade >= 60 ? String.valueOf(grade) : String.valueOf(grade) + "*";
  }

  /**
   * This method prints the grades of the student.
   *
   * @param id the student's id
   */
  public void showGrades(String id) {
    Grades s = system.getGradeById(id);
    int[] grades = s.getGrades();
    System.out.println(s.getName() + "成績:");
    for (int i = 0; i < 5; i++) {
      System.out.println(Grades.GRADES[i] + ": " + getGradeString(grades[i]));
    }
    System.out.println("total grade: " + getGradeString(s.calculateTotalGrade(system.getWeights())));
  }

  /**
   * This method prints the rank of the student.
   *
   * @param id the student's id
   */
  public void showRank(String id) {
    Grades s = system.getGradeById(id);
    int rank = system.getRank(id);
    System.out.println(String.format("%s排名第%d", s.getName(), rank));
  }

  /**
   * This method prints the weights with %.
   */
  public void showWeights(float[] weight) {
    for (int i = 0; i < 5; i++) {
      System.out.println(Grades.GRADES[i] + ": " + (weight[i]*100) + "%");
    }
  }

  public void updateWeights() {
    System.out.println("舊配分");
    showWeights(system.getWeights());
    float[] newWeights = getUpdateWeights();
    System.out.println("請確認新配分");
    showWeights(newWeights);
    if (confirm()) {
      system.updateWeights(newWeights);
    } else {
      System.out.println("配分未更新");
    }
  }

  private boolean confirm() {
    System.out.print("(Y/N)");
    Scanner sc = new Scanner(System.in);
    String confirm = sc.next();
    return confirm.equals("Y");
  }

  /**
   * This method prompts the user to enter new weights.
   *
   * @return new weights
   */
  public float[] getUpdateWeights() {
    float[] newWeights = new float[5];
    float total = 0f;
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < 5; i++) {
      newWeights[i] = promptWeight(sc, Grades.GRADES[i]);
    }
    for (float w: newWeights) total += w;
    for (int i = 0; i < newWeights.length; i++) newWeights[i] /= total;
    return newWeights;
  }

  private float promptWeight(Scanner sc, String name) {
    System.out.print(name + ": ");
    return sc.nextFloat();
  }

  public static class NoCommandException extends Exception {
    public NoCommandException(String command) {
      super("No command: " + command);
    }
  }
}


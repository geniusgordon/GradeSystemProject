package com.ganxus.nthu;

/**
 * A class for student's grades
 */
public class Grades {
  static int LAB1 = 0;
  static int LAB2 = 1;
  static int LAB3 = 2;
  static int MID = 3;
  static int FINAL = 4;

  private String name;
  private String id;
  private int[] grades;

  /**
   * Class constructor
   *
   * @param name The student's name.
   * @param id The student's id.
   * @param grades The student's grade.
   */
  public Grades(String name, String id, int[] grades) {
  }

  /**
   * This method calculates total grade
   * @param weights Weights for different grades.
   * @return Total grade for this student.
   */
  public int calculateTotalGrade(float[] weights) {
    return 0;
  }
}


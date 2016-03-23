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
    this.name = name;
    this.id = id;
    this.grades = grades;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public int[] getGrades() {
    return grades;
  }

  /**
   * This method calculates total grade
   * Time estimate: O(n)
   *
   * @param weights Weights for different grades.
   * @return Total grade for this student.
   *
   */
  public int calculateTotalGrade(float[] weights) {
    float total = 0f;
    // for each grade in grades
    for (int i = 0; i < grades.length; i++) {
      // multiple with corresponding weight
      // add them together
      total += grades[i] * weights[i];
    }
    return (int)Math.floor(total + 0.5f);
  }

  /**
   * This method ovverides Object.equals
   * Time estimate: O(n)
   *
   * @param weights Weights for different grades.
   * @return Total grade for this student.
   *
   */
  @Override
  public boolean equals(Object obj) {
    Grades grade = (Grades) obj;
    if (!this.id.equals(grade.id))
      return false;
    if (!this.name.equals(grade.name))
      return false;
    for (int i = 0; i < 5; i++) {
      if (this.grades[i] != grade.grades[i])
        return false;
    }
    return true;
  }
}


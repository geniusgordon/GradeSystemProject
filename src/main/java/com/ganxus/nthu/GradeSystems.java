package com.ganxus.nthu;
import java.util.*;

/**
 * A class for a list of student grades
 */
public class GradeSystems {
  private float[] weights;
  private List<Grades> students;

  static public List<Grades> readGrades(String filename) {
    return new List<Grades>();
  }

  static public Grade parseLine(String line) {
    return null;
  }

  /**
   * Class constructor
   *
   */
  public GradeSystems() {
    weight = { 0.1f, 0.1f, 0.1, 0.3f, 0.4f };
    students = readGrades("gradeInput.txt");
  }

  /**
   * This method gets the student's grade by his id
   * Time estimate: O(n)
   *
   * @param id Student's id.
   * @return Student's grade.
   *
   */
  public Grade getGradeById(String id) {
    return null;
  }

  /**
   * This method returns the student's rank.
   *
   * @param id Student's id.
   * @return Student's rank.
   */
  public int getRank(String id) {
    return 0;
  }

  /**
   * This method updates the weights.
   *
   * @param weights new weights.
   */
  public void updateWeights(float[] weights) {
  }
}


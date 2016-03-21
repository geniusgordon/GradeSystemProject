package com.ganxus.nthu;
import java.util.*;

/**
 * A class for a list of student grades
 */
public class GradeSystems {
  private float[] weights = { 0.1f, 0.1f, 0.1f, 0.3f, 0.4f };
  private List<Grades> students;

  static public List<Grades> readGrades(String filename) {
    return new LinkedList<Grades>();
  }

  /**
   * This function parse a line into a Grade object.
   * 
   * @param line A string represents the students grades.
   * @return a Grade object.
   */
  static public Grades parseLine(String line) {
    String[] s = line.split(" ");
    int[] grades = {
      Integer.parseInt(s[2]),
      Integer.parseInt(s[3]),
      Integer.parseInt(s[4]),
      Integer.parseInt(s[5]),
      Integer.parseInt(s[6]),
    };
    return new Grades(s[1], s[0], grades);
  }

  /**
   * Class constructor
   *
   */
  public GradeSystems() {
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
  public Grades getGradeById(String id) {
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


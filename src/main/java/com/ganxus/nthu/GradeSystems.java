package com.ganxus.nthu;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class for a list of student grades
 */
public class GradeSystems {
  private float[] weights = { 0.1f, 0.1f, 0.1f, 0.3f, 0.4f };
  private List<Grades> students;

  /**
   * This function reads data from a file and parse it into a List object.
   *
   * @param filename the filename of the data.
   * @return a List of Grades.
   */
  static public List<Grades> readGrades(String filename) throws IOException {
    List<Grades> grades = new LinkedList<Grades>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = null;
		while((line = br.readLine()) != null) {
      grades.add(parseLine(line));
		}
    return grades;
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
  public GradeSystems() throws IOException {
    students = readGrades("gradeInput.txt");
  }

  public GradeSystems(String filename) throws IOException {
    students = readGrades(filename);
  }

  public float[] getWeights() {
    return weights;
  }

  /**
   * This method gets the student's grade by his id
   * Time estimate: O(n)
   *
   * @param id Student's id.
   * @return Student's grade, return null if not found.
   *
   */
  public Grades getGradeById(String id) {
    for (Grades g: students) {
      if (g.getId().equals(id))
        return g;
    }
    return null;
  }

  /**
   * This method returns the student's rank.
   *
   * @param id Student's id.
   * @return Student's rank, return -1 if not found.
   */
  public int getRank(String id) {
    Grades s = getGradeById(id);
    if (s == null)
      return -1;
    int rank = 1;
    for(Grades g: students) {
      if (g.getId().equals(id))
        continue;
      int gGrade = g.calculateTotalGrade(weights);
      int sGrade = s.calculateTotalGrade(weights);
      if (gGrade > sGrade)
        rank++;
    }
    return rank;
  }

  /**
   * This method updates the weights.
   *
   * @param weights new weights.
   */
  public void updateWeights(float[] weights) throws IllegalArgumentException {
    if (weights.length != 5)
      throw new IllegalArgumentException();

    float total = 0f;
    for (float w: weights) {
      total += w;
    }
    float e = (total > 1.0f) ? total - 1.0f : 1.0f - total;
    if (e > 0.0000001f)
      throw new IllegalArgumentException();
    this.weights = weights;
  }
}


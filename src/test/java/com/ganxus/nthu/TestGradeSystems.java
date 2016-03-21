import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import java.io.IOException;
import com.ganxus.nthu.Grades;
import com.ganxus.nthu.GradeSystems;

public class TestGradeSystems {
  static String testInput = "src/test/java/com/ganxus/nthu/testInput.txt";

  @Test
  public void testParseLine() {
    String line = "102062312 Gordon 100 100 100 100 100";
    int grades[] = { 100, 100, 100, 100, 100 };
    Grades expectedGrade = new Grades("Gordon", "102062312", grades);
    Grades testGrade = GradeSystems.parseLine(line);
    assertEquals(expectedGrade, testGrade);
  }

  @Test
  public void testReadGrades() throws IOException {
    int grades1[] = { 100, 100, 100, 100, 100 };
    int grades2[] = { 0, 0, 0, 0, 0 };
    Grades student1 = new Grades("Gordon", "102062312", grades1);
    Grades student2 = new Grades("Frank", "102062115", grades2);
    List<Grades> expectedGrade = new LinkedList<Grades>();
    expectedGrade.add(student1);
    expectedGrade.add(student2);
    List<Grades> testGrade = GradeSystems.readGrades(testInput);
    assertEquals(expectedGrade.get(0), testGrade.get(0));
    assertEquals(expectedGrade.get(1), testGrade.get(1));
  }

  @Test
  public void testGetGradeById() throws IOException {
    GradeSystems system = new GradeSystems(testInput);
    int grades[] = { 100, 100, 100, 100, 100 };
    Grades expectedGrade = new Grades("Gordon", "102062312", grades);
    Grades testGrade = system.getGradeById("102062312");
    assertNotNull(testGrade);
    assertEquals(expectedGrade, testGrade);
  }

  @Test
  public void testGetRank() throws IOException {
    GradeSystems system = new GradeSystems(testInput);
    int rank = system.getRank("102062312");
    assertEquals(1, rank);
  }
}


import static org.junit.Assert.*;
import org.junit.Test;
import com.ganxus.nthu.Grades;

public class TestGrades {
  /**
   * Test if the grades calculate with weights return correct value.
   *
   */

  @Test
  public void testCalculateTotalGrade() {
    int grades[] = { 100, 100, 100, 100, 100 };
    Grades grade = new Grades("Gordon", "102062312", grades);
    float weights[] = { 0.1f, 0.1f, 0.1f, 0.3f, 0.4f };
    int totalGrade = grade.calculateTotalGrade(weights);
    assertEquals(100, totalGrade);
  }

  /**
   * Test the override function "equals" have the correct compare between Grade Object.
   *
   */   
  @Test
  public void testEquals(){
    int grades[] = {100, 100, 100, 100, 100};
    Grades grade1 = new Grades("Gordon","102062312",grades);
    Grades grade2 = new Grades("Gordon","102062312",grades);
    
    boolean checkEqual = grade1.equals(grade2);
    assertEquals(true, checkEqual);
  }
}


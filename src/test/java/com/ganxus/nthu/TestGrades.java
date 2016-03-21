import static org.junit.Assert.*;
import org.junit.Test;
import com.ganxus.nthu.Grades;

public class TestGrades {

  @Test
  public void testCalculateTotalGrade() {
    int grades[] = { 100, 100, 100, 100, 100 };
    Grades grade = new Grades("Gordon", "102062312", grades);
    float weights[] = { 0.1f, 0.1f, 0.1f, 0.3f, 0.4f };
    int totalGrade = grade.calculateTotalGrade(weights);
    assertEquals(100, totalGrade);
  }
}


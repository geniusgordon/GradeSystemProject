import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import com.ganxus.nthu.Grades;
import com.ganxus.nthu.GradeSystems;

public class TestGradeSystems {

  @Test
  public void testParseLine() {
    String line = "102062312 Gordon 100 100 100 100 100";
    int grades[] = { 100, 100, 100, 100, 100 };
    Grades expectedGrade = new Grades("Gordon", "102062312", grades);
    Grades testGrade = GradeSystems.parseLine(line);
    assertEquals(expectedGrade, testGrade);
  }

}


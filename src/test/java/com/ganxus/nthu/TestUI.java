import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.*;
import com.ganxus.nthu.UI;
import com.ganxus.nthu.GradeSystems;

public class TestUI {
  static String testInput = "src/test/java/com/ganxus/nthu/testInput.txt";
  UI ui;
  ByteArrayOutputStream out;

  @Before
  public void redirectSystemOut() throws IOException {
    ui = new UI(testInput);
    out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
  }

  @After
  public void cleanUpSystemOut() {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  public void TestPromptId() throws IOException {
    ByteArrayInputStream in = new ByteArrayInputStream("102062312\n".getBytes());
    System.setIn(in);
    String id = ui.promptId();
    assertEquals(id, "102062312");
  }

  @Test
  public void TestWelcomeMessage() {
    ui.showWelcomeMessage("102062312");
    assertEquals("Welcome Gordon\n", out.toString());
  }

  @Test
  public void TestPromptCommand() throws UI.NoCommandException {
    ByteArrayInputStream in = new ByteArrayInputStream("G\n".getBytes());
    System.setIn(in);
    String command = ui.promptCommand();
    assertEquals("G", command);
  }

  @Test(expected=UI.NoCommandException.class)
  public void TestPromptCommandException() throws UI.NoCommandException {
    ByteArrayInputStream in = new ByteArrayInputStream("Z\n".getBytes());
    System.setIn(in);
    String command = ui.promptCommand();
  }

  @Test
  public void TestShowGrades() {
    String expectedOutput = "Gordon成績:\n" +
                            "lab1: 100\n" +
                            "lab2: 100\n" +
                            "lab3: 100\n" +
                            "mid-term: 100\n" +
                            "final exam: 100\n" +
                            "total grade: 100\n";
    ui.showGrades("102062312");
    assertEquals(expectedOutput, out.toString());
  }

  @Test
  public void TestShowFailedGrades() {
    String expectedOutput = "Frank成績:\n" +
                            "lab1: 0*\n" +
                            "lab2: 0*\n" +
                            "lab3: 0*\n" +
                            "mid-term: 0*\n" +
                            "final exam: 0*\n" +
                            "total grade: 0*\n";
    ui.showGrades("102062115");
    assertEquals(expectedOutput, out.toString());
  }

  @Test
  public void TestShowRank() {
    ui.showRank("102062115");
    assertEquals("Frank排名第2\n", out.toString());
  }

  @Test
  public void TestUpdateWeight() {
    String inputWeight = "20\n20\n20\n20\n20\n";
    ByteArrayInputStream in = new ByteArrayInputStream(inputWeight.getBytes());
    System.setIn(in);
    float[] expectedWeights = { 0.2f, 0.2f, 0.2f, 0.2f, 0.2f };
    float[] newWeights = ui.updateWeights();
    for (int i = 0; i < 5; i++) {
      assertEquals(expectedWeights[i], newWeights[i], 0.0000001);
    }
  }
}


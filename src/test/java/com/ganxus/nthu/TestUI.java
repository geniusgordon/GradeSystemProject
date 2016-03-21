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
  public void TestWelcomeMessage() throws IOException {
    ui.showWelcomeMessage("102062312");
    assertEquals("Welcome 102062312\n", out.toString());
  }

  @Test
  public void TestPromptCommand() throws IOException, UI.NoCommandException {
    ByteArrayInputStream in = new ByteArrayInputStream("G\n".getBytes());
    System.setIn(in);
    String command = ui.promptCommand();
    assertEquals("G", command);
  }

  @Test(expected=UI.NoCommandException.class)
  public void TestPromptCommandException() throws IOException, UI.NoCommandException {
    ByteArrayInputStream in = new ByteArrayInputStream("Z\n".getBytes());
    System.setIn(in);
    String command = ui.promptCommand();
  }

  @Test
  public void TestShowGrades() throws IOException {
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
  public void TestShowFailedGrades() throws IOException {
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
}


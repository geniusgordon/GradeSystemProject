import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.*;
import com.ganxus.nthu.UI;
import com.ganxus.nthu.GradeSystems;

public class TestUI {
  static String testInput = "src/test/java/com/ganxus/nthu/testInput.txt";
  ByteArrayOutputStream outContent;

  @Before
  public void redirectSystemOut() throws IOException {
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void cleanUpSystemOut() {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  public void TestPromptId() throws IOException {
    UI ui = new UI(testInput);
    ByteArrayInputStream in = new ByteArrayInputStream("102062312\n".getBytes());
    System.setIn(in);
    String id = ui.promptId();
    assertEquals(id, "102062312");
  }

  @Test
  public void TestWelcomeMessage() throws IOException {
    UI ui = new UI(testInput);
    ui.showWelcomeMessage("102062312");
    assertEquals("Welcome 102062312\n", outContent.toString());
  }
}


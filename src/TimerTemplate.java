import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TimerTemplate {
  public static void main(String[] args) throws IOException {
    Executor executor = Executors.newSingleThreadExecutor();
    Runnable runner =
        () -> {
          long startTime = System.currentTimeMillis();
          Template.main(null);
          System.out.println("Execution took " + (System.currentTimeMillis() - startTime) + " ms");
        };
    System.setIn(new PipedInputStream());
    OutputStreamWriter out =
        new OutputStreamWriter(new PipedOutputStream((PipedInputStream) System.in));
    StringBuilder input = new StringBuilder();
    Random random = new Random();

    out.write(input.toString());
    executor.execute(runner);
    out.close();
  }
}

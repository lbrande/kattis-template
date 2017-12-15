import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimerTemplate {
  public static void main(String[] args) throws IOException {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Runnable runner =
        () -> {
          long startTime = System.currentTimeMillis();
          Template.main(null);
          System.out.println("Execution took " + (System.currentTimeMillis() - startTime) + " ms");
          executor.shutdown();
        };
    System.setIn(new PipedInputStream());
    PrintWriter out = new PrintWriter(new PipedOutputStream((PipedInputStream) System.in));
    Random random = new Random();
    executor.execute(runner);

    out.close();
  }
}

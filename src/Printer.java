import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Printer {
  private final Node rootPrj;

  public Printer(Node root) {
    System.out.println("                                        initial date              "
            + "               final date   " + "                               duration \n");
    rootPrj = root;
    schedule();
  }

  private void schedule() {
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleAtFixedRate(rootPrj::printAllDown, 0, 2000, TimeUnit.MILLISECONDS);
  }
}

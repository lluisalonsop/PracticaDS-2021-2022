import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PrinterVisitor implements Visitor {

  public PrinterVisitor(Node rootPrj) {
    schedule(rootPrj);
  }

  public void schedule(Node rootPrj) {
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleAtFixedRate(() -> {
      rootPrj.accept(this);
    }, 0, 2000, TimeUnit.MILLISECONDS);
  }

  public Node visitNode(Node p) {
    p.print();
    return p;
  }
}

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer extends Observable {
  private static Timer instance;

  private Timer() {
    schedule();
  }

  private void schedule() {
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleAtFixedRate(() -> {
      LocalDateTime currentDate = LocalDateTime.now();
      setChanged();
      notifyObservers(currentDate);
    }, 0, 1, TimeUnit.MILLISECONDS);
  }

  public static Timer getInstance() {
    if (instance == null) {
      instance = new Timer();
    }
    return instance;
  }
}

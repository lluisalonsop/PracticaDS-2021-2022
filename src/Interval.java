import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

/*
Interval starts counting when initialized, adding it as an observer.
In every update EndDate is refreshed until the function end() is called,
where it is deleted from teh observer list therefore not updating endDate anymore.
*/

class Interval implements Observer {
  private boolean working;
  private final LocalDateTime initialDate;
  private LocalDateTime endDate;
  private long intervalTime;

  public Interval() {
    working = true;
    initialDate = LocalDateTime.now();
    Timer.getInstance().addObserver(this);
  }

  public Interval(boolean workingToSet, String initialDateToSet, String endDateToSet) {
    this.working = workingToSet;
    this.initialDate = LocalDateTime.parse(initialDateToSet);
    this.endDate = LocalDateTime.parse(endDateToSet);
    calculateTime();
  }

  public void update(Observable obj, Object arg) {
    endDate = (LocalDateTime) arg;
    calculateTime();
  }

  public void end() {
    working = false;
    Timer.getInstance().deleteObserver(this);
  }

  public boolean getStatus() {
    return working;
  }

  public long getIntervalTime() {
    return intervalTime;
  }

  public LocalDateTime getInitialDate() {
    return initialDate;
  }

  public LocalDateTime getFinalDate() {
    return endDate;
  }

  private void calculateTime() {
    intervalTime = initialDate.until(endDate, ChronoUnit.SECONDS);
  }

  // print interval deleted
  public JSONObject toJson() {
    JSONObject result = new JSONObject();
    result.put("Working", working);
    result.put("InitialDate", initialDate);
    result.put("EndDate", endDate);
    return result;
  }
}

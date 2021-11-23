import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;



public class Task extends Node {
  private final List<Interval> intervals;
  public static final int SECONDSTOMINUTES = 60;
  public static final int MINUTESTOHOURS = 60;
  public static final int HOURSTODAYS = 24;

  public Task(String name) {
    super(name);
    intervals = new ArrayList<>();
  }

  public Task(String name, LinkedList<String> tags) {
    super(name, tags);
    intervals = new ArrayList<>();
  }

  public Task(String name, List<Interval> intervalsnew) {
    super(name);
    intervals = intervalsnew;
  }

  @Override
  protected long calculateTime() {
    long sumatory = 0;
    for (Interval act : intervals) {
      sumatory += act.getIntervalTime();
    }
    return sumatory;
  }
  // This method will Stop an Interval, or Create one.

  public void changeStatus() {
    if (intervals.size() != 0) {
      if (intervals.get(intervals.size() - 1).getStatus()) {
        intervals.get(intervals.size() - 1).end();
      } else {
        intervals.add(new Interval());
      }
    } else {
      intervals.add(new Interval());
    }
  }

  private void converseTimeandPrint(long sumatory) {
    final float seconds = sumatory % SECONDSTOMINUTES;
    sumatory = (int) sumatory / SECONDSTOMINUTES;
    float minutes = sumatory % SECONDSTOMINUTES;
    sumatory = (int) sumatory / SECONDSTOMINUTES;
    float hours = sumatory % MINUTESTOHOURS;
    sumatory = (int) sumatory / MINUTESTOHOURS;
    float days = sumatory % HOURSTODAYS;
    System.out.print("Days: " + days + " Hours: " + hours + " Minutes : "
            + minutes + " Seconds : " + seconds + "\n");
  }


  public void print() {
    for (int i = 0; i < intervals.size(); i++) {
      System.out.println("interval " + i + ":                   "
              + "     " + intervals.get(i).getInitialDate() + "   "
          + intervals.get(i).getFinalDate() + "                                   "
              + intervals.get(i).getIntervalTime()
          + " \n");
    }
    System.out.println("activity :    " + name + "       "
            + getInitialDate() + "    " + getFinalDate()
        + "                                  " + getTime() + "\n");
  }

  public void showTree(int depth) {
    char aux = '+';
    if (depth % 2 == 0) {
      aux = '-';
    }
    for (int a = 0; a < depth; a++) {
      System.out.print(aux);
    }
    System.out.print(name + ", " + calculateTime() + "\n");

  }

  public long getTime() {
    return calculateTime();
  }

  public JSONObject toJson() {
    JSONObject result = new JSONObject();
    result.put("Name", name);
    result.put("class", "Task");

    JSONArray aux = new JSONArray();

    for (Interval interval : intervals) {
      aux.put(interval.toJson());
    }

    result.put("activities", aux);

    return result;
  }

  public LocalDateTime getInitialDate() {
    if (intervals.size() == 0) {
      return null;
    }
    return intervals.get(0).getInitialDate();
  }

  public LocalDateTime getFinalDate() {
    if (intervals.size() == 0) {
      return null;
    }
    return intervals.get(intervals.size() - 1).getFinalDate();
  }

  public LinkedList<Node> accept(Visitor v) {
    LinkedList<Node> result = new LinkedList<Node>();

    Node aux = v.visitNode(this);
    if (aux.name != "null") {
      result.add(aux);
    }
    return result;
  }
}

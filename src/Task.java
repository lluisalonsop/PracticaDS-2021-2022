
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.logging.Level;

public class Task extends Node {
  private final List<Interval> intervals;

  public Task(String name, int id) {
    super(name, id);
    intervals = new ArrayList<>();
  }

  public Task(String name, int id, LinkedList<String> tags) {
    super(name, id, tags);
    intervals = new ArrayList<>();
  }

  public Task(String name, int id, List<Interval> intervalsnew) {
    super(name, id);
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
        intervals.add(new Interval(intervals.size()));
      }
    } else {
      intervals.add(new Interval(intervals.size()));
    }
  }

  public void print() {
    for (int i = 0; i < intervals.size(); i++) {
      LOGGER_F1.log(Level.INFO,
          ("interval " + i + ":                   " + "     " + intervals.get(i).getInitialDate() + "   "
              + intervals.get(i).getFinalDate() + "                                   "
              + intervals.get(i).getIntervalTime() + " \n"));
    }
    LOGGER_F1.log(Level.INFO, ("activity :    " + name + "       " + getInitialDate() + "    " + getFinalDate()
        + "                                  " + getTime() + "\n"));
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

  public JSONObject toJson(int depth) {
    JSONObject result = new JSONObject();
    result.put("name", name);
    result.put("id", id);
    result.put("class", "task");
    result.put("duration", getTime());

    try {
      result.put("active", intervals.get(intervals.size() - 1).getStatus());
    } catch (Exception e) {
      result.put("active", false);
    }

    String tagsresult = "[";
    if (!tags.isEmpty()) {
      tagsresult += '"' + tags.get(0) + '"';
      for (int i = 1; i < tags.size(); i++) {
        tagsresult += ",";
        tagsresult += '"' + tags.get(i) + '"';
      }
    }
    tagsresult += "]";

    result.put("tags", tagsresult);

    JSONArray aux = new JSONArray();

    for (Interval interval : intervals) {
      aux.put(interval.toJson());
    }

    result.put("intervals", aux);

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

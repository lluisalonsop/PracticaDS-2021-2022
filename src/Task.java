import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import org.json.*;

public class Task extends Node {
    private List<Interval> intervals;

    public Task(String name) {
        super(name);
        intervals = new ArrayList<Interval>();
    }

    public Task(String name, List<Interval> Intervals) {
        super(name);
        intervals = Intervals;
    }

    @Override

    protected long calculateTime() {
        long sumatory = 0;
        for (int i = 0; i < intervals.size(); i++) {
            sumatory += intervals.get(i).getIntervalTime();
        }
        return sumatory;
    }

    public void changeStatus() {
        if (intervals.size() != 0) {
            if (intervals.get(intervals.size() - 1).getStatus() == true) {
                intervals.get(intervals.size() - 1).end();
            } else {
                intervals.add(new Interval());
            }
        } else {
            intervals.add(new Interval());
        }
    }

    private void converseTimeandPrint(long sumatory) {
        float seconds = sumatory % 60;
        sumatory = (int) sumatory / 60;
        float minutes = sumatory % 60;
        sumatory = (int) sumatory / 60;
        float hours = sumatory % 60;
        sumatory = (int) sumatory / 60;
        float days = sumatory % 24;
        System.out
                .print("Days: " + days + " Hours: " + hours + " Minutes : " + minutes + " Seconds : " + seconds + "\n");
    }

    public void print() {
        System.out.println("\nPRINTING : " + Name + "\n");
        for (int i = 0; i < intervals.size(); i++) {
            System.out.println("Interval " + (i + 1) + ": ");
            intervals.get(i).printInterval();
        }
        long sumatory = calculateTime();
        System.out.print("TOTAL TIME OF TASK " + Name + " : ");
        converseTimeandPrint(sumatory);
    }

    public void showTree(int depth) {
        char aux = '+';
        if (depth % 2 == 0) {
            aux = '-';
        }
        for (int a = 0; a < depth; a++) {
            System.out.print(aux);
        }

        System.out.print(Name + ", " + calculateTime() + "\n");

    }

    public long getTime() {
        return calculateTime();
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("Name", Name);
        result.put("class", "Task");

        JSONArray aux = new JSONArray();

        for (int i = 0; i < intervals.size(); i++) {
            aux.put(intervals.get(i).toJson());
        }

        result.put("activities", aux);

        return result;
    }
}

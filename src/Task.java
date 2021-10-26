import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Task extends Node {
    private List<Interval> intervals;

    public Task(String name) {
        super(name);
        intervals = new ArrayList<Interval>();
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

    public void print() {
        System.out.println("\nPRINTING : " + Name + "\n");
        for (int i = 0; i < intervals.size(); i++) {
            System.out.println("Interval " + (i + 1) + ": ");
            intervals.get(i).printInterval();
        }
        long sumatory = calculateTime();
        System.out.print("TOTAL TIME OF TASK " + Name + " in seconds: " + sumatory + "\n");
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
}

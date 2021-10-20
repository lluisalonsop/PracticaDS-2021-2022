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

    public long getTime() {
        long aux = 0;
        for (int i = 0; i < intervals.size(); i++) {
            aux += intervals[i].getTime();
        }
    }

    public void play() {
        intervals.add(new Interval());
    }

    public Interval getCurrentInterval() {
        return intervals.get(intervals.size() - 1);
    }

    public void print() {
        System.out.println("Task : " + Name + " ");
        for (int i = 0; i < intervals.size(); i++) {
            System.out.println("Interval " + (i + 1) + ": ");
            intervals.get(i).printInterval();
        }
    }

    public void showTree(int depth) {
        char aux = '+';
        if (depth % 2 == 0) {
            aux = '-';
        }
        for (int a = 0; a < depth; a++) {
            System.out.print(aux);
        }

        System.out.print(Name + ", " + getTime() + "\n");

    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
public class Task {
    private String Taskname;
    private List<Interval> intervals;

    public Task(String name){
        intervals = new ArrayList<Interval>();
        Taskname = name;
    }

    public void play(){
        intervals.add(new Interval());
    }

    public Interval getCurrentInterval(){
        return intervals.get(intervals.size()-1);
    }

    public void print(){
        System.out.println("Tasca : " + Taskname + " ");
        for (int i =0; i < intervals.size();i++){
            System.out.println("Interval " + ( i + 1 ) + ": ");
            intervals.get(i).printInterval();
        }
    }
}

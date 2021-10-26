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

    public void changeStatus(){
        if (intervals.size() != 0) {
            if (intervals.get(intervals.size() - 1).getStatus() == true ) {
                intervals.get(intervals.size()-1).end();
            }
            else{
                intervals.add(new Interval());
            }
        }
        else {
            intervals.add(new Interval());
        }
    }

    public void print(){
        System.out.println("\nPRINTING : " + Taskname + "\n");
        long sumatory = 0;
        for (int i =0; i < intervals.size();i++){
            sumatory += intervals.get(i).getIntervalTime();
            System.out.println("Interval " + ( i + 1 ) + ": ");
            intervals.get(i).printInterval();
        }
        System.out.print("TOTAL TIME OF TASK " + Taskname + " in seconds: "+ sumatory +"\n");
    }
}

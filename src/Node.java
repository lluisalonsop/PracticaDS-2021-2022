import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.json.*;

public abstract class Node {
    protected String Name;

    public Node(String name) {
        this.Name = name;
    }

    public long getTime() {
        return 0;
    }

    public void addNode(Node name) {
        System.out.print("This is a Task, not a Project!");
    }

    public void print() {
    }
    public void printALLDOWN(){

    }

    public void showTree(int depth) {
    }
    protected LocalDateTime getInitialDate(){
        LocalDateTime result = LocalDateTime.now();
        return result;
    }
    protected LocalDateTime getFinalDate(){
        LocalDateTime result = LocalDateTime.now();
        return result;
    }
    public String getName() {
        return this.Name;
    }

    public void changeStatus() {
        System.out.print("This is a Project, not a Task!");
    }

    protected long calculateTime() {
        return 0;
    }

    public JSONObject toJson() {
        return new JSONObject();
    }

}

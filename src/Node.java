import java.util.LinkedList;
import java.util.List;
import org.json.*;

public abstract class Node {
    protected String Name;

    public Node(String name) {
        this.Name = name;
    }

    public long getTime() {
        System.out.print("This is a Project, not a Task!");
        return 0;
    }

    public void addNode(Node name) {
        System.out.print("This is a Task, not a Project!");
    }

    public void print() {
        System.out.print("This is a Project, not a Task!");
    }

    public void showTree(int depth) {
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

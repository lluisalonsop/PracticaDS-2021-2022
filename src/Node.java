import java.time.LocalDateTime;
import org.json.*;
import java.util.LinkedList;

import java.util.logging.Logger;

/*
The class Node, collects all the functions from
projects and Tasks, and implement the Composite
Pattern
*/
public abstract class Node {
    protected String Name;
    protected final static Logger LOGGER = Logger.getLogger("time_tracker");
    protected LinkedList<String> tags = new LinkedList<String>();

    public Node(String name) {
        this.Name = name;
    }

    public Node(String name, LinkedList<String> tags) {
        this.Name = name;
        this.tags = tags;
    }

    public long getTime() {
        return 0;
    }

    public void addNode(Node name) {
        System.out.print("This is a Task, not a Project!");
    }

    public void print() {
    }

    public void printALLDOWN() {

    }

    public void showTree(int depth) {
    }

    protected LocalDateTime getInitialDate() {
        LocalDateTime result = LocalDateTime.now();
        return result;
    }

    protected LocalDateTime getFinalDate() {
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

    public LinkedList<Node> accept(Visitor v) {
        return new LinkedList<Node>();
    }

}

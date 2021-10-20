import java.util.LinkedList;
import java.util.List;

public abstract class Node {
    protected String Name;

    public Node(String name) {
        this.Name = name;
    }

    public long getTime() {
        System.out.print("This is a Project, not a Task!");
        return 0;
    }

    public void addProject(String name) {
        System.out.print("This is a Task, not a Project!");
    }

    public void stopTask(String name) {
        System.out.print("This is a Project, not a Task!");
    }

    public void addTask(String name) {
        System.out.print("This is a Task, not a Project!");
    }

    public Interval getCurrentInterval() {
        System.out.print("This is a Project, not a Task!");
        return new Interval();
    }

    public void print() {
        System.out.print("This is a Project, not a Task!");
    }

    public String getName() {
        return Name;
    }

    public void showTree(int depth) {
    }

    public void play() {
        System.out.print("This is a Project, not a Task!");
    }

    public boolean isEqual(String name) {
        return this.Name == name;
    }

}

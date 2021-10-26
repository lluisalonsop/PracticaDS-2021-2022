import java.util.ArrayList;
import java.util.LinkedList;
import org.json.*;

public class Project extends Node {

    protected LinkedList<Node> children = new LinkedList<Node>();

    public Project(String name) {
        super(name);
    }

    @Override

    public void addNode(Node aux) {// Arreglar
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).getName() == aux.getName()) {
                if (aux instanceof Project) {
                    System.out.print("This Project already exists!");
                } else {
                    System.out.print("This Task already exists!");
                }

                return;
            }
        }
        children.add(aux);
    }

    public void showTree(int depth) {
        char aux = '+';
        if (depth % 2 == 0) {
            aux = '-';
        }
        for (int a = 0; a < depth; a++) {
            System.out.print(aux);
        }

        System.out.print(Name + "\n");

        for (int i = 0; i < children.size(); i++) {
            children.get(i).showTree(depth + 1);
        }
    }

    public long getTime() {
        int tiempo = 0;

        for (int i = 0; i < children.size(); i++) {
            tiempo += children.get(i).getTime();
        }
        return tiempo;
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("Name", Name);
        result.put("class", "Project");

        JSONArray aux = new JSONArray();

        for (int i = 0; i < children.size(); i++) {
            aux.put(children.get(i).toJson());
        }

        result.put("activities", aux);

        return result;
    }
}

import java.time.LocalDateTime;
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
    public LocalDateTime getInitialDate(){
        ArrayList <LocalDateTime > datesTimes = new ArrayList<LocalDateTime>();
        for (int i = 0 ; i < children.size(); i++){
            if (children.get(i).getInitialDate() != null ){
                datesTimes.add(children.get(i).getInitialDate());
            }
        }
        if (datesTimes.size() == 0){
            return null;
        }
        else{
            LocalDateTime answer = datesTimes.get(0);
            for (int i = 1; i < datesTimes.size();i++){
                int rest = answer.compareTo(datesTimes.get(i));
                if (rest > 0){
                    answer = datesTimes.get(i);
                }
            }
            return answer;
        }
    }
    public LocalDateTime getFinalDate(){
        ArrayList <LocalDateTime > datesTimes = new ArrayList<LocalDateTime>();
        for (int i = 0 ; i < children.size(); i++){
            if (children.get(i).getFinalDate() != null ){
                datesTimes.add(children.get(i).getFinalDate());
            }
        }
        if (datesTimes.size() == 0){
            return null;
        }
        else{
            LocalDateTime  answer = datesTimes.get(0);
            for (int i = 1; i < datesTimes.size();i++){
                int rest = answer.compareTo(datesTimes.get(i));
                if (rest > 0){
                    answer = datesTimes.get(i);
                }
            }
            return answer;
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
    public void printALLDOWN(){
        for (int i = 0; i < children.size(); i++){
            children.get(i).print();
        }
        print();
    }
    public void print(){
            long time = getTime();
            System.out.println("activity :    " + getName() +"                "+ getInitialDate() + "      " + getFinalDate() + "                               " + time);
    }
}

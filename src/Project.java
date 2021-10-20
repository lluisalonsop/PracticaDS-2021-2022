import java.util.ArrayList;
import java.util.LinkedList;

public class Project extends Node {

    protected LinkedList<Node> children = new LinkedList<Node>();
        
    public Project(String name) {
        super(name);
    }

    @Override

    public void addProject(String name) {
        for(int i=0; i<children.size();i++){
            if(children.get(i).isEqual(name)){
                System.out.print("This Project already exists!");
                return;
            }
        }
        Project aux = new Project(name);
        children.add(aux);
    }

    public void addTask(String name) {
        for(int i=0; i<children.size();i++){
            if(children.get(i).isEqual(name)){
                System.out.print("This Task already exists!");
                return;
            }
        }
        Task aux = new Task(name);
        children.add(aux);
    }

    public void showTree(int depth){
        char aux='+';
        if (depth%2==0){
            aux='-';
        }
        for (int a=0; a<depth; a++){
            System.out.print(aux);
        }
        
        System.out.print(Name + "\n");

        for(int i=0; i<=children.size();i++) {
            children.get(i).showTree(depth);
        }
    }
}

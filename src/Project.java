import java.util.ArrayList;
import java.util.LinkedList;

public class Project extends Node {

    protected LinkedList<Node> children = new LinkedList<Node>();
        
    public Project(String name) {
        super(name);
    }

    @Override

    public void addProject(Project aux) {
        for(int i=0; i<children.size();i++){
            if(children.get(i).getName() == aux.getName()){
                System.out.print("This Project already exists!");
                return;
            }
        }
        children.add(aux);
    }

    public void addTask(Task aux) {
        for(int i=0; i<children.size();i++){
            if(children.get(i).getName() == aux.getName()){
                System.out.print("This Task already exists!");
                return;
            }
        }
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

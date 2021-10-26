public class client {

    public Project createTree(){
        Project p1 = new Project("P1");
        Project p2 = new Project("P2");
        Project p3 = new Project("P2");
        Task t1 = new Task("t2");
        Task t2 = new Task("t2");

        p1.addProject(p2);
        p1.addTask(t1);
        p2.addProject(p3);
        p3.addTask(t2);

        return p1;
    }

    public void main(String[] args) {
        
        Project root = createTree();
        root.showTree(1);

    }
}




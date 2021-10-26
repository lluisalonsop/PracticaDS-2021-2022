public class Main {

    public Project createTree() {
        Project p1 = new Project("P1");
        Project p2 = new Project("P2");
        Project p3 = new Project("P2");
        Task t1 = new Task("t2");
        Task t2 = new Task("t2");

        p1.addNode(p2);
        p1.addNode(t1);
        p2.addNode(p3);
        p3.addNode(t2);

        return p1;
    }

    public static void main(String[] args) {
        Timer.getInstance();
        System.out.println("HOLA");
        Task tasca1 = new Task("Task1");
        Task tasca2 = new Task("Task2");
        tasca1.changeStatus(); // cuidao
        tasca1.print();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }
        tasca1.changeStatus();
        tasca2.changeStatus();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }
        tasca1.print();
        tasca1.changeStatus();
        tasca2.print();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }
        // tasca1.print();
        // tasca2.print();

        Project p1 = new Project("P1");
        Project p2 = new Project("P2");
        Project p3 = new Project("P3");

        p1.addNode(p2);
        p1.addNode(tasca1);
        p2.addNode(p3);
        p3.addNode(tasca2);

        p1.showTree(1);
        System.out.println(tasca2.getTime());
    }
}

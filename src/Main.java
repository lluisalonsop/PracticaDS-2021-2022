public class Main {
    public static void main(String[] args){
        Timer.getInstance();
        System.out.println("HOLA");
        Task tasca1 = new Task("Task1");
        Task tasca2 = new Task( "Task2");
        tasca1.changeStatus();                              //cuidao
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
        tasca1.print();
        tasca2.print();
    }
}

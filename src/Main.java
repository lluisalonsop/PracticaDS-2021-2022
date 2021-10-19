public class Main {
    public static void main(String[] args){
        Timer clock = new Timer();
        Task tasca1 = new Task("Task1");
        Task tasca2 = new Task( "Task2");
        tasca1.play();                                  //cuidao
        clock.addObserver(tasca1.getCurrentInterval());
        clock.tick();
        tasca1.print();

        clock.deleteObserver(tasca1.getCurrentInterval());
        tasca1.play();
        clock.addObserver(tasca1.getCurrentInterval());

        tasca2.play();
        clock.addObserver(tasca2.getCurrentInterval());

        while (true){
            clock.tick();
            tasca1.print();
            tasca2.print();
        }
    }
}

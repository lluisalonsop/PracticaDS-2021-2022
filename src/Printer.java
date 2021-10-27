import java.time.LocalDateTime;
/*
* Printer is a runnable class thet is in charge of printing information about the tree
* */
public class Printer implements Runnable{
    private final Node rootPrj;

    public Printer(Node root) {
        System.out.println("                                        initial date                             final date                                  duration \n");
        rootPrj = root;
        Thread printThread = new Thread(this);
        printThread.start();
    }
    @Override
    public void run() {
        while(true) {
            rootPrj.printALLDOWN();
            try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }
    }
    }
}

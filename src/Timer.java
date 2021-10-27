import java.time.LocalDateTime;
import java.util.Observable;
import java.time.LocalTime;

/*
Timer is inizialized through getInstance() and is a singleton,
when whis occurs the method run() is called in a thread and
runs for the rest of the execution
*/
public class Timer extends Observable implements Runnable{
    private static Timer instance;
    private Timer(){
        Thread Timerthread = new Thread(this);
        Timerthread.start();
    }
    @Override
    //This method will be executed by the Thread, simulating a clock
    public void run() {
        while(true) {
            LocalDateTime CurrentDate = LocalDateTime.now();
            setChanged();
            notifyObservers(CurrentDate);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Error Occurred.");
            }
        }
    }
    public static Timer getInstance(){
        if (instance == null){
            instance = new Timer();
        }
        return instance;
    }
}

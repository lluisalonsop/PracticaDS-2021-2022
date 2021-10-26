import java.time.LocalDateTime;
import java.util.Observable;
import java.time.LocalTime;
public class Timer extends Observable implements Runnable{
    private static Timer instance;
    private Timer(){
        Thread Timerthread = new Thread(this);
        Timerthread.start();
    }
    @Override
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

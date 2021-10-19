import java.time.LocalDateTime;
import java.util.Observable;
import java.time.LocalTime;
public class Timer extends Observable {
    void tick() {
        LocalDateTime CurrentDate = LocalDateTime.now();
        setChanged();
        notifyObservers(CurrentDate);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }
    }
}

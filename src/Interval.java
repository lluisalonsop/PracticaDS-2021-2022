import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import org.json.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.*;

// To end the interval we simply have to un subscribe from the observer

class Interval implements Observer {
    private boolean Working;
    private LocalDateTime InitialDate; // Revisar llibreria per format de les dates.
    private LocalDateTime EndDate;
    private long IntervalTime;

    public Interval() {
        Working = true;
        InitialDate = LocalDateTime.now();
        Timer.getInstance().addObserver(this);
    }

    public Interval(boolean Working, String InitialDate, String EndDate) {
        this.Working = Working;
        this.InitialDate = LocalDateTime.parse(InitialDate);
        this.EndDate = LocalDateTime.parse(EndDate);
        calculateTime();
    }

    public void update(Observable obj, Object arg) {
        EndDate = (LocalDateTime) arg; // The end Date of the interval doesn't mean that it has finished yet.
        calculateTime();
    }

    public void end() {
        Working = false;
        Timer.getInstance().deleteObserver(this);
    }

    public boolean getStatus() {
        return Working;
    }
    public long getIntervalTime() {
        return IntervalTime;
    }
    public LocalDateTime getInitialDate() {
        return InitialDate;
    }
    public LocalDateTime getFinalDate() {
        return EndDate;
    }

    private void calculateTime() {
        IntervalTime = InitialDate.until(EndDate, ChronoUnit.SECONDS); // Calculates the difference between the initial
                                                                       // and end
    }

    public void printInterval() {
        System.out.println("Initial date : " + InitialDate + " ");
        if (Working == true) {
            System.out.println("Current date : " + EndDate + " ");
        } else {
            System.out.println("End date : " + EndDate + " ");
        }
        System.out.println("Total time : " + IntervalTime + " \n");
    }

    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("Working", Working);
        result.put("InitialDate", InitialDate);
        result.put("EndDate", EndDate);
        return result;
    }
}

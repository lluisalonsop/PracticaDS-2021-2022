import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.*;

// To end the interval we simply have to un subscribe from the observer

class Interval implements Observer{

    private LocalDateTime InitialDate;  //Revisar llibreria per format de les dates.
    private LocalDateTime EndDate;
    private long IntervalTime;

    public Interval(){
        InitialDate = LocalDateTime.now();
    }
    public void update(Observable obj, Object arg){
        EndDate = (LocalDateTime) arg; // The end Date of the interval doesn't mean that it has finished yet.
        calculateTime();
    }


    public LocalDateTime getInitialDate(){
        return InitialDate;
    }
    public LocalDateTime getFinalDate(){
        return EndDate;
    }

    private void calculateTime(){
        IntervalTime = InitialDate.until(EndDate,ChronoUnit.SECONDS); // Calculates the difference between the initial and en
    }
    public void printInterval(){
        System.out.println("Initial date : " + InitialDate + " ");
        System.out.println("End date :" + EndDate + " ");
        System.out.println("Total time : "+ IntervalTime + " \n");
    }
}

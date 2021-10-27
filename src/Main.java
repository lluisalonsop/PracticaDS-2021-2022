
import org.json.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Timer.getInstance();


        // ---Aqui deberia ir el import desde JSON-----
        Project Master = new Project("Master");
        Project Lists = new Project("Lists");
        Task transportation = new Task("transportation");
        Task firstList = new Task("first list");
        Task secondList = new Task("second list");

        Lists.addNode(firstList);
        Lists.addNode(secondList);
        Master.addNode(transportation);
        Master.addNode(Lists);
        // -------------------------------------------

        Json data = new Json("./Data/Initial.json"); 
        Node root = data.fromJson(); // imported from JSON
        data.saveJson(root.toJson()); //Save to JSON
        
        new Printer(Master);
        System.out.println("start test");
        System.out.println("Transportation Starts");
        transportation.changeStatus(); // START

        // 4 Second Sleep
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }

        System.out.println("Transportation Stops");
        transportation.changeStatus(); // STOP

        // 2 Second Sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }

        System.out.println("First List Starts");
        firstList.changeStatus(); // START

        // 6 Second Sleep
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }

        System.out.println("Second List Starts");
        secondList.changeStatus(); // START

        // 4 Second Sleep
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }

        System.out.println("First list Stops");
        firstList.changeStatus(); // STOP

        // 2 Second Sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }

        System.out.println("Second list Stops");
        secondList.changeStatus(); // STOP

        // 2 Second Sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }

        System.out.println("Transportation Starts");
        transportation.changeStatus(); // START

        // 4 Second Sleep
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Error Occurred.");
        }

        System.out.println("Transportation Stops");
        //transportation.changeStatus(); // STOP

        // ------PRINTS------
        // Deberia ser un print desde el root que es importado del JSON
        //transportation.print();
        //firstList.print();
        //secondList.print();
        // -------------------

        root.showTree(1); // no muestra el de la ejecuccion actual sino de la de guardadoo del JSON
    }

}

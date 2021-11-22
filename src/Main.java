
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;
import java.util.LinkedList;

public class Main {

    private final static Logger LOGGER = Logger.getLogger("time_tracker");

    private static void initHandlers() {
        try {
            Handler fileHandler = new FileHandler("./out/production/timetracker/logback.xml", false);
            fileHandler.setLevel(Level.ALL);
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            LOGGER.addHandler(fileHandler);
            LOGGER.addHandler(consoleHandler);
            LOGGER.log(Level.INFO, "Logger initialized");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error initializing Logger");
        }
    }

    private static Node createFromJson(JSONObject jsonObject) {

        if (jsonObject.get("class").toString().equals("Project")) {
            Project result = new Project(jsonObject.getString("Name"));
            JSONArray jsonArray = jsonObject.getJSONArray("activities");
            for (int i = 0; i < jsonArray.length(); i++) {
                result.addNode(createFromJson(jsonArray.getJSONObject(i)));
            }
            return result;
        }

        if (jsonObject.get("class").toString().equals("Task")) {

            List<Interval> intervals = new ArrayList<Interval>();

            JSONArray jsonArray = jsonObject.getJSONArray("activities");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject aux = jsonArray.getJSONObject(i);
                intervals.add(new Interval(aux.getBoolean("Working"), aux.getString("InitialDate"),
                        aux.getString("EndDate")));
            }

            Task result = new Task(jsonObject.getString("Name"), intervals);

            return result;

        }

        return new Project("");
    }

    private static Node fromJson(String route) {
        try {
            Reader fileReader = new FileReader(route);
            char[] destination = new char[102400];
            fileReader.read(destination, 0, destination.length);

            String object = new String(destination);

            JSONObject jsonobj = new JSONObject(object);
            Node root = createFromJson(jsonobj);
            fileReader.close();
            return root;

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error JSON file not found");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error translating data to JSON");
        }

        return new Project("");
    }

    public static void main(String[] args) {
        initHandlers();

        Timer.getInstance();

        // Cambiar system.outs a logger.log

        // ---Aqui deberia ir el import desde JSON-----
        LinkedList<String> tags = new LinkedList<String>();
        tags.add("Software");
        tags.add("Hardware");

        Project Master = new Project("Master");
        Project Lists = new Project("Lists");
        Task transportation = new Task("transportation");
        Task firstList = new Task("first list");
        Task secondList = new Task("second list", tags);

        Lists.addNode(firstList);
        Lists.addNode(secondList);
        Master.addNode(transportation);
        Master.addNode(Lists);
        // -------------------------------------------

        SearchByTagVisitor v = new SearchByTagVisitor("Software");
        LinkedList<Node> result = Master.accept(v);

        if (result.getLast().Name == secondList.Name) {
            LOGGER.log(Level.INFO, "Search by tag is working!");
        }

        Node root = fromJson("./Data/Initial.json"); // imported from JSON
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
        // transportation.changeStatus(); // STOP

        // ------PRINTS------
        // Deberia ser un print desde el root que es importado del JSON
        // transportation.print();
        // firstList.print();
        // secondList.print();
        // -------------------

        root.showTree(1); // no muestra el de la ejecuccion actual sino de la de guardadoo del JSON
    }

}

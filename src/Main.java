import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
  private static final Logger LOGGER = Logger.getLogger("time_tracker");

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
      List<Interval> intervals = new ArrayList<>();
      JSONArray jsonArray = jsonObject.getJSONArray("activities");
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject aux = jsonArray.getJSONObject(i);
        intervals.add(new Interval(aux.getBoolean("Working"), aux.getString("InitialDate"),
                        aux.getString("EndDate")));
      }
      return new Task(jsonObject.getString("Name"), intervals);
    }
    return new Project("");
  }

  private static Node fromJson() {
    try {
      Reader fileReader = new FileReader("./Data/Initial.json");
      char[] destination = new char[102400];
      int val = fileReader.read(destination, 0, destination.length);
      if (val == -1) {
        //COMPTE AMB AIXÓ SI CAMBIA
        return new Project("null");
      }
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

    LinkedList<String> tags = new LinkedList<>();
    tags.add("Software");
    tags.add("Hardware");
    Project master = new Project("Master");
    Project lists = new Project("Lists");
    Task transportation = new Task("transportation");
    Task firstList = new Task("first list");
    Task secondList = new Task("second list", tags);

    lists.addNode(firstList);
    lists.addNode(secondList);
    master.addNode(transportation);
    master.addNode(lists);
    // -------------------------------------------

    SearchByTagVisitor v = new SearchByTagVisitor("Software");
    LinkedList<Node> result = master.accept(v);

    if (Objects.equals(result.getLast().name, secondList.name)) {
      LOGGER.log(Level.INFO, "Search by tag is working!");
    }

    final Node root = fromJson(); // imported from JSON
    new Printer(master);
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

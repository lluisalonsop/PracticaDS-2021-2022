import java.io.IOException;

import java.util.LinkedList;

import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
  private static final Logger LOGGER_F1 = Logger.getLogger("LOGGER_F1");
  private static final Logger LOGGER_F2 = Logger.getLogger("LOGGER_F2");

  private static void initHandlers_F1() {
    try {
      Handler fileHandler = new FileHandler("./out/production/timetracker/logback_F1.xml", false);
      fileHandler.setLevel(Level.ALL);
      Handler consoleHandler = new ConsoleHandler();
      consoleHandler.setLevel(Level.ALL);
      LOGGER_F1.addHandler(fileHandler);
      LOGGER_F1.addHandler(consoleHandler);
      LOGGER_F1.log(Level.INFO, "Logger initialized");

    } catch (IOException e) {
      LOGGER_F1.log(Level.SEVERE, "Error initializing Logger");
    }
  }

  private static void initHandlers_F2() {
    try {
      Handler fileHandler = new FileHandler("./out/production/timetracker/logback_F2.xml", false);
      fileHandler.setLevel(Level.ALL);
      Handler consoleHandler = new ConsoleHandler();
      consoleHandler.setLevel(Level.ALL);
      LOGGER_F2.addHandler(fileHandler);
      LOGGER_F2.addHandler(consoleHandler);
      LOGGER_F2.log(Level.INFO, "Logger initialized");

    } catch (IOException e) {
      LOGGER_F2.log(Level.SEVERE, "Error initializing Logger");
    }
  }

  public static void main(String[] args) {
    initHandlers_F1();
    initHandlers_F2();
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

    new PrinterVisitor(master);

    if (Objects.equals(result.getLast().name, secondList.name)) {
      LOGGER_F2.log(Level.INFO, "Search by tag is working!");
    } else {
      LOGGER_F2.log(Level.WARNING, "Search by tag isn't working!");
    }

    Json data = new Json("./Data/Initial.json");
    Node root = data.fromJson(); // imported from JSON

    LOGGER_F1.log(Level.INFO, "Start Test");
    LOGGER_F1.log(Level.INFO, "Transportation Starts");
    transportation.changeStatus(); // START
    // 4 Second Sleep
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      LOGGER_F1.log(Level.SEVERE, "4 second sleep suffered an interruption");
    }

    LOGGER_F1.log(Level.INFO, "Transportation Stops");
    transportation.changeStatus(); // STOP
    // 2 Second Sleep
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      LOGGER_F1.log(Level.SEVERE, "2 second sleep suffered an interruption");
    }

    LOGGER_F1.log(Level.INFO, "First List Start");
    firstList.changeStatus(); // START
    // 6 Second Sleep
    try {
      Thread.sleep(6000);
    } catch (InterruptedException e) {
      LOGGER_F1.log(Level.SEVERE, "6 second sleep suffered an interruption");
    }

    LOGGER_F1.log(Level.INFO, "Second List Starts");
    secondList.changeStatus(); // START
    // 4 Second Sleep
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      LOGGER_F1.log(Level.SEVERE, "4 second sleep suffered an interruption");
    }

    LOGGER_F1.log(Level.INFO, "First List Stops");
    firstList.changeStatus(); // STOP
    // 2 Second Sleep
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      LOGGER_F1.log(Level.SEVERE, "2 second sleep suffered an interruption");
    }
    LOGGER_F1.log(Level.INFO, "Second list Stops");
    secondList.changeStatus(); // STOP
    // 2 Second Sleep
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      LOGGER_F1.log(Level.SEVERE, "2 second sleep suffered an interruption");
    }

    LOGGER_F1.log(Level.INFO, "Transportation Starts");
    transportation.changeStatus(); // START

    // 4 Second Sleep
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      LOGGER_F1.log(Level.SEVERE, "4 second sleep suffered an interruption");
    }

    LOGGER_F1.log(Level.INFO, "Transportation Stops");

    data.saveJson(root.toJson()); // Save to JSON

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

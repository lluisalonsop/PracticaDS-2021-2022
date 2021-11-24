import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.LinkedList;
import java.util.Objects;
public class Main {
  private static  Logger logger = null;
  public static void main(String[] args) {
    logger = LoggerFactory.getLogger(Main.class);
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
      logger.info("Search_by_tag_is_working!");
    } else {
      logger.warn("Search_by_tag_isn't_working!");
    }

    Json data = new Json("./Data/Initial.json");
    final Node root = data.fromJson(); // imported from JSON

    logger.info("Start_Test");
    logger.info("Transportation_Starts");
    transportation.changeStatus(); // START
    // 4 Second Sleep
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      logger.warn("4_second_sleep_suffered_an_interruption");
    }

    logger.info("Transportation_Stops");
    transportation.changeStatus(); // STOP
    // 2 Second Sleep
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      logger.warn("2_second_sleep_suffered_an_interruption");
    }

    logger.info("First_List_Start");
    firstList.changeStatus(); // START
    // 6 Second Sleep
    try {
      Thread.sleep(6000);
    } catch (InterruptedException e) {
      logger.warn("6_second_sleep_suffered_an_interruption");
    }

    logger.info("Second_List_Starts");
    secondList.changeStatus(); // START
    // 4 Second Sleep
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      logger.warn("4_second_sleep_suffered_an_interruption");
    }

    logger.info("First_List_Stops");
    firstList.changeStatus(); // STOP
    // 2 Second Sleep
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      logger.info("2_second_sleep_suffered_an_interruption");
    }
    logger.info("Second_list_Stops");
    secondList.changeStatus(); // STOP
    // 2 Second Sleep
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      logger.warn("2_second_sleep_suffered_an_interruption");
    }

    logger.info("Transportation_Starts");
    transportation.changeStatus(); // START

    // 4 Second Sleep
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      logger.warn("4_second_sleep_suffered_an_interruption");
    }

    logger.info("Transportation Stops");

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

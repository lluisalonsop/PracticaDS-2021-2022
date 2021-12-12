import java.util.LinkedList;

public class MainWebServer {
  public static void main(String[] args) {
    webServer();
  }

  public static Node makeTreeCourses() {
    LinkedList<String> tags = new LinkedList<>();
    tags.add("Software");
    tags.add("Hardware");
    Project master = new Project("Master", 0);
    Project lists = new Project("Lists", 1);
    Task transportation = new Task("transportation", 2);
    Task firstList = new Task("first list", 3);
    Task secondList = new Task("second list", 4, tags);

    lists.addNode(firstList);
    lists.addNode(secondList);
    master.addNode(transportation);
    master.addNode(lists);
    return master;
  }

  public static void webServer() {

    final Node root = makeTreeCourses();

    Timer.getInstance();

    new WebServer(root);
  }
}
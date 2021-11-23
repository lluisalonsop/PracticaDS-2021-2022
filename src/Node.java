import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.logging.Logger;
import org.json.JSONObject;
import java.util.logging.Level;

/*
The class Node, collects all the functions from
projects and Tasks, and implement the Composite
Pattern
*/

public abstract class Node {
  protected String name;
  protected static final Logger LOGGER_F1 = Logger.getLogger("LOGGER_F1");
  protected static final Logger LOGGER_F2 = Logger.getLogger("LOGGER_F2");
  protected LinkedList<String> tags = new LinkedList<>();

  public Node(String nameToSet) {
    assert (nameToSet == "") : String.format("El nombre no puede estar vacio");
    this.name = nameToSet;
  }

  public Node(String nameToSet, LinkedList<String> tagsToSet) {
    assert (nameToSet == "") : String.format("El nombre no puede estar vacio");
    this.name = nameToSet;
    this.tags = tagsToSet;
  }

  public long getTime() {
    return 0;
  }

  public void addNode(Node name) {
    assert (name.getName() == "") : String.format("This is a Task, not a Project!");
  }

  public void print() {
  }

  public void showTree(int depth) {
  }

  protected LocalDateTime getInitialDate() {
    return LocalDateTime.now();
  }

  protected LocalDateTime getFinalDate() {
    return LocalDateTime.now();
  }

  public String getName() {
    return this.name;
  }

  public void changeStatus() {
    LOGGER_F1.log(Level.WARNING, "This is a Project, not a Task!");
  }

  protected long calculateTime() {
    LOGGER_F1.log(Level.WARNING, "This is a Project, not a Task!");
    return 0;
  }

  public JSONObject toJson() {
    return new JSONObject();
  }

  public LinkedList<Node> accept(Visitor v) {
    return new LinkedList<>();
  }
}

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

/*
The class Project Initialize the new Project created with
his name, then after a new children of this project appended to
him we save it to the LinkedList of Node called children
*/

public class Project extends Node {
  protected LinkedList<Node> children = new LinkedList<>();

  public Project(String name) {
    super(name);
  }

  /* public Project(String name, LinkedList<String> tags) {
  //  super(name, tags);}
  */
  @Override
  public void addNode(Node aux) {
    for (Node child : children) {
      if (Objects.equals(child.getName(), aux.getName())) {
        if (aux instanceof Project) {
          System.out.print("This Project already exists!");
        } else {
          System.out.print("This Task already exists!");
        }
        return;
      }
    }
    children.add(aux);
  }

  public void showTree(int depth) {
    char aux = '+';
    if (depth % 2 == 0) {
      aux = '-';
    }
    for (int a = 0; a < depth; a++) {
      System.out.print(aux);
    }

    System.out.print(name + "\n");
    for (Node child : children) {
      child.showTree(depth + 1);
    }
  }

  public LocalDateTime getInitialDate() {
    ArrayList<LocalDateTime> datesTimes = new ArrayList<>();
    for (Node child : children) {
      if (child.getInitialDate() != null) {
        datesTimes.add(child.getInitialDate());
      }
    }
    if (datesTimes.size() == 0) {
      return null;
    } else {
      LocalDateTime answer = datesTimes.get(0);
      for (int i = 1; i < datesTimes.size(); i++) {
        int rest = answer.compareTo(datesTimes.get(i));
        if (rest > 0) {
          answer = datesTimes.get(i);
        }
      }
      return answer;
    }
  }

  public LocalDateTime getFinalDate() {
    ArrayList<LocalDateTime> datesTimes = new ArrayList<>();
    for (Node child : children) {
      if (child.getFinalDate() != null) {
        datesTimes.add(child.getFinalDate());
      }
    }
    if (datesTimes.size() == 0) {
      return null;
    } else {
      LocalDateTime answer = datesTimes.get(0);
      for (int i = 1; i < datesTimes.size(); i++) {
        int rest = answer.compareTo(datesTimes.get(i));
        if (rest > 0) {
          answer = datesTimes.get(i);
        }
      }
      return answer;
    }
  }

  public long getTime() {
    int timeCount = 0;
    for (Node child : children) {
      timeCount += child.getTime();
    }
    return timeCount;
  }

  public JSONObject toJson() {
    JSONObject result = new JSONObject();
    result.put("Name", name);
    result.put("class", "Project");

    JSONArray aux = new JSONArray();

    for (Node child : children) {
      aux.put(child.toJson());
    }

    result.put("activities", aux);

    return result;
  }

  @Override
  public void printAllDown() {
    for (Node child : children) {
      child.print();
    }
    print();
  }

  public void print() {
    long time = getTime();
    System.out.println("activity :    " + getName() + "                "
            + getInitialDate() + "      "
            + getFinalDate() + "                               " + time);
  }

  public LinkedList<Node> accept(Visitor v) {
    LinkedList<Node> result = new LinkedList<>();

    for (Node child : children) {
      LinkedList<Node> aux = child.accept(v);
      if (!aux.isEmpty()) {
        result.addAll(aux);
      }
    }

    Node aux = v.visitNode(this);
    if (!Objects.equals(aux.name, "null")) {
      result.add(aux);
    }
    return result;
  }
}

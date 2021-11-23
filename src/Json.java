import org.json.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import java.util.logging.Logger;
import java.util.logging.Level;

/*
This class has three functionalities
1.- Creates a tree from a json object
2.- Opens up a file and returns a tree
3.- Creates a new .JSON file from a string
*/

public class Json {
  private String route;
  protected static final Logger LOGGER_F1 = Logger.getLogger("LOGGER_F1");
  protected static final Logger LOGGER_F2 = Logger.getLogger("LOGGER_F2");

  public Json(String route) {
    this.route = route;
  }

  private Node createFromJson(JSONObject jsonObject) {

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
        intervals.add(new Interval(aux.getBoolean("Working"), aux.getString("InitialDate"), aux.getString("EndDate")));
      }

      Task result = new Task(jsonObject.getString("Name"), intervals);

      return result;

    }

    return new Project("");
  }

  public Node fromJson() {
    try {
      Reader fileReader = new FileReader(this.route);
      char[] destination = new char[102400];
      fileReader.read(destination, 0, destination.length);

      String object = new String(destination);

      JSONObject jsonobj = new JSONObject(object);
      Node root = createFromJson(jsonobj);
      fileReader.close();
      return root;

    } catch (FileNotFoundException e) {
      LOGGER_F1.log(Level.SEVERE, "Error on Json, file not found");
    } catch (IOException e) {
      LOGGER_F1.log(Level.SEVERE, "Error on Json, IOException");
    }

    return new Project("");
  }

  public void saveJson(JSONObject jsonObject) {
    FileWriter file = null;
    try {
      file = new FileWriter(this.route);
      file.write(jsonObject.toString());

    } catch (Exception e) {
      LOGGER_F1.log(Level.SEVERE, "Error on Json");
    } finally {
      try {
        if (file != null) {
          file.flush();
          file.close();
        }
      } catch (IOException e) {
        LOGGER_F1.log(Level.SEVERE, "Error on Json, IOException");
      }
    }
  }

}

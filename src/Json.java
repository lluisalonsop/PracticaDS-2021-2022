import org.json.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Json {
    private String route;
    
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
                intervals.add(new Interval(aux.getBoolean("Working"), aux.getString("InitialDate"),
                        aux.getString("EndDate")));
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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Project("");
    }

    public void saveJson(JSONObject jsonObject) {
        FileWriter file = null;
        try{
            file = new FileWriter("./Data/destination.json");
            file.write(jsonObject.toString());

        } catch (Exception e) {
			e.printStackTrace();
        } finally {
			try {
				if (file != null) {
					file.flush();
					file.close();					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

}

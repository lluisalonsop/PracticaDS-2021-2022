import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.StringTokenizer;
import java.util.LinkedList;
import org.json.JSONArray;

// Based on 
// https://www.ssaurel.com/blog/create-a-simple-http-web-server-in-java
// http://www.jcgonzalez.com/java-socket-mini-server-http-example

public class WebServer {
  private static final int PORT = 8080; // port to listen to

  private Node currentNode;
  private final Node root;

  public WebServer(Node root) {
    this.root = root;
    System.out.println(root);
    currentNode = root;
    try {
      ServerSocket serverConnect = new ServerSocket(PORT);
      System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");
      // we listen until user halts server execution
      while (true) {
        // each client connection will be managed in a dedicated Thread
        new SocketThread(serverConnect.accept());
        // create dedicated thread to manage the client connection
      }
    } catch (IOException e) {
      System.err.println("Server Connection error : " + e.getMessage());
    }
  }

  private Node findNodeById(int id) {
    SearchByIdVisitor v = new SearchByIdVisitor(id);
    LinkedList<Node> result = root.accept(v);
    return result.getLast();
  }

  private class SocketThread extends Thread {
    // SocketThread sees WebServer attributes
    private final Socket insocked;
    // Client Connection via Socket Class

    SocketThread(Socket insocket) {
      this.insocked = insocket;
      this.start();
    }

    @Override
    public void run() {
      // we manage our particular client connection
      BufferedReader in;
      PrintWriter out;
      String resource;

      try {
        // we read characters from the client via input stream on the socket
        in = new BufferedReader(new InputStreamReader(insocked.getInputStream()));
        // we get character output stream to client
        out = new PrintWriter(insocked.getOutputStream());
        // get first line of the request from the client
        String input = in.readLine();
        // we parse the request with a string tokenizer

        System.out.println("sockedthread : " + input);

        StringTokenizer parse = new StringTokenizer(input);
        String method = parse.nextToken().toUpperCase();
        // we get the HTTP method of the client
        if (!method.equals("GET")) {
          System.out.println("501 Not Implemented : " + method + " method.");
        } else {
          // what comes after "localhost:8080"
          resource = parse.nextToken();
          System.out.println("input " + input);
          System.out.println("method " + method);
          System.out.println("resource " + resource);

          parse = new StringTokenizer(resource, "/[?]=&");
          int i = 0;
          String[] tokens = new String[20];
          // more than the actual number of parameters
          while (parse.hasMoreTokens()) {
            tokens[i] = parse.nextToken();
            System.out.println("token " + i + "=" + tokens[i]);
            i++;
          }

          // Make the answer as a JSON string, to be sent to the Javascript client
          String answer = makeHeaderAnswer() + makeBodyAnswer(tokens);
          System.out.println("answer\n" + answer);
          // Here we send the response to the client
          out.println(answer);
          out.flush(); // flush character output stream buffer
        }

        in.close();
        out.close();
        insocked.close(); // we close socket connection
      } catch (Exception e) {
        System.err.println("Exception : " + e);
      }
    }

    private String makeBodyAnswer(String[] tokens) {
      String body = "";
      System.out.println("TOKENS: ");
      System.out.println(tokens[0]);
      switch (tokens[0]) {
        case "get_tree": {
          int id = Integer.parseInt(tokens[1]);
          Node node = findNodeById(id);
          assert (node != null);
          body = node.toJson(1).toString();
          break;
        }
        case "start": {
          int id = Integer.parseInt(tokens[1]);
          Node node = findNodeById(id);
          assert (node != null);
          Task task = (Task) node;
          task.changeStatus();
          body = "{}";
          break;
        }
        case "stop": {
          int id = Integer.parseInt(tokens[1]);
          Node node = findNodeById(id);
          assert (node != null);
          Task task = (Task) node;
          task.changeStatus();
          body = "{}";
          break;
        }
        case "createProject": {
          String name = tokens[1];
          int id = Integer.parseInt(tokens[3]);
          int tagged = Integer.parseInt(tokens[4]);
          LinkedList<String> tags = new LinkedList<>();
          Node node = findNodeById(id);
          System.out.println("Tagged: ");
          System.out.println(tagged);

          if (tagged == 1) {
            String tag = tokens[2];
            tags.add(tag);
            node.addNode(new Project(name, LocalDateTime.now().hashCode(), tags));
          } else {
            node.addNode(new Project(name, LocalDateTime.now().hashCode()));
          }
          System.out.println("Name: ");
          System.out.println(name);
          System.out.println("IDPARENT: ");
          System.out.println(id);
          break;
        }
        case "createTask": {
          String name = tokens[1];
          int id = Integer.parseInt(tokens[3]);
          int tagged = Integer.parseInt(tokens[4]);
          LinkedList<String> tags = new LinkedList<>();
          Node node = findNodeById(id);
          if (tagged == 1) {
            String tag = tokens[2];
            tags.add(tag);
            node.addNode(new Task(name, LocalDateTime.now().hashCode(), tags));
          } else {
            node.addNode(new Task(name, LocalDateTime.now().hashCode()));
          }
          System.out.println("Name: ");
          System.out.println(name);
          System.out.println("IDPARENT: ");
          System.out.println(id);
          break;
        }
        case "update": {
          Node node = findNodeById(Integer.parseInt(tokens[1]));
          JSONArray array = new JSONArray(tokens[3]);
          array.forEach(action);
          LinkedList<String> aux = new LinkedList<String>();
          node.update(tokens[2], aux);
          break;
        }
        // TODO: edit task, project properties
        default:
          assert false;
      }
      System.out.println(body);
      return body;
    }

    private String makeHeaderAnswer() {
      String answer = "";
      answer += "HTTP/1.0 200 OK\r\n";
      answer += "Content-type: application/json\r\n";
      answer += "Access-Control-Allow-Origin: *\r\n";
      answer += "\r\n";
      // blank line between headers and content, veryimportant !
      return answer;
    }
  } // SocketThread

} // WebServer
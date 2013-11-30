import java.io.*;
import java.util.*;

public class GameOfLifeText {
  public static String read_line() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String text = null;
    try {
      text = br.readLine();
    } catch (IOException ioe) {
      System.out.println("IO error trying to read your name!");
      System.exit(1);
    }
    return text;
  }

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Please enter the width: ");
    int width = Integer.parseInt(read_line());

    System.out.println("Please enter the height: ");
    int height = Integer.parseInt(read_line());

    System.out.println("Would you like your edges to wrap? (y/n)");
    String answer = read_line();
    while (answer.equals("y") && answer.equals("n")) {
      System.out.println("Please enter y/n.");
      answer = read_line();
    }
    boolean wrapped_edges;
    if (answer.equals("y")) {
      wrapped_edges = true;
    } else {
      wrapped_edges = false;
    }

    World world = new World(width, height, wrapped_edges);
    world.setup_defaults();
    world.print();

    System.out.println("Choose the squares to activate (e.g. 3,4).");
    String squares_to_make_active = read_line();
    while (!squares_to_make_active.equals("done")) {
      System.out.println(squares_to_make_active);
      int x_position = Integer.parseInt(squares_to_make_active.split(",")[0]);
      int y_position = Integer.parseInt(squares_to_make_active.split(",")[1]);
      if(!world.toggle_square(x_position, y_position)) {
        System.out.println("Invalid square");
      }

      System.out.println("Choose the squares to activate (e.g. 3,4). Type done when you are done.");
      squares_to_make_active = read_line();
    }

    while(true) {
      world.update();
      Thread.sleep(500);
    }
  }
}
import java.util.*;
public class World {
  private int width;
  private int height;
  private boolean wrapped_edges;
  private int iteration;
  private Square[][] squares;

  public World(int width, int height, boolean wrapped_edges) {
    this.width         = width;
    this.height        = height;
    this.wrapped_edges = wrapped_edges;
    this.iteration     = 0;
    this.squares       = new Square[this.width][this.height];

    this.create_squares();
    this.print();
  }

  private void create_squares() {
    for(int i=0; i<width; i++) {
      for(int j=0; j<height; j++) {
        squares[i][j] = new Square(i+1, j+1, false);
      }
    }
  }

  public boolean toggle_square(int x_position, int y_position) {
    if(x_position > 0 && x_position <= width && y_position > 0 && y_position <= height) {
      squares[x_position-1][y_position-1].toggle_alive();
      print();
      return true;
    } else {
      return false;
    }
  }

  public void update() {
    for(int i=0; i<height; i++) {
      for(int j=0; j<width; j++) {
        squares[j][i].calculate_switch(num_neighbors_for(j,i));
      }
    }
    for(int i=0; i<height; i++) {
      for(int j=0; j<width; j++) {
        squares[j][i].update();
      }
    }
    print();
  }

  private int num_neighbors_for(int x_position, int y_position) {
    return value_for(x_position-1, y_position-1) +
           value_for(x_position,   y_position-1) +
           value_for(x_position+1, y_position-1) +
           value_for(x_position-1, y_position)   +
           value_for(x_position+1, y_position)   +
           value_for(x_position-1, y_position+1) +
           value_for(x_position,   y_position+1) +
           value_for(x_position+1, y_position+1);
  }

  private int value_for(int x_position, int y_position) {
    if(wrapped_edges) {
      if(x_position < 0) {
        x_position = width;
      }
      if(x_position == width) {
        x_position = 0;
      }
      if(y_position < 0) {
        y_position = height;
      }
      if(y_position == height) {
        y_position = 0;
      }
      if(squares[x_position][y_position].get_alive()) {
        return 1;
      } else {
        return 0;
      }
    } else {
      if(x_position >= 0 && x_position < width &&
         y_position >= 0 && y_position < height &&
         squares[x_position][y_position].get_alive()) {
        return 1;
      } else {
        return 0;
      }
    }
  }

  public void print() {
    System.out.print("  ");
    for(int i=0; i<width; i++) {
      System.out.format("%2d ", i+1);
    }
    System.out.println();
    for(int i=0; i<height; i++) {
      System.out.format("%2d ", i+1);
      for(int j=0; j<width; j++) {
        squares[j][i].print();
      }
      System.out.println();
    }
  }

  public void setup_defaults() {
    Random generator = new Random(19580427);
    int initial_seed = generator.nextInt(width*height);
    System.out.println(initial_seed);
    for(int i=0; i<initial_seed; i++) {
      squares[generator.nextInt(width)][generator.nextInt(height)].toggle_alive();
    }
    // squares[0][6].toggle_alive();
    // squares[0][7].toggle_alive();
    // squares[1][6].toggle_alive();
    // squares[1][7].toggle_alive();
  }
}
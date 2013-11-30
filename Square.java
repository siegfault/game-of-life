public class Square {
  private int x_position;
  private int y_position;
  private boolean alive;
  private boolean alive_on_update;

  public Square(int x_position, int y_position, boolean alive) {
    this.x_position = x_position;
    this.y_position = y_position;
    this.alive      = alive;
  }

  public void print() {
    String text = "";
    if(alive) {
      text = "*  ";
    } else {
      text = "-  ";
    }
    System.out.print(text);
  }

  public void calculate_switch(int num_neighbors_alive) {
    if(alive) {
      if(num_neighbors_alive < 2 || num_neighbors_alive > 3) {
        alive_on_update = false;
      } else {
        alive_on_update = true;
      }
    } else {
      if(num_neighbors_alive == 3) {
        alive_on_update = true;
      } else {
        alive_on_update = false;
      }
    }
  }

  public void update() {
    alive = alive_on_update;
  }

  public boolean get_alive() {
    return alive;
  }

  public void toggle_alive() {
    this.alive = !this.alive;
  }
}
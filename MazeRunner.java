import java.util.*;

public class MazeRunner {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Maze myMap = new Maze();
    intro(scan, myMap);
  }

  public static void intro(Scanner scan, Maze myMap) {
    System.out.println("Welcome to Maze Runner. This is the maze: ");
    myMap.printMap();
    System.out.println(
        "Your map will look like all \".\" and one \"x\". The \".\"'s may turn into either walls \"-\" or freespace \"*\". There might even be other surprises as well.");
    System.out.println("Which direction would you like to move?");
    String input = scan.nextLine();
    if (input == "R") {
      // indicate move right
    } else if (input == "L") {
      // indicate move left
    } else if (input == "U") {
      // indicate move up
    } else if (input == "D") {
      // indicate move down
    } else {

    }
  }

}
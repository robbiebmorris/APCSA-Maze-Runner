import java.util.*;

public class MazeRunner {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Maze myMap = new Maze();
    intro(myMap);
    // myMap.fillSolution();
    while (!myMap.didIWin()) {
      String nextMove = userMove(myMap, scan);
      if (myMap.isThereAPit(nextMove)) {
        navigatePit(myMap, scan);
      } else if (nextMove.equalsIgnoreCase("R")) {
        myMap.moveRight();
      } else if (nextMove.equalsIgnoreCase("L")) {
        myMap.moveLeft();
      } else if (nextMove.equalsIgnoreCase("U")) {
        myMap.moveUp();
      } else if (nextMove.equalsIgnoreCase("D")) {
        myMap.moveDown();
      }
      myMap.printMap();
    }
    System.out.println("Congratulations, you made it out alive!");
  }

  public static void intro(Maze myMap) {
    System.out.println("Welcome to Maze Runner. This is the maze: ");
    myMap.printMap();
    System.out.println(
        "Your map will look like all \".\" and one \"x\". The \".\"'s may turn into either walls \"-\" or freespace \"*\". There might even be other surprises as well.");
  }

  public static String userMove(Maze myMap, Scanner scan) {
    System.out.println("Which direction would you like to move? ('R', 'L', 'U', 'D')");

    while (true) {
      String input = scan.nextLine().toUpperCase();
      if (input.equals("R") || input.equals("L") || input.equals("U") || input.equals("D")) {
        if (canIMove(input, myMap) || myMap.isThereAPit(input)) {
          return input;
        }
        System.out.println(
            "You cannot move this direction, there is a wall obstructing your path. \n Choose a new direction to move ('R', 'L', 'U', 'D')");
        continue;
      }
      System.out.println("You must input a valid move!");
    }
  }

  public static boolean canIMove(String move, Maze myMap) {
    if (move.equals("R")) {
      return myMap.canIMoveRight();
    } else if (move.equals("L")) {
      return myMap.canIMoveLeft();
    } else if (move.equals("U")) {
      return myMap.canIMoveUp();
    } else if (move.equals("D")) {
      return myMap.canIMoveDown();
    }
    return false;
  }

  public static void navigatePit(Maze myMap, Scanner scan) {
    System.out.println("Watch out! There's a pit ahead. Jump it?");
    String input = scan.nextLine();
    if (input.equals("y")) {

      while (true) {
        System.out.println("Which direction do you want to jump? ('R', 'L', 'U', 'D')");
        String dir = scan.nextLine().toUpperCase();
        if ((dir.equals("R") && myMap.canMove(0, 2)) || (dir.equals("L") && myMap.canMove(0, -2))
            || (dir.equals("U") && myMap.canMove(-2, 0)) || (dir.equals("D") && myMap.canMove(2, 0))) {
          myMap.jumpOverPit(dir);
          break;
        } else {
          System.out.println("You must input a valid move!");
        }
      }
    }
  }
}
import java.util.*;

public class MazeRunner {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Maze myMap = new Maze();

    int moveCounter = 0;

    intro(myMap);
    // myMap.fillSolution();
    while (!myMap.didIWin()) {
      String nextMove = userMove(myMap, scan);
      if (myMap.isThereAPit(nextMove)) {
        navigatePit(myMap, scan, nextMove);
      } else if (nextMove.equalsIgnoreCase("R")) {
        myMap.moveRight();
      } else if (nextMove.equalsIgnoreCase("L")) {
        myMap.moveLeft();
      } else if (nextMove.equalsIgnoreCase("U")) {
        myMap.moveUp();
      } else if (nextMove.equalsIgnoreCase("D")) {
        myMap.moveDown();
      }
      moveCounter++;
      moveMessage(moveCounter);
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

  public static void navigatePit(Maze myMap, Scanner scan, String move) {
    System.out.println("Watch out! There's a pit ahead. Jump it?");
    String input = scan.nextLine();
    if (input.equals("y")) {

      System.out.println("Which direction do you want to jump? ('R', 'L', 'U', 'D')");
      String dir = scan.nextLine().toUpperCase();

      if (dir.equals("R")) {
        if (move.equals("R")) {
          myMap.jumpOverPit(dir);
        } else if (move.equals("D")) {
          myMap.jump(1, 1);
        } else if (move.equals("U")) {
          myMap.jump(-1, 1);
        } // no fourth condition because it ends up being the same location
      }

      else if (dir.equals("L")) {
        if (move.equals("L")) {
          myMap.jumpOverPit(dir);
        } else if (move.equals("D")) {
          myMap.jump(1, -1);
        } else if (move.equals("U")) {
          myMap.jump(-1, -1);
        } // no fourth condition because it ends up being the same location
      }

      else if (dir.equals("D")) {
        if (move.equals("R")) {
          myMap.jump(1, 1);
        } else if (move.equals("L")) {
          myMap.jump(1, -1);
        } else if (move.equals("D")) {
          myMap.jumpOverPit(dir);
        } // no fourth condition because it ends up being the same location
      }

      else if (dir.equals("U")) {
        if (move.equals("R")) {
          myMap.jump(-1, 1);
        } else if (move.equals("L")) {
          myMap.jump(-1, -1);
        } else if (move.equals("U")) {
          myMap.jumpOverPit(dir);
        } // no fourth condition because it ends up being the same location
      }
    }
  }

  public static void moveMessage(int moves) {
    if (moves == 50) {
      System.out.println("50 Moves - Warning! You have made 50 moves, you have 50 remaining before the exit closes");
    } else if (moves == 75) {
      System.out.println("75 Moves - Alert! You have made 75 moves, you only have 25 left to escape");
    } else if (moves == 90) {
      System.out.println("90 Moves - DANGER! You have made 90 moves, you only have 10 moves left to escape");
    } else if (moves == 100) {
      System.out.println("100 Moves - Oh no! You took too long to escape and ran out of moves!");
    }
  }
}
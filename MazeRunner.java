/*
NOTES:

Work distribution:
Robbie:
- Basic maze program
- Move limit
- Pits
- Helping out with pathfinding algorithm, although staying hands-off because I've done this before.

Angela:
- Fixing / adding a few things to the basic program
- Pathfinding algorithm (a*?)
 */

import java.util.*;

public class MazeRunner {
  public static void main(String[] args) {
    // global scanner for the MazeRunner class
    Scanner scan = new Scanner(System.in);
    // initialize map
    Maze myMap = new Maze();

    int moveCounter = 0;
    intro(myMap);

    // continue to loop and ask for moves until the game is won (or as later
    // specified the move limit is reached)
    while (!myMap.didIWin()) {
      // save the next move from the usernmove function in a variable
      String nextMove = userMove(myMap, scan);
      // check for pits, and if there is one where the next move would go, call the
      // navigate pit function instead
      if (myMap.isThereAPit(nextMove)) {
        navigatePit(myMap, scan, nextMove);
        // simple movement commands
      } else if (nextMove.equalsIgnoreCase("R")) {
        myMap.moveRight();
      } else if (nextMove.equalsIgnoreCase("L")) {
        myMap.moveLeft();
      } else if (nextMove.equalsIgnoreCase("U")) {
        myMap.moveUp();
      } else if (nextMove.equalsIgnoreCase("D")) {
        myMap.moveDown();
      }
      // increment move counter
      moveCounter++;
      // print the map
      myMap.printMap();
      // call the move counter function to give the user warnings at certain intervals
      moveMessage(moveCounter);
      // print the number of moves used so far
      System.out.println("Moves made: " + moveCounter);
      // if the number of moves made equals 100, then break the loop and tell the user
      // they lost.
      if (moveCounter == 100) {
        System.out.println("Sorry, but you didn't escape in time - you lose");
        // system.exit(0) stops the while loop from inside the if statement
        System.exit(0);
      }
    }
    // give the user a congratulaztions if they won the game
    if (myMap.didIWin() == true && moveCounter < 100) {
      System.out.println("Congratulations, you beat the maze!");
    }
  }

  // simple introduction function, which explains the game
  public static void intro(Maze myMap) {
    System.out.println("Welcome to Maze Runner. This is the maze: ");
    myMap.printMap();
    System.out.println(
        "Your map will look like all \".\" and one \"x\". The \".\"'s may turn into either walls \"-\" or freespace \"*\". \n There might even be other surprises as well.");
  }

  // function which enables basic movement and protects against movement which
  // intersects with walls
  public static String userMove(Maze myMap, Scanner scan) {
    System.out.println("Which direction would you like to move? ('R', 'L', 'U', 'D')");

    // loop so if they don't input a direction they can try again
    while (true) {
      // put the input as uppercase so both uppercase and lowercase input works
      String input = scan.nextLine().toUpperCase();
      // check what direction was inputted
      if (input.equals("R") || input.equals("L") || input.equals("U") || input.equals("D")) {
        // check if the move is valid and make sure there is no pit there
        if (canIMove(input, myMap) || myMap.isThereAPit(input)) {
          return input;
        }
        // if it's not a valid move, tell the user this.
        System.out.println(
            "You cannot move this direction, there is a wall obstructing your path. \n Choose a new direction to move ('R', 'L', 'U', 'D')");
        continue;
      }
      // tell the user they did not input a valid moving direction and loop again
      System.out.println("You must input a valid move!");
    }
  }

  // arrange if else statements calling canIMove for each direction into a
  // function
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

  // function which is called when a pit is encountered
  public static void navigatePit(Maze myMap, Scanner scan, String move) {
    System.out.println("Watch out! There's a pit ahead. Jump it?");
    // to lower case so if they put an uppercase y, then it still works
    String input = scan.nextLine().toLowerCase();
    // check if the user indicated yes. if not stay still.
    if (input.equals("y")) {

      // ask the user for input about diagonal jump direction. for example, if the
      // hole is to the right of them, they have the choice to move to the right one,
      // and then up, down, or right one
      System.out.println("Which direction do you want to jump? ('R', 'L', 'U', 'D')");
      String dir = scan.nextLine().toUpperCase();

      // if the direction of the pit is to the right
      if (dir.equals("R")) {
        // then use the coordinates defined by Mr. Budd in maze.java to jump in the
        // direction as indicated
        if (move.equals("R")) {
          // deeper explanation of these numbers in maze.java
          myMap.jump(0, 2);
        } else if (move.equals("D")) {
          myMap.jump(1, 1);
        } else if (move.equals("U")) {
          myMap.jump(-1, 1);
        } // no fourth condition because it ends up being the same location
      }

      // if the direction of the pit is to the left
      else if (dir.equals("L")) {
        if (move.equals("L")) {
          myMap.jump(0, -2);
        } else if (move.equals("D")) {
          myMap.jump(1, -1);
        } else if (move.equals("U")) {
          myMap.jump(-1, -1);
        } // no fourth condition because it ends up being the same location
      }

      // if the direction of the pit is below
      else if (dir.equals("D")) {
        if (move.equals("R")) {
          myMap.jump(1, 1);
        } else if (move.equals("L")) {
          myMap.jump(1, -1);
        } else if (move.equals("D")) {
          myMap.jump(2, 0);
        } // no fourth condition because it ends up being the same location
      }

      // if the direction of the pit is above
      else if (dir.equals("U")) {
        if (move.equals("R")) {
          myMap.jump(-1, 1);
        } else if (move.equals("L")) {
          myMap.jump(-1, -1);
        } else if (move.equals("U")) {
          myMap.jump(-2, 0);
        } // no fourth condition because it ends up being the same location
      }
    }
  }

  // simple function to organize if statements for when the move counter reaches
  // certain thresholds
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

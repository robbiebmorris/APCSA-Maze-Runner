public class Pathfinder {
  
  import java.io.*;
import java.util.*;


class Graph {
    private int V; 

    //Array of lists
    //Adjacency list representation
    private LinkedList<Integer> adj[];

    //Constructor
    @SuppressWarnings("unchecked") Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }
    
    // Function to add an edge into the graph
    void addEdge(int v, int w)
    {
        adj[v].add(w); // Add w to v's list.
    }
 
    // A function used by DFS
    void DFSUtil(int v, boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");
 
        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) 
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
 
    // The function to do DFS traversal.
    // It uses recursive
    // DFSUtil()
    void DFS(int v)
    {
        // Mark all the vertices as 
        // not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];
 
        // Call the recursive helper 
        // function to print DFS
        // traversal
        DFSUtil(v, visited);
    }
 
    // Driver Code
    public static void main(String args[])
    {
        Graph g = new Graph(4);
 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println(
            "Following is Depth First Traversal "
            + "(starting from vertex 2)");
 
        g.DFS(2);
    }
}

/**

public List<Coordinate> solve(Maze maze) {

    private static int[][] Directions 
        ={ {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    private Coordinate getNextCoordinate(
        int row, int col, int i, int j) {
            return new Coordinate(row + i, col + j);
        }

}

public List<Coordinate> solve(Maze maze) {
    List<Coordinate> path = new ArrayList<>();
    if (
        explore (
            maze,
            maze.getEntry().getX(),
            maze.getEntry().getY(),
            path
        )
        )  {
            return path;
    }
    return Collections.emptyList();
}

private boolean explore(
  Maze maze, int row, int col, List<Coordinate> path) {
    if (
      !maze.isValidLocation(row, col) 
      || maze.isWall(row, col) 
      || maze.isExplored(row, col)
    ) {
        return false;
    }

    path.add(new Coordinate(row, col));
    maze.setVisited(row, col, true);

    if (maze.isExit(row, col)) {
        return true;
    }

    for (int[] direction : DIRECTIONS) {
        Coordinate coordinate = getNextCoordinate(
          row, col, direction[0], direction[1]);
        if (
          explore(
            maze, 
            coordinate.getX(), 
            coordinate.getY(), 
            path
          )
        ) {
            return true;
        }
    }

    path.remove(path.size() - 1);
    return false;
}

private int [][] maze 
    {   {1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
        {0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0},
        {1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0},
        {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
        {0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0},
        {0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0},
        {0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,1,0,0,0},
        {0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
        {0,0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
        {0,0,0,1,0,1,0,0,0,0,0,0,0,1,0,0,1,1,1,1},
        {0,0,0,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,0,0},
        {0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1},
        {0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0},
        {0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
        {1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
        {1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},

    };

 */

}

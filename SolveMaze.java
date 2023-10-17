import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class SolveMaze {

  /** */
  public boolean checkLocation (Maze maze, MazeLocation current) {
    // if current is final location in maze
    if (current.equals(maze.getFinish())) {
      // mark current as part of path
      maze.grid[current.getRow()][current.getCol()] = MazeContents.PATH;
      return true;
    }
    MazeContents contentsAtCurrent = maze.getContents(current.getRow(), current.getCol());
    // if current is a wall or already visited
    if (contentsAtCurrent.equals(MazeContents.VISITED) || contentsAtCurrent.equals(MazeContents.WALL)) {
      return false;
    }
    else {
      maze.grid[current.getRow()][current.getCol()] = MazeContents.VISITED;;
      try { Thread.sleep(50);	} catch (InterruptedException e) {};
      if (checkLocation(maze, current.neighbor(MazeDirection.NORTH)) || checkLocation(maze, current.neighbor(MazeDirection.SOUTH)) || checkLocation(maze, current.neighbor(MazeDirection.EAST)) || checkLocation(maze, current.neighbor(MazeDirection.WEST))) {
        maze.grid[current.getRow()][current.getCol()] = MazeContents.PATH;
      }
    }
    return true;
  }


  public static void main(String[] args) {
    Scanner file = null;
    if (args.length > 0) {
      String filename = args[0];
      try {
        file = new Scanner(new File(filename));
      } catch (FileNotFoundException e) {
        System.err.println("Cannot locate file.");
        System.exit(-1);
      }
    } else {
      file = new Scanner(System.in);
    }

    // first scan to find dimensions of array
    int rowCount = 0;
    String line = "";
    while (file.hasNextLine()) {
      line = file.nextLine();
      rowCount += 1;
    }
    int columnCount = line.length();
    file.close();

    MazeContents[][] grid = new MazeContents[rowCount][columnCount];

    // second scan to populate array
    Scanner file2 = null;
    if (args.length > 0) {
      String filename = args[0];
      try {
        file2 = new Scanner(new File(filename));
      } catch (FileNotFoundException e) {
        System.err.println("Cannot locate file.");
        System.exit(-1);
      }
    } else {
      file2 = new Scanner(System.in);
    }

    int rowCounter = 0;
    MazeLocation start = new MazeLocation(0,0);
    MazeLocation finish = new MazeLocation(0,0);
    while (file2.hasNextLine()) {
      line = file.nextLine();
      String value = String.valueOf(line.charAt(0));
      for (int i = 0; i < columnCount; i++) {
        // WALL
        if (value == "#") {
          grid[rowCounter][i] = MazeContents.WALL;
        }
        // OPEN
        if (value == ".") {
          grid[rowCounter][i] = MazeContents.OPEN;
        }
        // start
        if (value == "S") {
          grid[rowCounter][i] = MazeContents.OPEN;
          start.setRow(rowCounter);
          start.setCol(i);
        }
        // finish
        if (value == "F") {
          grid[rowCounter][i] = MazeContents.OPEN;
          finish.setRow(rowCounter);
          finish.setCol(i);
        }
      }
      rowCounter += 1;
    }
    file2.close();

    // initalize a new maze
    Maze maze = new Maze(start, finish, grid);
    MazeViewer viewer = new MazeViewer(maze);
    SolveMaze solve = new SolveMaze();
    if (solve.checkLocation(maze, maze.getStart())) {
      System.out.println("Found solution");
    }






    // Maze maze = new Maze();
    // MazeViewer viewer = new MazeViewer(maze);
    // SolveMaze solve = new SolveMaze();
    // if (solve.checkLocation(maze, maze.getStart())) {
    //   System.out.println("Found solution");
    // }

  }
}
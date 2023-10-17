import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** SolveMaze
 * Main method reads in file and calls checkLocation
*/
class SolveMaze {

  /** Reads in a file from command line and solves maze */
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
      line = file2.nextLine();
      for (int i = 0; i < columnCount; i++) {
        String value = String.valueOf(line.charAt(i));
        // WALL
        if (value.equals("#")) {
          grid[rowCounter][i] = MazeContents.WALL;
        }
        // OPEN
        if (value.equals(".")) {
          grid[rowCounter][i] = MazeContents.OPEN;
        }
        // start
        if (value.equals("S")) {
          grid[rowCounter][i] = MazeContents.OPEN;
          start.setRow(rowCounter);
          start.setCol(i);
        }
        // finish
        if (value.equals("F")) {
          grid[rowCounter][i] = MazeContents.OPEN;
          finish.setRow(rowCounter);
          finish.setCol(i);
        }
      }
      rowCounter += 1;
    }
    file2.close();

    System.out.println("finish = "+finish.getRow()+","+finish.getCol());

    // initalize a new maze
    Maze maze = new Maze(start, finish, grid);
    MazeViewer viewer = new MazeViewer(maze);
    if (maze.solve(maze.getStart())) {
      System.out.println("Found solution");
    }

  }
}
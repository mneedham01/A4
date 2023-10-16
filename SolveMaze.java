
class SolveMaze {

  /** */
  public boolean checkLocation (Maze maze, MazeLocation current) {

    System.out.println("Called checkLocation on square: "+ current.getRow() + current.getCol());

    // if current is final location in maze
    if (current.equals(maze.getFinish())) {
      System.out.println("Current = finish.");
      // mark current as part of path
      maze.grid[current.getRow()][current.getCol()] = MazeContents.PATH;
      return true;
    }
    MazeContents contentsAtCurrent = maze.getContents(current.getRow(), current.getCol());
    // if current is a wall or already visited
    if (contentsAtCurrent.equals(MazeContents.VISITED) || contentsAtCurrent.equals(MazeContents.WALL)) {
      System.out.println("Current = wall OR Current = already visited.");
      return false;
    }
    else {
      System.out.println("In the else.");
      maze.grid[current.getRow()][current.getCol()] = MazeContents.VISITED;;
      try { Thread.sleep(50);	} catch (InterruptedException e) {};
      if (checkLocation(maze, current.neighbor(MazeDirection.NORTH)) || checkLocation(maze, current.neighbor(MazeDirection.SOUTH)) || checkLocation(maze, current.neighbor(MazeDirection.EAST)) || checkLocation(maze, current.neighbor(MazeDirection.WEST))) {
        maze.grid[current.getRow()][current.getCol()] = MazeContents.PATH;
      }
    }
    return true;
  }


  public static void main(String[] args) {
    Maze maze = new Maze();
    MazeViewer viewer = new MazeViewer(maze);
    SolveMaze solve = new SolveMaze();

    if (solve.checkLocation(maze, maze.getStart())) {
      System.out.println("Found solution");
    }

  }
}
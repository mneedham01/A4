
class SolveMaze {

  // set up enum values
  MazeDirection NORTH;
  MazeDirection EAST;
  MazeDirection SOUTH;
  MazeDirection WEST;

  /** */
  public boolean checkLocation (Maze maze, MazeLocation current) {

    System.out.println("Called checkLocation on square: "+ current.getRow() + current.getCol());

    // if current is final location in maze
    if (current.equals(maze.getFinish())) {
      System.out.println("Current = finish.");
      // mark current as part of path
      current.setOnPath(true);
      return true;
    }
    // if current is a wall or already visited
    if (current.getVisited() || maze.getContents(current.getRow(), current.getCol()).equals(MazeContents.WALL)) {
      System.out.println("Current = wall OR Current = already visited.");
      return false;
    }
    else {
      System.out.println("In the else.");
      current.setVisited(true);
      try { Thread.sleep(50);	} catch (InterruptedException e) {};
      if (checkLocation(maze, current.neighbor(MazeDirection.NORTH)) || checkLocation(maze, current.neighbor(MazeDirection.SOUTH)) || checkLocation(maze, current.neighbor(MazeDirection.EAST)) || checkLocation(maze, current.neighbor(MazeDirection.WEST))) {
        current.setOnPath(true);
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
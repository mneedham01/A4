
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
      // mark current as part of path
      current.setOnPath(true);
      return true;
    }
    // if current is a wall or already visited
    if (current.getVisited()) {
      return false;
    }
    else {
      current.setVisited(true);
      try { Thread.sleep(50);	} catch (InterruptedException e) {};
      if (checkLocation(maze, current.neighbor(NORTH)) || checkLocation(maze, current.neighbor(SOUTH)) || checkLocation(maze, current.neighbor(EAST)) || checkLocation(maze, current.neighbor(WEST))) {
        current.setOnPath(true);
      }
    }
    return true;
  }


  public static void main(String[] args) {
    Maze maze = new Maze();
    MazeViewer viewer = new MazeViewer(maze);
    SolveMaze solve = new SolveMaze();

    solve.checkLocation(maze, maze.getStart());

  }
}
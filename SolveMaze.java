
class SolveMaze {

  /** */
  public boolean checkLocation (Maze maze, MazeLocation current) {
    MazeContents contents = maze.getContents(current.getRow(), current.getCol());
    // if current is final location in maze
    if (current.equals(maze.getFinish())) {
      // mark current as part of path
      contents = MazeContents.PATH;
      return true;
    }
    // if current is a wall or already visited
    if (contents.equals(MazeContents.VISITED) || contents.equals(MazeContents.WALL)) {
      return false;
    }
    else {
      contents = MazeContents.VISITED;;
      try { Thread.sleep(50);	} catch (InterruptedException e) {};
      if (checkLocation(maze, current.neighbor(MazeDirection.NORTH)) || checkLocation(maze, current.neighbor(MazeDirection.SOUTH)) || checkLocation(maze, current.neighbor(MazeDirection.EAST)) || checkLocation(maze, current.neighbor(MazeDirection.WEST))) {
        contents = MazeContents.PATH;
      }
    }
    return true;
  }


  public static void main(String[] args) {
    // initalize a scanner
    Maze maze = new Maze();
    MazeViewer viewer = new MazeViewer(maze);
    SolveMaze solve = new SolveMaze();
    if (solve.checkLocation(maze, maze.getStart())) {
      System.out.println("Found solution");
    }

  }
}
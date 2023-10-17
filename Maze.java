
/* Maze class stores information about maze, including the 2D array, start, and finish */
public class Maze implements DisplayableMaze {

    // to hard code a maze
    //MazeContents[][] grid =  {{MazeContents.OPEN, MazeContents.OPEN, MazeContents.OPEN, MazeContents.WALL}, {MazeContents.WALL, MazeContents.WALL, MazeContents.OPEN, MazeContents.WALL}, {MazeContents.WALL, MazeContents.WALL, MazeContents.OPEN, MazeContents.WALL}, {MazeContents.WALL, MazeContents.WALL, MazeContents.OPEN, MazeContents.OPEN}};
    //MazeLocation start = new MazeLocation(0,0);
    //MazeLocation finish = new MazeLocation(3,3);

    MazeContents[][] grid;
    MazeLocation start;
    MazeLocation finish;

    /** @return Maze */
    public Maze () {
    }

     /** @return Maze */
    public Maze (MazeLocation start, MazeLocation finish) {
        this.start = start;
        this.finish = finish;
    }

    /** @return Maze */
    public Maze (MazeLocation start, MazeLocation finish, MazeContents[][] grid) {
        this.grid = grid;
        this.start = start;
        this.finish = finish;
    }

    /** @return height of maze grid */
    public int getHeight(){
        // how many subarrays
        return this.grid.length;
    }

    /** @return width of maze grid */
    public int getWidth() {
        // length of each subarray
        return this.grid[0].length;
    }

    /** @return contents of maze grid at row i, column j */
    public MazeContents getContents(int i, int j) {
        if (i >= this.getHeight() || i < 0 || j >= this.getWidth() || j < 0) {
            return MazeContents.WALL;
        } else {
            return this.grid[i][j];
        }
    }

    /** @return location of maze start point */
    public MazeLocation getStart(){
        return this.start;
    }

    /** @return location of maze finish point */
    public MazeLocation getFinish(){
        return this.finish;
    }

    /** @return whether the maze can be solved given an individual location  */
    public boolean solve (MazeLocation current) {
        // if current is final location in maze
        if (current.equals(this.getFinish())) {
            // mark current as part of path
            this.grid[current.getRow()][current.getCol()] = MazeContents.PATH;
            return true;
        }
        MazeContents contentsAtCurrent = this.getContents(current.getRow(), current.getCol());
        // if current is a wall or already visited
        if (contentsAtCurrent == MazeContents.VISITED || contentsAtCurrent  == MazeContents.WALL) {
            return false;
        }
        // if current is not the final location, not a wall, and not already visited
        // set current to visited
        this.grid[current.getRow()][current.getCol()] = MazeContents.VISITED;;
        // delay for graphics
        try { Thread.sleep(10);	} catch (InterruptedException e) {};
        // check in each direction
        if (solve(current.neighbor(MazeDirection.NORTH)) || solve(current.neighbor(MazeDirection.SOUTH)) || solve(current.neighbor(MazeDirection.EAST)) || solve(current.neighbor(MazeDirection.WEST))) {
            this.grid[current.getRow()][current.getCol()] = MazeContents.PATH;
            return true;
        } else {
            this.grid[current.getRow()][current.getCol()] = MazeContents.DEAD_END;
            return false;
        }
  }

}


/* fakjla;kfldj */
public class Maze implements DisplayableMaze {

    MazeContents[][] grid =  {{MazeContents.OPEN, MazeContents.OPEN, MazeContents.OPEN, MazeContents.WALL}, {MazeContents.WALL, MazeContents.WALL, MazeContents.OPEN, MazeContents.WALL}, {MazeContents.WALL, MazeContents.WALL, MazeContents.OPEN, MazeContents.WALL}, {MazeContents.WALL, MazeContents.WALL, MazeContents.OPEN, MazeContents.OPEN}};
    MazeLocation start = new MazeLocation(0,0);
    MazeLocation finish = new MazeLocation(3,3);

    /* Constructor */
    public Maze () {
    }

     /* Constructor */
    public Maze (MazeLocation start, MazeLocation finish) {
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
        return (i > this.getHeight() || j > this.getWidth()) ? MazeContents.WALL : this.grid[i][j];
    }

    /** @return location of maze start point */
    public MazeLocation getStart(){
        return this.start;
    }

    /** @return location of maze finish point */
    public MazeLocation getFinish(){
        return this.finish;
    }

}

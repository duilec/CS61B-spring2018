package lab11.graphs;

/**
 *  @author Josh Hug, Huang Jinhong
 */
public class CyclesDemo {
    /* Identifies a cycle (if any exist) in the given graph, and draws the cycle with
     * a purple line. */
    
    public static void main(String[] args) {
        Maze maze = new Maze("lab11/graphs/maze.txt");

        MazeCycles mc = new MazeCycles(maze);
        mc.solve();
    }

}

// test of CyclesDemo in maze.text
// case 1
// N = 8
// rseed = 4

// MazeType = SINGLE_GAP
// MazeType = POPEN_SOLVABLE
// MazeType = BLANK

// pOpen = 0.48
// DRAW_DELAY_MS = 30

// case 2
// N = 8
// rseed = 4

// MazeType = SINGLE_GAP
// MazeType = POPEN_SOLVABLE
// %MazeType = BLANK

// pOpen = 0.48
// DRAW_DELAY_MS = 30
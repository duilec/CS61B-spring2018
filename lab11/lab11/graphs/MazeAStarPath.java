package lab11.graphs;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug, Huang Jinhong
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked(Queue<Integer> queue) {
        int minimumVertex = queue.peek();
        int minimumPath = distTo[minimumVertex] + h(minimumVertex);
        for (int vertex : queue) {
            if (distTo[vertex] + h(vertex) < minimumPath) {
                minimumVertex = vertex;
            }
        }
        return minimumVertex;
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        Queue<Integer> fringe = new ArrayDeque<>();
        fringe.add(s);
        marked[s] = true;
        announce();
        while (!fringe.isEmpty()){
            int v = findMinimumUnmarked(fringe);
            fringe.remove(v);
            for (int w : maze.adj(v)){
                if (!marked[w]) {
                    fringe.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();
                    if (w == t) {
                        targetFound = true;
                    }
                    if (targetFound) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}


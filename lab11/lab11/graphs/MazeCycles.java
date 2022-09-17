package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int markedCount = 0;
    private boolean FoundCycle = false;
    private boolean notFoundCycle = false;
    private Maze maze;
    private int[] edgeToWithCycle;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        edgeTo[s] = s;
        distTo[s] = 0;
        edgeToWithCycle = new int[maze.V()];
        for (int i = 0; i < maze.V(); i += 1) {
            edgeToWithCycle[i] = Integer.MAX_VALUE;
        }
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfsWithDetectCycle(s);
    }

    // Helper methods go here
    private void dfsWithDetectCycle(int v) {
        marked[v] = true;
        markedCount += 1;
        announce();

        if (markedCount >= maze.V()) {
            notFoundCycle = true;
        }

        if (notFoundCycle) {
            return;
        }

        for (int w : maze.adj(v)) {
            // you should use "else if" to check w to find cycle
            // you couldn't use "if" to check w to find cycle.
            // because you can't mark w, then, immediately check it.
            // otherwise, when tracebacking to second node you will immediately return.
            // you only do one thing(mark or check) when visiting a w.
            if (!marked[w]) {
                edgeToWithCycle[w] = v;
                distTo[w] = distTo[v] + 1;
                dfsWithDetectCycle(w);
                if (FoundCycle) {
                    return;
                }
                if (notFoundCycle) {
                    return;
                }
            } else if (marked[w] && (edgeToWithCycle[v] != w)) {
                FoundCycle = true;
                linkCycle(v);
                announce();
                return;
            }
        }
    }

    // only link a cycle with 4 nodes
    // if more than 4 nodes, we need to search paths of two nodes by dfs or bfs
    // I don't implement these(cycle with more than 4 nodes)
    private void linkCycle(int parent) {
        int [] adjCycle = new int[3];
        int i = 0;
        // find 2 nodes
        for (int u : maze.adj(parent)) {
            if (edgeToWithCycle[u] != Integer.MAX_VALUE) {
                adjCycle[i] = u;
                i += 1;
            }
        }
        // find 1 node
        for (int u1 : maze.adj(adjCycle[0])) {
            for (int u2 : maze.adj(adjCycle[1])) {
                if (u1 == u2 && u1 != parent) {
                    adjCycle[2] = u1;
                    break;
                }
            }
        }
        // link a cycle with 4 nodes
        edgeTo[parent] = adjCycle[0];
        edgeTo[adjCycle[1]] = parent;
        edgeTo[adjCycle[2]] = adjCycle[1];
        edgeTo[adjCycle[0]] = adjCycle[2];
    }
}

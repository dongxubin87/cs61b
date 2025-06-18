package lab11.graphs;


/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean cycleFound = false;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        detectCycle(0, -1);
    }

    // Helper methods go here
    private void detectCycle(int s, int p) {
        if (cycleFound) return;
        marked[s] = true;
        announce();
        for (int w : maze.adj(s)) {
            if (cycleFound) return;
            if (!marked[w]) {
                edgeTo[w] = s;
                detectCycle(w, s);
            } else if (w != p) {
                cycleFound = true;
                edgeTo[w] = s;

                int x = s;
                while (x != w && x != -1) {
                    int prev = edgeTo[x];
                    edgeTo[prev] = x;
                    x = prev;
                }
                announce();
                return;
            }
        }
    }

}


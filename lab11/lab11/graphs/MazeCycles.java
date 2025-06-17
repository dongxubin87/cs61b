package lab11.graphs;


import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        detectCycle(0);
    }

    // Helper methods go here
    private void detectCycle(int s){
        marked[s] = true;
        announce();
        for (int w : maze.adj(s)) {
            if (!marked[w]) {
                if(){

                }
                edgeTo[w] = s;
                announce();
                detectCycle(w);

            }
        }
    }
}


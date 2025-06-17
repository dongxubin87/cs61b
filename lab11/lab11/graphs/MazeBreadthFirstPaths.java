package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs(int v) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> q = new Queue<Integer>();

        marked[v] = true;
        q.enqueue(v);
        announce();

        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }
        while (!q.isEmpty()) {
            int s = q.dequeue();
            for (int w : maze.adj(s)) {
                if (!marked[w]) {
                    edgeTo[w] = s;
                    distTo[w] = distTo[s] + 1;
                    marked[w] = true;
                    announce();
                    if (w == t) {
                        return;
                    }
                    q.enqueue(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}


package lab11.graphs;

import java.util.PriorityQueue;

/**
 * @author Josh Hug
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

    /**
     * Estimate of the distance from v to the target.
     */
    private int h(int v) {
        int vX = maze.toX(v);
        int vY = maze.toY(v);
        int tX = maze.toX(v);
        int tY = maze.toY(v);

        return Math.abs(vX - vY) + Math.abs(tX + tY);
    }

    /**
     * Finds vertex estimated to be closest to target.
     */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /**
     * Performs an A star search from vertex s.
     */
    private void astar(int s) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((v1, v2) -> Integer.compare(distTo[v1] + h(v1), distTo[v2] + h(v2)));
        pq.add(s);
        marked[s] = true;

        while (!pq.isEmpty()) {
            int current = pq.poll();

            if (current == t) {
                targetFound = true;
                return;
            }

            for (int neighbor : maze.adj(current)) {
                if (!marked[neighbor]) {
                    distTo[neighbor] = distTo[current] + 1;
                    edgeTo[neighbor] = current;
                    marked[neighbor] = true;
                    announce();
                    pq.add(neighbor);
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}


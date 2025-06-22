package lab11.graphs;


import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

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
    private boolean foundCycle = false;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
    }

    @Override
//    public void solve() {
//        // TODO: Your code here!
//        detectCycle(0, -1);
//    }
//
//    // Helper methods go here
//    private void detectCycle(int s, int p) {
//        if (foundCycle) return;
//        marked[s] = true;
//        announce();
//        for (int w : maze.adj(s)) {
//            if (foundCycle) return;
//            if (!marked[w]) {
//                edgeTo[w] = s;
//                detectCycle(w, s);
//            } else if (w != p) {
//                foundCycle = true;
//                edgeTo[w] = s;
//
//                int x = s;
//                while (x != w && x != -1) {
//                    int prev = edgeTo[x];
//                    edgeTo[prev] = x;
//                    x = prev;
//                }
//                announce();
//                return;
//            }
//        }
//    }
    public void solve() {
        Stack<Integer> stack = new Stack<>();
        int[] parent = new int[maze.V()];
        Arrays.fill(parent, -1); // -1 表示无父节点

        stack.push(0);
        marked[0] = true;
        announce();

        while (!stack.isEmpty() && !foundCycle) {
            int v = stack.pop();

            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    parent[w] = v;
                    edgeTo[w] = v;
                    announce();
                    stack.push(w);
                } else if (w != parent[v]) {
                    // 发现环，w 已访问，且不是 v 的父节点
                    foundCycle = true;

                    // 构造环路径（从 v 回到 w）
                    edgeTo[w] = v;
                    announce();

                    int x = v;
                    while (x != w && x != -1) {
                        int p = parent[x];
                        edgeTo[p] = x;
                        announce();
                        x = p;
                    }

                    return; // 找到环后立即退出
                }
            }
        }
    }
}


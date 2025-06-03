package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private Boolean[][] sites;
    private int opendSites;
    private WeightedQuickUnionUF uf;
    private int WIDTH;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IndexOutOfBoundsException("Please enter a valid number!");
        }
        sites = new Boolean[N][N];
        // initialize all the sites blocked
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = false;
            }
        }

        opendSites = 0;
        WIDTH = N;
        // initialize class WeightedQuickUnionUF;
        uf = new WeightedQuickUnionUF(N * N);

    }

    public void open(int row, int col) {
        checkValid(row, col);
        if (isOpen(row, col)) {
            return;
        }
        sites[row][col] = true;
        opendSites++;

        AutoConnectSites(row, col);

    }

    // the idea is that after every time open a site, connect its up,down,left,right sites
    private void AutoConnectSites(int x, int y) {
        int pos = xyToId(x, y);
        // initialize four sites in four directions
        int up = pos - WIDTH;
        int down = pos + WIDTH;
        int left = pos - 1;
        int right = pos + 1;
        if (up >= 0 && sites[x-1][y] && !uf.connected(pos, up)) {
            uf.union(pos, up);
        }
        if (down < WIDTH * WIDTH && sites[x+1][y] && !uf.connected(pos, down)) {
            uf.union(pos, down);
        }
        if (left >= 0 && sites[x][y-1]  && !uf.connected(pos, left)) {
            uf.union(pos, left);
        }
        if (right < WIDTH * WIDTH && sites[x][y+1]  && !uf.connected(pos, right)) {
            uf.union(pos, right);
        }
    }

    public boolean isOpen(int row, int col) {
        checkValid(row, col);
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        checkValid(row, col);
        return false;
    }

    private void checkValid(int row, int col) {
        if (row < 0 || row >= WIDTH || col < 0 || col >= WIDTH) {
            throw new IllegalArgumentException("Please enter a legal argument.");
        }
    }

    public int numberOfOpenSites() {
        return opendSites;
    }

    private int xyToId(int x, int y) {
        return WIDTH * x + y;
    }

    public boolean percolates() {
        return false;
    }

}

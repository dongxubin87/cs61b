package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private Boolean[][] sites;
    private int opendSites;
    private WeightedQuickUnionUF uf;
    private int WIDTH; // the width of the map , also the length of boolean array

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
        // initialize class WeightedQuickUnionUF, last item as top virtual site, last second item as bottom virtual site
        uf = new WeightedQuickUnionUF(N * N + 2);
        // connect items in the first row to the last item
        for (int i = 0; i < N; i++) {
            uf.union(N * N + 1, i);
        }
        // connect items in the last row to the last second item
        for (int i = N * N - 1; i >= N * N - N; i--) {
            uf.union(N * N, i);
        }
    }

    public void open(int row, int col) {
        checkValid(row, col);
        if (isOpen(row, col)) {
            return;
        }
        sites[row][col] = true;
        // update openedSites
        opendSites++;
        // call the method after opening any site successfully
        AutoConnectSites(row, col);

    }

    // the idea is that after every time open a site, connect its up,down,left,right sites
    private void AutoConnectSites(int x, int y) {
        int pos = xyToId(x, y);
        // initialize up direction

        if (x - 1 >= 0 && sites[x - 1][y]) {
            uf.union(pos, pos - WIDTH);
        }
        // initialize down direction
        if (x + 1 < WIDTH && sites[x + 1][y]) {
            uf.union(pos, pos + WIDTH);
        }
        // initialize left direction
        if (y - 1 >= 0 && sites[x][y - 1]) {
            uf.union(pos, pos - 1);
        }
        // initialize right direction
        if (y + 1 < WIDTH && sites[x][y + 1]) {
            uf.union(pos, pos + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        checkValid(row, col);
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        checkValid(row, col);
        return uf.connected(xyToId(row, col), WIDTH * WIDTH + 1);// check this site is connected to the top site
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
        return uf.connected(WIDTH * WIDTH, WIDTH * WIDTH + 1); // check top and bottom is connected
    }

}

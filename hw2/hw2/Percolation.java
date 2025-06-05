package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private Boolean[][] sites;
    private int opendSites;
    private WeightedQuickUnionUF uf;
    private int width; // the width of the map , also the length of boolean array
    private int topSite;
    private int bottomSite;

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
        width = N;
        // here we set top(N*N+1) and bottom(N*N) site,
        uf = new WeightedQuickUnionUF(N * N + 2);

        topSite = N * N + 1;
        bottomSite = N * N;
    }

    public void open(int row, int col) {
        checkValid(row, col);
        if (isOpen(row, col)) {
            return;
        }
        sites[row][col] = true;
        // if the site is right in the first row, then connect it to the top site
        if (row == 0) {
            uf.union(xyToId(row, col), topSite);
        }
        // if the site is right in the last row, then connect it to the bottom site
        if (row == width - 1) {
            uf.union(xyToId(row, col), bottomSite);
        }
        // update openedSites
        opendSites++;
        // call the method after opening any site successfully
        autoConnectSites(row, col);

    }

    // the idea is that after every time open a site, connect its up,down,left,right sites
    private void autoConnectSites(int x, int y) {
        int pos = xyToId(x, y);
        // initialize up direction

        if (x - 1 >= 0 && sites[x - 1][y]) {
            uf.union(pos, pos - width);
        }
        // initialize down direction
        if (x + 1 < width && sites[x + 1][y]) {
            uf.union(pos, pos + width);
        }
        // initialize left direction
        if (y - 1 >= 0 && sites[x][y - 1]) {
            uf.union(pos, pos - 1);
        }
        // initialize right direction
        if (y + 1 < width && sites[x][y + 1]) {
            uf.union(pos, pos + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        checkValid(row, col);
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        checkValid(row, col);
        if (!sites[row][col]) {
            return false;
        }
        // check this site is connected to the top site
        return uf.connected(xyToId(row, col), width * width + 1);
    }

    private void checkValid(int row, int col) {
        if (row < 0 || row >= width || col < 0 || col >= width) {
            throw new IllegalArgumentException("Please enter a legal argument.");
        }
    }

    public int numberOfOpenSites() {
        return opendSites;
    }

    private int xyToId(int x, int y) {
        return width * x + y;
    }

    public boolean percolates() {
        return uf.connected(width * width, width * width + 1); // check top and bottom is connected
    }

    public static void main(String[] args) {

    }

}

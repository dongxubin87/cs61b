package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private Boolean[][] sites;
    private int openedSites;
    private WeightedQuickUnionUF ufPercolaton;

    // this is for backwash check, only has top site for ufFullCheck
    private WeightedQuickUnionUF ufFullCheck;
    private int width; // the width of the map , also the length of boolean array
    private int topSite;
    private int bottomSite;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be > 0");
        }
        sites = new Boolean[N][N];
        // initialize all the sites blocked
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = false;
            }
        }

        openedSites = 0;
        width = N;
        // here we set top(N*N) and bottom(N*N+1) site,
        ufPercolaton = new WeightedQuickUnionUF(N * N + 2);
        ufFullCheck = new WeightedQuickUnionUF(N * N + 1);
        topSite = N * N;
        bottomSite = N * N + 1;
    }

    public void open(int row, int col) {
        checkValid(row, col);
        if (isOpen(row, col)) {
            return;
        }
        sites[row][col] = true;
        // if the site is right in the first row, then connect it to the top site
        if (row == 0) {
            ufPercolaton.union(xyToId(row, col), topSite);
            ufFullCheck.union(xyToId(row, col), topSite);
        }
        // if the site is right in the last row, then connect it to the bottom site
        if (row == width - 1) {
            ufPercolaton.union(xyToId(row, col), bottomSite);
        }
        // update openedSites
        openedSites++;
        // call the method after opening any site successfully
        autoConnectSites(row, col);

    }

    // the idea is that after every time open a site, connect its up,down,left,right sites
    private void autoConnectSites(int x, int y) {
        int pos = xyToId(x, y);
        // initialize up direction

        if (x - 1 >= 0 && sites[x - 1][y]) {
            ufPercolaton.union(pos, pos - width);
            ufFullCheck.union(pos, pos - width);
        }
        // initialize down direction
        if (x + 1 < width && sites[x + 1][y]) {
            ufPercolaton.union(pos, pos + width);
            ufFullCheck.union(pos, pos + width);
        }
        // initialize left direction
        if (y - 1 >= 0 && sites[x][y - 1]) {
            ufPercolaton.union(pos, pos - 1);
            ufFullCheck.union(pos, pos - 1);
        }
        // initialize right direction
        if (y + 1 < width && sites[x][y + 1]) {
            ufPercolaton.union(pos, pos + 1);
            ufFullCheck.union(pos, pos + 1);
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
        return ufFullCheck.connected(xyToId(row, col), topSite);
    }

    private void checkValid(int row, int col) {
        if (row < 0 || row >= width || col < 0 || col >= width) {
            throw new IndexOutOfBoundsException("row or column index out of bounds");
        }
    }

    public int numberOfOpenSites() {
        return openedSites;
    }

    private int xyToId(int x, int y) {
        return width * x + y;
    }

    public boolean percolates() {
        // check top and bottom is connected
        return ufPercolaton.connected(topSite, bottomSite);
    }

    public static void main(String[] args) {

    }

}

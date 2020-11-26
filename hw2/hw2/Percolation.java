package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int UNOPENED = 0;
    private final int OPENED = 1;
    /** Sequence of grid of cell, top left cell is first index, bottom right cell is last index.
     * 1 represents opened cell, 0 represents unopened cell. */
    private int[] grid;
    /** Length of side of grid of square. */
    private int side;
    /** Sets of open cell, last index for the root of full sites. */
    private WeightedQuickUnionUF openSiteSets;
    /** Number of opened cell. */
    private int openSiteCount;

    /** create N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N for Percolation must be positive.");
        }
        int siteCount = N * N;
        this.grid = new int[siteCount];
        for (int i = 0; i < siteCount; i++) {
            grid[i] = UNOPENED;
        }
        this.side = N;
        this.openSiteSets = new WeightedQuickUnionUF(siteCount + 1);
        this.openSiteCount = 0;
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (row < 0 || side <= row || col < 0 || side <= col) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int siteIndex = row * side + col;
        this.grid[siteIndex] = OPENED;
        int[] aroundSiteIndices = {siteIndex - 1, siteIndex - side, siteIndex + 1, siteIndex + side};
        for (int aroundSiteIndex: aroundSiteIndices) {
            if (aroundSiteIndex < 0 || grid.length <= aroundSiteIndex) {
                continue;
            }
            if (grid[aroundSiteIndex] == OPENED) {
                openSiteSets.union(aroundSiteIndex, siteIndex);
            }
        }
        if (siteIndex < side) {
            openSiteSets.union(siteIndex, grid.length);
        }
        openSiteCount += 1;
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (row < 0 || side <= row || col < 0 || side <= col) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int siteIndex = row * side + col;
        return grid[siteIndex] == OPENED;
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        if (row < 0 || side <= row || col < 0 || side <= col) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int siteIndex = row * side + col;
        int root = openSiteSets.find(siteIndex);
        return root == grid.length;
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    /** does the system percolate? */
    public boolean percolates() {
        int lastRowStartIdx = grid.length - side;
        int lastRowEndIdx = grid.length - 1;
        for (int lastRowIndex = lastRowStartIdx; lastRowIndex <= lastRowEndIdx ; lastRowIndex++) {
            int root = openSiteSets.find(lastRowIndex);
            if (root == grid.length) {
                return true;
            }
        }
        return false;
    }

    /** use for unit testing (not required, but keep this here for the autograder) */
    public static void main(String[] args) {

    }
}

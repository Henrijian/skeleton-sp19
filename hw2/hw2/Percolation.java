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
    /** Root index for all site connected to top sites. (index = side * side) */
    private final int ROOT_TOP_IDX;
    /** Root index for all site connected to bottom sites. (index = side * side) */
    private final int ROOT_BOTTOM_IDX;
    /** Record whether the grid is percolated. */
    private boolean percolated;
    /** Sets of open sites, last index for the root of top sites. */
    private WeightedQuickUnionUF topSiteSets;
    /** Sets of open sites, last index for the root of bottom sites. */
    private WeightedQuickUnionUF bottomSiteSets;
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
        this.ROOT_TOP_IDX = siteCount;
        this.ROOT_BOTTOM_IDX = siteCount;
        this.percolated = false;
        this.topSiteSets = new WeightedQuickUnionUF(siteCount + 1); // 1 for rootTopIdx index.
        this.bottomSiteSets = new WeightedQuickUnionUF(siteCount + 1); // 1 for rootBottomIdx index.
        this.openSiteCount = 0;
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        int siteIndex = siteIndex(row, col);
        if (grid[siteIndex] == OPENED) {
            return;
        }
        this.grid[siteIndex] = OPENED;
        int[] aroundSiteIndices;
        if (col % side == side - 1) {
            aroundSiteIndices = new int[]{siteIndex - 1, siteIndex - side, siteIndex + side};
        } else if (col % side == 0) {
            aroundSiteIndices = new int[]{siteIndex - side, siteIndex + 1, siteIndex + side};
        } else {
            aroundSiteIndices = new int[]{siteIndex - 1, siteIndex - side, siteIndex + 1,
                siteIndex + side};
        }
        for (int aroundSiteIndex: aroundSiteIndices) {
            if (aroundSiteIndex < 0 || grid.length <= aroundSiteIndex) {
                continue;
            }
            if (grid[aroundSiteIndex] == OPENED) {
                topSiteSets.union(aroundSiteIndex, siteIndex);
                bottomSiteSets.union(aroundSiteIndex, siteIndex);
            }
        }
        if (siteIndex < side) {
            topSiteSets.union(siteIndex, ROOT_TOP_IDX);
        }
        if (grid.length - side <= siteIndex && siteIndex < grid.length) {
            bottomSiteSets.union(siteIndex, ROOT_BOTTOM_IDX);
        }
        if (!percolated) {
            percolated = topSiteSets.connected(ROOT_TOP_IDX, siteIndex)
                && bottomSiteSets.connected(ROOT_BOTTOM_IDX, siteIndex);
        }
        openSiteCount += 1;
    }

    /** Get the index of row and col in grid. */
    private int siteIndex(int row, int col) {
        if (row < 0 || side <= row || col < 0 || side <= col) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return row * side + col;
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        int siteIndex = siteIndex(row, col);
        return grid[siteIndex] == OPENED;
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        int siteIndex = siteIndex(row, col);
        return topSiteSets.connected(siteIndex, this.ROOT_TOP_IDX);
    }

    /** number of open sites */
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return percolated;
    }

    /** use for unit testing (not required, but keep this here for the autograder) */
    public static void main(String[] args) {

    }
}

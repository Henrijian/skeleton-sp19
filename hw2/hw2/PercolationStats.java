package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    /** Sequence of percolation threshold of every experiment. */
    private double[] percolationThresholdArray;

    /** perform T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T < 0) {
            throw new java.lang.IllegalArgumentException("N and T for PercolationStats must be positive.");
        }

        this.percolationThresholdArray = new double[T];

        int sitesCount = N * N;
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                percolation.open(row, col);
            }
            percolationThresholdArray[i] = (double) percolation.numberOfOpenSites() / sitesCount;
        }
    }

    /** Sample mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(percolationThresholdArray);
    }

    /** Sample standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(percolationThresholdArray);
    }

    /** Low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(percolationThresholdArray.length);
    }

    /** High endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(percolationThresholdArray.length);
    }
}

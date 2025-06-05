package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private PercolationFactory factory;
    private int times;
    private int width;
    private double standardDeviation;
    private double[] thresholdArray; // store each threshold

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Please a legal number");
        }
        factory = pf;
        times = T;
        width = N;
        thresholdArray = new double[T];
        calculateThreshold();
    }


    private void calculateThreshold() {

        int t = times;

        while (t > 0) {
            // create new perc
            Percolation perc = factory.make(width);
            while (!perc.percolates()) {
                int x = StdRandom.uniform(0, width);
                int y = StdRandom.uniform(0, width);
                perc.open(x, y);
            }
            // update array
            thresholdArray[times - t] = (double) perc.numberOfOpenSites() / (width * width);
            t--;

        }

    }

    public double mean() {
        return StdStats.mean(thresholdArray);
    }

    public double stddev() {
        return StdStats.stddev(thresholdArray);
    }

    public double confidenceLow() {

        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }
}

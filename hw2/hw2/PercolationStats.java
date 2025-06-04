package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private Percolation perc;
    private int experimentTimes;
    private int WIDTH;
    private double standardDeviation;
    private double[] thresholdArray; // store each threshold

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Please a legal number");
        }
        perc = pf.make(N);
        experimentTimes = T;
        WIDTH = N;
        thresholdArray = new double[T];
    }


    private void calculateShreshold() {

        int t = experimentTimes;

        while (t >= 0) {
            // create new perc
            perc = new PercolationFactory().make(WIDTH);

            while (!perc.percolates()) {
                int x = StdRandom.uniform(0, WIDTH);
                int y = StdRandom.uniform(0, WIDTH);
                perc.open(x, y);
            }
            // update array
            thresholdArray[experimentTimes - t] = perc.numberOfOpenSites() / WIDTH * WIDTH;
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

        return mean() - 1.96 * stddev() / Math.sqrt(experimentTimes);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(experimentTimes);
    }
}

package hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {

    private Percolation perc;
    private int experimentTimes;
    private int WIDTH;
    private double meanOfThreshold;
    private double standardDeviation;
    private double[] ThresholdArray; // store each threshold

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Please a legal number");
        }
        perc = pf.make(N);
        experimentTimes = T;
        WIDTH = N;
        ThresholdArray = new double[T];
    }


    private void calculateShreshold() {

        int t = experimentTimes;

        double sumOfThreshold = 0;

        while (t >= 0) {

            perc = new PercolationFactory().make(WIDTH);

            while (!perc.percolates()) {
                int x = StdRandom.uniform(0, WIDTH);
                int y = StdRandom.uniform(0, WIDTH);
                perc.open(x, y);
            }
            ThresholdArray[]
            meanOfThreshold =  perc.numberOfOpenSites();
            t--;
        }

    }

    public double mean() {

        return sumOfThreshold / experimentTimes;
    }

    public double stddev() {
    }

    public double confidenceLow() {
    }

    public double confidenceHigh() {
    }
}

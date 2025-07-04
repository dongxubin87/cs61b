import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }

    public Picture picture() {
        return new Picture(picture);
    }


    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    public double energy(int x, int y) {
        int width = width();
        int height = height();
        if (x < 0 || x >= width) {
            throw new IndexOutOfBoundsException("x is not valid");
        }
        if (y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("y is not valid");
        }
        int xLeft = x == 0 ? width - 1 : x - 1;
        int xRight = x == width - 1 ? 0 : x + 1;
        int yUp = y == 0 ? height - 1 : y - 1;
        int yDown = y == height - 1 ? 0 : y + 1;

        Color colorxLeft = picture.get(xLeft, y);
        Color colorxRight = picture.get(xRight, y);
        Color coloryUp = picture.get(x, yUp);
        Color coloryDown = picture.get(x, yDown);


        int redxLeft = colorxLeft.getRed();
        int redxRight = colorxRight.getRed();
        int redyUp = coloryUp.getRed();
        int redyDown = coloryDown.getRed();

        int greenxLeft = colorxLeft.getGreen();
        int greenxRight = colorxRight.getGreen();
        int greenyUp = coloryUp.getGreen();
        int greenyDown = coloryDown.getGreen();

        int bluexLeft = colorxLeft.getBlue();
        int bluexRight = colorxRight.getBlue();
        int blueyUp = coloryUp.getBlue();
        int blueyDown = coloryDown.getBlue();

        int rX = Math.abs(redxLeft - redxRight);
        int gX = Math.abs(greenxLeft - greenxRight);
        int bX = Math.abs(bluexLeft - bluexRight);

        double eX = rX * rX + gX * gX + bX * bX;

        int rY = Math.abs(redyUp - redyDown);
        int gY = Math.abs(greenyUp - greenyDown);
        int bY = Math.abs(blueyUp - blueyDown);

        double eY = rY * rY + gY * gY + bY * bY;
        double e = eX + eY;
        return e;
    }

    public int[] findHorizontalSeam() {
        Picture origin = picture;
        picture = transpose(picture);
        int[] hSeam = findVerticalSeam();
        picture = origin;
        return hSeam;
    }

    private Picture transpose(Picture p) {
        int width = width();
        int height = height();
        Picture transposed = new Picture(height, width);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                transposed.set(j, i, p.get(i, j));
            }
        }
        return transposed;
    }

    public int[] findVerticalSeam() {

        int w = width();
        int h = height();

        double[][] distTo = new double[h][w];
        int[][] edgeTo = new int[h][w];

        // Initialize top row
        for (int x = 0; x < w; x++) {
            distTo[0][x] = energy(x, 0);
        }

        // DP to compute paths
        for (int y = 1; y < h; y++) {
            for (int x = 0; x < w; x++) {
                distTo[y][x] = Double.POSITIVE_INFINITY;
                for (int dx = -1; dx <= 1; dx++) {
                    int prevX = x + dx;
                    if (prevX >= 0 && prevX < w) {
                        double newDist = distTo[y - 1][prevX] + energy(x, y);
                        if (newDist < distTo[y][x]) {
                            distTo[y][x] = newDist;
                            edgeTo[y][x] = prevX;
                        }
                    }
                }
            }
        }

        // Find the minimum in the bottom row
        double minEnergy = Double.POSITIVE_INFINITY;
        int minIndex = 0;
        for (int x = 0; x < w; x++) {
            if (distTo[h - 1][x] < minEnergy) {
                minEnergy = distTo[h - 1][x];
                minIndex = x;
            }
        }

        // Backtrack the path
        int[] seam = new int[h];
        seam[h - 1] = minIndex;
        for (int y = h - 2; y >= 0; y--) {
            seam[y] = edgeTo[y + 1][seam[y + 1]];
        }

        return seam;
    }

    public void removeHorizontalSeam(int[] seam) {
        picture = SeamRemover.removeHorizontalSeam(picture, seam);
    }

    public void removeVerticalSeam(int[] seam) {
        picture = SeamRemover.removeVerticalSeam(picture, seam);
    }
}

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture picture;


    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    public Picture picture() {
        return picture;
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

        int Rx = Math.abs(redxLeft - redxRight);
        int Gx = Math.abs(greenxLeft - greenxRight);
        int Bx = Math.abs(bluexLeft - bluexRight);

        double eX = Rx * Rx + Gx * Gx + Bx * Bx;

        int Ry = Math.abs(redyUp - redyDown);
        int Gy = Math.abs(greenyUp - greenyDown);
        int By = Math.abs(blueyUp - blueyDown);

        double eY = Ry * Ry + Gy * Gy + By * By;
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
        int width = width();
        int height = height();
        int[] vSeam = new int[height];
        int l = 0;
        int r = width - 1;
        for (int i = 0; i < height; i++) {
            double min = Double.MAX_VALUE;
            int target = 0;
            for (int j = l; j <= r; j++) {
                if (min > energy(j, i)) {
                    min = energy(j, i);
                    target = j;
                }
            }
            vSeam[i] = target;
            l = target == 0 ? target : target - 1;
            r = target == width - 1 ? target : target + 1;
        }
        return vSeam;
    }

    public void removeHorizontalSeam(int[] seam) {
        picture = SeamRemover.removeHorizontalSeam(picture, seam);
    }

    public void removeVerticalSeam(int[] seam) {
        picture = SeamRemover.removeVerticalSeam(picture, seam);
    }
}
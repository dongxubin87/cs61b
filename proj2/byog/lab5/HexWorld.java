package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 28;
    private static final int HEIGHT = 30;

    // draw a Hexagon
    public static void addHexagon() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        InitializeTiles(tiles);
        // (x,y) is the coordinate of the top left point, k is the number of dots in the first row

        // draw from the left to the right, first column
        drawHexagon(tiles, 3, 2, 6, Tileset.SAND);
        drawHexagon(tiles, 3, 2, 12, Tileset.TREE);
        drawHexagon(tiles, 3, 2, 18, Tileset.TREE);
        // second column
        drawHexagon(tiles, 3, 7, 3,Tileset.GRASS);
        drawHexagon(tiles, 3, 7, 9,Tileset.LOCKED_DOOR);
        drawHexagon(tiles, 3, 7, 15,Tileset.FLOOR);
        drawHexagon(tiles, 3, 7, 21,Tileset.FLOWER);

        // the third column
        drawHexagon(tiles, 3, 12, 1,Tileset.MOUNTAIN);
        drawHexagon(tiles, 3, 12, 7,Tileset.FLOOR);
        drawHexagon(tiles, 3, 12, 13,Tileset.PLAYER);
        drawHexagon(tiles, 3, 12, 19,Tileset.FLOOR);
        drawHexagon(tiles, 3, 12, 25,Tileset.GRASS);
       // the forth column
        drawHexagon(tiles, 3, 17, 3,Tileset.GRASS);
        drawHexagon(tiles, 3, 17, 9,Tileset.PLAYER);
        drawHexagon(tiles, 3, 17, 15,Tileset.FLOOR);
        drawHexagon(tiles, 3, 17, 21,Tileset.FLOWER);
        // the fifth column
        drawHexagon(tiles, 3, 22, 6, Tileset.WATER);
        drawHexagon(tiles, 3, 22, 12, Tileset.WALL);
        drawHexagon(tiles, 3, 22, 18, Tileset.TREE);

        ter.renderFrame(tiles);
    }

    // initialize the entire window with Nothing
    public static void InitializeTiles(TETile[][] tiles) {
        int width = tiles.length;
        int height = tiles[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = Tileset.NOTHING;
            }
        }
    }

    // draw your hexagon
    public static void drawHexagon(TETile[][] tiles, int k, int x, int y, TETile pattern) {

        for (int j = y; j < k + y; j++) {
            for (int i = x - j + y; i < x + k + j - y; i++) {
                tiles[i][HEIGHT - j] = pattern;
            }
        }
        for (int j = k + y; j < k * 2 + y; j++) {
            for (int i = (x - k + 1) + (j - y) % k; i < x + k * 2 - 1 - (j - y) % k; i++) {
                tiles[i][HEIGHT - j] = pattern;
            }
        }

    }

    public static void main(String[] args) {
        addHexagon();
    }
}

package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

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
        // (1,1) is the coordinate of the top left point, k is the number of dots in the first row

        // draw from the left to the right, first column
        drawHexagon(tiles, 3, 3, 7, Tileset.SAND);
        drawHexagon(tiles, 3, 3, 13, Tileset.TREE);
        drawHexagon(tiles, 3, 3, 19, Tileset.TREE);
        // second column
        drawHexagon(tiles, 3, 8, 4, Tileset.GRASS);
        drawHexagon(tiles, 3, 8, 10, Tileset.LOCKED_DOOR);
        drawHexagon(tiles, 3, 8, 16, Tileset.FLOOR);
        drawHexagon(tiles, 3, 8, 22, Tileset.FLOWER);

        // the third column
        drawHexagon(tiles, 3, 13, 1, Tileset.MOUNTAIN);
        drawHexagon(tiles, 3, 13, 7, Tileset.FLOOR);
        drawHexagon(tiles, 3, 13, 13, Tileset.PLAYER);
        drawHexagon(tiles, 3, 13, 19, Tileset.FLOOR);
        drawHexagon(tiles, 3, 13, 25, Tileset.GRASS);
        // the forth column
        drawHexagon(tiles, 3, 18, 4, Tileset.GRASS);
        drawHexagon(tiles, 3, 18, 10, Tileset.PLAYER);
        drawHexagon(tiles, 3, 18, 16, Tileset.FLOOR);
        drawHexagon(tiles, 3, 18, 22, Tileset.FLOWER);
        // the fifth column
        drawHexagon(tiles, 3, 23, 7, Tileset.WATER);
        drawHexagon(tiles, 3, 23, 13, Tileset.WALL);
        drawHexagon(tiles, 3, 23, 19, Tileset.TREE);

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
        if (k < 2) {
            throw new IllegalArgumentException("Hexagon size must be at least 2.");
        }
        // height is k*2
        int height = k * 2;
        // x = x-1;
        for (int row = 0; row < height; row++) {
            int rowY = row + y;
            int offset = row < k ? row : height - row - 1;
            int rowStartX = x - offset;
            int rowWidth = k + offset * 2;
            for (int i = 0; i < rowWidth; i++) {
                int xCoord = rowStartX + i;
                tiles[xCoord - 1][HEIGHT - rowY] = pattern; // here I reverse y,make the origin coordinate(1,1)
            }

        }
//        for (int j = y; j < k + y; j++) {
//            for (int i = x - j + y; i < x + k + j - y; i++) {
//                tiles[i][HEIGHT - j] = pattern;
//            }
//        }
//        for (int j = k + y; j < k * 2 + y; j++) {
//            for (int i = (x - k + 1) + (j - y) % k; i < x + k * 2 - 1 - (j - y) % k; i++) {
//                tiles[i][HEIGHT - j] = pattern;
//            }
//        }

    }

    public static void main(String[] args) {
        addHexagon();
    }
}

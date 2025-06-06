package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.List;
import java.util.Random;

public class World {
    private int width;
    private int height;
    private TETile[][] teTiles;
    private List<Room> rooms;
    private RoomGenerator roomGenerator;

    public World(int width, int height, long seed) {
        this.width = width;
        this.height = height;

        // create a game layout
        teTiles = new TETile[width][height];
        initializeTETile();
// create a roomGenerator
        roomGenerator = new RoomGenerator(width,height,seed);
        // start generating the room
        rooms = roomGenerator.generateRoom(teTiles);
    }

    // initialize teTiles with Nothing
    private void initializeTETile() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                teTiles[i][j] = Tileset.NOTHING;
            }
        }
    }

    // get TETile[][] teTiles
    public TETile[][] getTeTiles() {
        return teTiles;
    }

    // get list rooms
    public List<Room> getRooms(){
        return rooms;
    }
}
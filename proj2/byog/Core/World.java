package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class World {
    private int width;
    private int height;
    private Random random;
    private TETile[][] teTiles;
    public World(int width,int height,long seed){
        this.width = width;
        this.height = height;
        random = new Random(seed);

        // create a game layout
        teTiles = new TETile[width][height];
        initializeTETile();
    }
    // initialize teTiles with Nothing
    private void initializeTETile(){
        for (int i = 0;i<width;i++){
            for(int j= 0;j<height;j++){
                teTiles[i][j] = Tileset.NOTHING;
            }
        }
    }

    // get TETile[][] teTiles
    public TETile[][] getTeTiles(){
        return teTiles;
    }

}

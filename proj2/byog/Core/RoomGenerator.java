package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomGenerator {
    private int width;
    private int height;
    private Random random;
    private int maxRandomNumberOfRooms;

    public RoomGenerator(int width, int height, long seed) {
        this.width = width;
        this.height = height;
        random = new Random(seed);
        maxRandomNumberOfRooms = RandomUtils.uniform(random, 20, 25);
    }

    // draw teTiles
    public List<Room> generateRoom(TETile[][] teTiles) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < maxRandomNumberOfRooms; i++) {
            // generate a random room with random width, height, x, y
            int randomWidth = RandomUtils.uniform(random, 3, 6);
            int randomHeight = RandomUtils.uniform(random, 3, 6);
            int xPos = RandomUtils.uniform(random, 0, width - randomWidth - 1);
            int yPos = RandomUtils.uniform(random, 0, height - randomHeight - 1);
            // build a new room
            Room newRoom = new Room( xPos, yPos,randomWidth, randomHeight);
            // check if the room is valid
            if (isValid(newRoom, rooms)) {
                drawRoom(newRoom,teTiles);
                if (rooms.size() > 0) {
                    connectRooms(rooms, rooms.get(rooms.size() - 1), newRoom, teTiles);
                }
                rooms.add(newRoom);
            }else{
                i--;
            }
        }


        return rooms;
    }

    // check new room is valid or not
    private boolean isValid(Room newRoom, List<Room> rooms) {
        if (rooms.size() < 2) {
            return true;
        }
        // room compare with each room in the list
        for (Room r : rooms) {
            if (
                    (newRoom.getX() >= r.getX() + r.getWidth())
                            || (newRoom.getX() + newRoom.getWidth() <= r.getX())
                            || (newRoom.getY() >= r.getY() + r.getHeight())
                            || (newRoom.getY() + newRoom.getHeight() <= r.getY())

            ) {
                return true;

            }
        }
        return false;
    }

    private void drawRoom(Room room, TETile[][] teTiles) {
        // draw walls first
        for(int i = room.getX();i<room.getWidth()+room.getX();i++){
            for(int j = room.getY();j<room.getHeight()+room.getY();j++){
                if(teTiles[i][j] != Tileset.FLOOR){
                    teTiles[i][j] = Tileset.WALL;
                }

            }
        }
        // draw floor
        for(int i = room.getX()+1;i<room.getWidth()+room.getX()-1;i++){
            for(int j = room.getY()+1;j<room.getHeight()+room.getY()-1;j++){
                teTiles[i][j] = Tileset.FLOOR;
            }
        }
    }

    private void connectRooms(List<Room> rooms, Room prev, Room cur, TETile[][] teTiles) {
        // get the center of prev, cur
        int AcenterX = prev.getX() + prev.getWidth() / 2;
        int AcenterY = prev.getY() + prev.getHeight() / 2;
        int BcenterX = cur.getX() + cur.getWidth() / 2;
        int BcenterY = cur.getY() + cur.getHeight() / 2;

        // divide the "L" hallway into two parts, horizontal and vertical
        Room horizontal = new Room(Math.min(AcenterX, BcenterX)-1, AcenterY-1,Math.abs(AcenterX - BcenterX) + 3, 3);
        Room vertical = new Room(BcenterX -1, Math.min(AcenterY, BcenterY)-1,3, Math.abs(AcenterY - BcenterY) + 3);

        // add two parts to the list rooms
        rooms.add(horizontal);
        rooms.add(vertical);

        drawRoom(horizontal,teTiles);
       drawRoom(vertical,teTiles);
    }
}

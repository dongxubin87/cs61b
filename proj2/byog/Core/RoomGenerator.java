package byog.Core;

import byog.TileEngine.TETile;

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
        maxRandomNumberOfRooms = RandomUtils.uniform(random, 15, 20);
    }

    // draw teTiles
    public List<Room> generateRoom(TETile[][] teTiles) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < maxRandomNumberOfRooms; i++) {
            // generate a random room with random width, height, x, y
            int randomWidth = RandomUtils.uniform(random, 3, 7);
            int randomHeight = RandomUtils.uniform(random, 3, 7);
            int xPos = RandomUtils.uniform(random, 0, width - randomWidth - 1);
            int yPos = RandomUtils.uniform(random, 0, height - randomHeight - 1);
            // build a new room
            Room newRoom = new Room(randomWidth, randomHeight, xPos, yPos);
            // check if the room is valid
            if (isValid(newRoom, rooms)) {

                drawRoom(newRoom,teTiles);
                if (rooms.size() > 0) {
                    connectRooms(rooms, rooms.get(rooms.size() - 1), newRoom, teTiles);
                }
                rooms.add(newRoom);
            }
        }


        return null;
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

    private void drawRoom(Room room,TETile[][] teTiles) {

    }

    private void connectRooms(List<Room> rooms, Room prev, Room cur, TETile[][] teTiles) {
        // get the center of prev, cur
        int AcenterX = prev.getX() + prev.getWidth() / 2;
        int AcenterY = prev.getY() + prev.getHeight() / 2;
        int BcenterX = cur.getX() + cur.getWidth() / 2;
        int BcenterY = cur.getX() + cur.getWidth() / 2;

        // divide the "L" hallway into two parts, horizontal and vertical
        Room horizontal = new Room(Math.abs(AcenterX - BcenterX) + 2, 3, Math.min(AcenterX, BcenterX), AcenterY - 1);
        Room vertical = new Room(3, Math.abs(AcenterY - BcenterY) + 2, BcenterX - 1, Math.min(AcenterY, BcenterY));

        // add two parts to the list rooms
        rooms.add(horizontal);
        rooms.add(vertical);

        drawRoom(horizontal,teTiles);
    }
}

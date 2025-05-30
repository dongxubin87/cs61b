package byog.Core;

public class Room {
    private int width;
    private int height;
    private int x_room;
    private int y_room;

    // Room constructor
    public Room(int x_room, int y_room,int width,int height){
        this.width = width;
        this.height = height;
        this.x_room = x_room;
        this.y_room = y_room;
    }

    public int getX() {
        return x_room;
    }

    public int getY() {
        return y_room;
    }
    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}

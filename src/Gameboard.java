import java.security.PrivateKey;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

public class Gameboard {
    private ArrayList<Player> players;
    private double size;
    private final Tile[][] board;
    private Style style;
    Random rand = new Random();
    private final static int rotationLock = 45;

    public Gameboard(int size, Style style, ArrayList<Player> players) {
        setPlayers(players);
        setSize(size);
        generateBoard(size);
        setStyle(style);
        board = new Tile[(int) Math.sqrt(size)][(int) Math.sqrt(size)];

    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Style getStyle() {
        return style;
    }

    private boolean isFixed(){
        return false;

    }
    private void generateBoard(int size) {
        double numRows = Math.sqrt(size);
        double numColumns = Math.sqrt(size);
        for (int y = 0; y < numRows; y++) {
            Tile[] column = new Tile[(int) numColumns];
            for (int x = 0; x < numColumns; x++) {
                TileType tileType = "";
                int randAngle = (rand.nextInt(4) * rotationLock);
                column[x] = new Tile(new Coordinate(x, y), tileType, getStyle(), randAngle, isFixed());
            }
            board[y] = column;
        }
    }

}

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        Style style = new Style();
        ArrayList<Player> players = new ArrayList<>();
        Gameboard gameboard = new Gameboard(new int[]{10, 10}, style, players);
        gameboard.boardToString();
    }
}

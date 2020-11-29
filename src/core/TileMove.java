package core;

import holdables.Tile;

public class TileMove {

    public Gameboard2 tileMove(Gameboard2 gameboard, String rowOrColumn, int num){
        int xOrY;

        if (rowOrColumn.equals("Column")){
            Tile tempTile = gameboard.getTile(0, num);

            for (int y = 0; y < gameboard.getSize()[1]; y++) {
                gameboard.setTile(tempTile, y, num);
                tempTile = gameboard.getTile(y+1, num);
            }

        }
        else if (rowOrColumn.equals("Row")){
            Tile tempTile = gameboard.getTile(num, 0);

            for (int x = 0; x < gameboard.getSize()[1]; x++) {
                gameboard.setTile(tempTile,num, x );
                tempTile = gameboard.getTile(num, x+1);
            }
        }
        return gameboard;
    }
}

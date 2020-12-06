package players;

import constants.Angle;
import core.Coordinate;
import core.Gameboard;
import holdables.PlayerEffect;
import holdables.TileEffect;
import java.io.Serializable;
import players.TurnCounter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import holdables.Tile;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Moves the players
 *
 * @author FungWah Westley
 * @author Isabelle Ludwig
 * @author Jordy Martinson
 * @author Martin Samm
 * @version 1.14
 */

public class PlayerMovement implements Serializable {

    private Gameboard gameboard;    
    ArrayList<Integer> currentMovable = new ArrayList<Integer>();
    ArrayList<Integer> nextMovable = new ArrayList<Integer>();
    private Boolean[] alignsArr = new Boolean[4];
    private int count = 0;
    private TurnCounter TurnCounter;

    public PlayerMovement(Gameboard gameboard) {
        this.gameboard = gameboard;
    }


    /**
     * Checks the key input and translates it to coordinate change - controls whether the sprite is moving
     * @param key KeyEvent identifier
     * @param player players turn to move
     */
    public void keyPressed(KeyCode key, Player player){
        Tile tile = gameboard.getTiles().get(player.getCoordinate());
        switch(key){
            case W:
            case UP:
                moveCheck(player, tile, 2);
                break;
            case A:
            case LEFT:
                moveCheck(player, tile, 1);
                break;
            case S:
            case DOWN:
                moveCheck(player, tile, 0);
                break;
            case D:
            case RIGHT:
                moveCheck(player, tile, 3);
                break;
            case SPACE:
            case ENTER:
                TurnCounter.switchPlayer();
                break;
        }
    }

    //TODO Whenever the player's position changes, you need to tell the controller
    // to move the player's sprite to the new position
    /**
     * moves the player every time their coordinates change
     * @param player
     * @param coord
     */
    private void move (Player player, Coordinate coord){
        //this should call movePlayer from game controller
    }


    /**
     * Determines if move can be done
     * @param player player to move
     * @param tile current tile
     * @param direction direction to move in
     */
    private void moveCheck(Player player, Tile tile, int direction) {
        if(tilesAligned(player, this.gameboard)[direction]) {
            switch(direction) {
                case 0:
                    player.setCoordinate(tile.getSouthTile(gameboard).getCoordinate());
                    player.setCurrentDirection(Angle.LEFT);
                    break;
                case 1:
                    player.setCoordinate(tile.getWestTile(gameboard).getCoordinate());
                    player.setCurrentDirection(Angle.UP);

                    break;
                case 2:
                    player.setCoordinate(tile.getNorthTile(gameboard).getCoordinate());
                    player.setCurrentDirection(Angle.RIGHT);
                    break;
                case 3:
                    player.setCoordinate(tile.getEastTile(gameboard).getCoordinate());
                    player.setCurrentDirection(Angle.DOWN);
                    break;
            };
            count++;
        }
    }
    
    /**
     * Determines which directions the current tile allows movement in
     * Then checks the corresponding adjacent tiles to see if they line up
     * Assigns true or false to array according to which directions can be travelled in
     *
     * @param currentPlayer Player to input
     * @return boolean array of accessible direction
     */
    public Boolean[] tilesAligned(Player currentPlayer, Gameboard g){
        this.gameboard = g;
        Arrays.fill(alignsArr, false);
        Tile currentTile = gameboard.getTiles().get(currentPlayer.getCoordinate());
        checkAligns(currentTile, currentMovable);
        Tile[] adjTiles = adjacentTiles(currentTile);

        for(int i = 0; i < adjTiles.length; i++) {
            if(currentMovable.contains(i) && adjTiles[i] != null && !isPlayerOnTile(adjTiles[i]) && !isOnFire(adjTiles[i])) {
                checkAligns(adjTiles[i], nextMovable);

                int nextTileDir = i + 2;
                if(nextTileDir >= adjTiles.length) {
                    nextTileDir = i - 2;
                }

                if(nextMovable.contains(nextTileDir)) {
                    alignsArr[i] = true;
                }
                else {
                    alignsArr[i] = false;
                }
            }
        }
        return alignsArr;
    }
    
    /**
     * determines which direction a player can move in
     * @param tile Tile to check alignment of
     * @param list List to add movable tiles to
     * @return arraylist of movable directions
     */
    private ArrayList<Integer> checkAligns(Tile tile, ArrayList<Integer> list) {
        list.clear();
        switch(tile.getType()) {
            case STRAIGHT:
                switch(tile.getAngle()) {
                    case LEFT:
                    case RIGHT:
                        list.add(0);
                        list.add(2);
                        break;
                    default:
                        list.add(1);
                        list.add(3);
                        break;
                }
                break;
            case CORNER:
                switch(tile.getAngle()) {
                    case DOWN:
                        list.add(3);
                        list.add(0);
                        break;
                    case LEFT:
                        list.add(0);
                        list.add(1);
                        break;
                    case UP:
                        list.add(1);
                        list.add(2);
                        break;
                    case RIGHT:
                        list.add(2);
                        list.add(3);
                        break;
                }
                break;
            case JUNCTION:
                switch(tile.getAngle()) {
                    case DOWN:
                        list.add(3);
                        list.add(0);
                        list.add(1);
                        break;
                    case LEFT:
                        list.add(0);
                        list.add(1);
                        list.add(2);
                        break;
                    case UP:
                        list.add(1);
                        list.add(2);
                        list.add(3);
                        break;
                    case RIGHT:
                        list.add(2);
                        list.add(3);
                        list.add(0);
                        break;
                }
                break;
            case GOAL:
                list.add(0);
                list.add(1);
                list.add(2);
                list.add(3);
                break;
        }
        return list;
    }
    
    /**
     * Stores adjacent tiles as array
     *
     * @param currentTile Player's current tile
     * @return Array of adjacent tiles
     */
    private Tile[] adjacentTiles(Tile currentTile) {
        Tile[] adjTilesArr = new Tile[4];
        adjTilesArr[0] = currentTile.getSouthTile(gameboard);
        adjTilesArr[1] = currentTile.getWestTile(gameboard);
        adjTilesArr[2] = currentTile.getNorthTile(gameboard);
        adjTilesArr[3] = currentTile.getEastTile(gameboard);
        return adjTilesArr;
    }
    
    /**
     * Checks if another player is on the tile the player wants to move onto
     *
     * @param checkTile Tile to check for player
     * @return If other player is on next tile
     */
    private Boolean isPlayerOnTile(Tile checkTile) {
        for(int i = 0; i < gameboard.getPlayersCount(); i++) {
            if(gameboard.getPlayers(i).getCoordinate() == checkTile.getCoordinate()) { //may fail, not entirely sure how the matrix works now
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the next tile is on fire or not
     *
     * @param checkTile Tile to check for fire
     * @return If next tile is on fire
     */
    private boolean isOnFire(Tile checkTile) {
        return checkTile.getEffect() == TileEffect.FIRE;
    }

    /**
     * moves the player to the tile they were two turns ago.
     * If that tile is on fire, then they move to their previous tile
     * If their previous tile is on fire, they stay where they are.
     *
     * @param player Selected player
     */
    public void backMovement(Player player){
        Coordinate[] tiles = player.getLastTwoCoordinates();
        Tile previousTile = gameboard.getTiles().get(tiles[0]);
        Tile preferredTile = gameboard.getTiles().get(tiles[1]);
        if(!isOnFire(previousTile) && !isPlayerOnTile(previousTile)){
            if(!isOnFire(preferredTile) && !isPlayerOnTile(preferredTile)){
                player.setCoordinate(tiles[1]);
            } else {
                player.setCoordinate(tiles[0]);
            }
        }
    }


//TODO make any movement away from start square of turn a move and if you move back towards that square, you get the move back


}




package players;

import core.Coordinate;
import core.Gameboard;
import holdables.TileEffect;
//import players.TurnCounter;
import javafx.scene.input.KeyCode;
import holdables.Tile;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

/**
 * Moves the players
 *
 * @author FungWah Westley & Isabelle Ludwig
 * @author Jordy Martinson
 * @author Martin Samm
 * @version 1.0
 */

public class PlayerMovement {

    private Gameboard gameboard;
    private static int x;
    private static int y;
    private static int dx;
    private static int dy;
    
    ArrayList<Integer> currentMovable = new ArrayList<Integer>();
    ArrayList<Integer> nextMovable = new ArrayList<Integer>();
    private Boolean[] alignsArr = new Boolean[4];
    private final int MOVE_COUNT = 4;
    private int count = 0;
    private TurnCounter TurnCounter;

    /**
     * Checks the key input and translates it to coordinate change - controls whether the sprite is moving
     * @param e KeyEvent identifier
     * @param player players turn to move
     */
    public void keyPressed(KeyEvent e, Player player){
        Tile tile = gameboard.getTiles().get(player.getCoordinate());
        KeyCode key = e.getCode();

        switch(key){
            case W:
            case UP:
                dy = -1;
                moveCheck(player, tile, 2);
                break;
            case A:
            case LEFT:
                dx = -1;
                moveCheck(player, tile, 1);
                break;
            case S:
            case DOWN:
                dy = 1;
                moveCheck(player, tile, 0);
                break;
            case D:
            case RIGHT:
                dx = 1;
                moveCheck(player, tile, 3);
                break;
            case SPACE:
            case ENTER:
                //TurnCounter.switchPlayer();
                break;
        }
    }
    
    /**
     * Determines if move can be done
     * @param player player to move
     * @param tile current tile
     * @param direction direction to move in
     */
    private void moveCheck(Player player, Tile tile, int direction) {
        if(tilesAligned(player)[direction]) {
            if(!isOffBoard(player)) {
                player.setCoordinate(tile.getNorthTile(gameboard).getCoordinate());
                count++;
            }
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
    private Boolean[] tilesAligned(Player currentPlayer){
        Tile currentTile = gameboard.getTiles().get(currentPlayer.getCoordinate());
        checkAligns(currentTile, currentMovable);
        Tile[] adjTiles = adjacentTiles(currentTile);

        for(int i = 0; i < adjTiles.length; i++) {
            if(currentMovable.contains(i) && adjTiles[i] != null && !isPlayerOnTile(adjTiles[i]) && !isOnFire(adjTiles[i])) {
                checkAligns(adjTiles[i], nextMovable);

                int nextTileDir = i + 2;
                if(nextTileDir > adjTiles.length) {
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
     * @param tile
     * @param list
     * @return arraylist of moveable directions
     * Determines which direction a player can move in
     *
     * @param tile Tile to check allignment of
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
     * Checks if the player would be off the board
     *
     * @param p Player moving
     * @return If player would move off the board
     */
    private boolean isOffBoard(Player p) {
        if ((p.getCoordinate().getX() + dx > gameboard.getWidth()) || p.getCoordinate().getX() + dx < 0) {
            return true;
        }
        else if (p.getCoordinate().getX() + dy > gameboard.getHeight() || p.getCoordinate().getX() + dy < 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Moves the player to the opposite side of the board if moved off edge
     * @param p Player to move
     */
    private void loopPlayer(Player p) {
        if(dx == 1) {
            p.setCoordinate(new Coordinate(0, p.getCoordinate().getY()));
        }
        else if (dx == -1) {
            p.setCoordinate(new Coordinate(gameboard.getWidth(), p.getCoordinate().getY()));
        }
        else if(dy == 1) {
            p.setCoordinate(new Coordinate(p.getCoordinate().getY(), 0));
        }
        else {
            p.setCoordinate(new Coordinate(gameboard.getHeight(), p.getCoordinate().getY()));
        }
    }
    
    // TODO private method double move and backspace - pop coordinate stack

    /**
     * moves the player to the tile they were two turns ago.
     * If that tile is on fire, then they move to their previous tile
     * If their previous tile is on fire, they stay where they are.
     *
     * @param player Selected player
     */
    private void backMovement(Player player){
        Coordinate[] tiles = player.getLastTwoCoordinates();
        Tile previousTile = gameboard.getTiles().get(tiles[1]);
        Tile preferredTile = gameboard.getTiles().get(tiles[2]);
        if(!isOnFire(previousTile) && tiles[1] != null && !isPlayerOnTile(previousTile)){
            if(!isOnFire(preferredTile) && tiles[2] != null && !isPlayerOnTile(preferredTile)){
                player.setCoordinate(tiles[2]);
            } else {
                player.setCoordinate(tiles[1]);
            }
        } else {
            player.setCoordinate(tiles[0]);
        }
    }


    /**
     * player effect double move
     */
    private static void doubleMove() {
        //reverse turn counter one?
    }


    /**
     * Checks if tile is accessible by player
     *
     * @param currTile Current tile player is on
     * @param nextTile Tile to check for accessibility
     * @return If the next tile accessible
     */
    private Boolean isAccessible(Tile currTile, Tile nextTile) {
        if (!isOnFire(nextTile) && !isPlayerOnTile(nextTile)) {
            return true;
        } else {
            return false;
        }
    }

    //fungwah sent on discord
   /* private Boolean canPlayerEnterTile(Player currentPlayer, Tile selectedTile){
        if(gameboard.getPlayers(i).getCoordinate().equals(checkTile.getCoordinate())) {
            return !(TileEffect.FIRE || !(tilesAligned(currentPlayer)) || isPlayerOnTile(selectedTile));

        }
*/


//TODO move counter method 1-4 moves - old coord != new coord private static

    /**
     * counts the moves out of 4 a player is allowed to make per round
     */
//   private void moveCount(){
//        int count = 0;
//        while(isAccessible()){
//            //move
//            count++;
//        }
//    }


//TODO end turn (dw about this just yet tho)
//TODO make any movement away from start square of turn a move and if you move back towards that square, you get the move back


   public void Move(Player currentPlayer, int i){
       int count = 0;
       Boolean hasUserFinishedMoving = false;
       while (hasUserFinishedMoving || count <= 4) {

        }

        //update the final location of the player
        currentTile = tempMoveTile;

    }


    private Tile updateTempTile(Tile currentTile, Button up, Button down, Button left, Button right){
        Coordinate upCood, downCood, leftCood, righCood;
        //if up is pressed then
        upCood = new Coordinate(currentTile.getCoordinate().getX(),currentTile.getCoordinate().getY()+1);
        currentTile.setCoordinate(upCood);
        //if down is pressed then
        downCood = new Coordinate(currentTile.getCoordinate().getX(),currentTile.getCoordinate().getY()-1);
        currentTile.setCoordinate(downCood);
        //if left is pressed then
        leftCood = new Coordinate(currentTile.getCoordinate().getX()-1,currentTile.getCoordinate().getY());
        currentTile.setCoordinate(leftCood);
        //if right is pressed then
        righCood = new Coordinate(currentTile.getCoordinate().getX()-1,currentTile.getCoordinate().getY());
        currentTile.setCoordinate(leftCood);

        return currentTile;

    }
}
*/




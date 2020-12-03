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


//TODO key listener method


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
            }
            count++;
        }
    }
    
    /**
     * Determines which directions the current tile allows movement in
     * Then checks the corresponding adjacent tiles to see if they line up
     * Assigns true or false to array according to which directions can be travelled in
     * @param currentPlayer
     * @return boolean array of accessable direction
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
     * @param currentTile player's current tile
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
     * checks if another player is on the tile the player wants to move onto
     * @param checkTile
     * @return boolean (if pther player is on next tile)
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
     * checks if the next tile is on fire or not
     * @param checkTile
     * @return boolean (next tile is on fire)
     */
    private boolean isOnFire(Tile checkTile) {
        return checkTile.getEffect() == TileEffect.FIRE;
    }
    
    /**
     * Checks if the player would be off the board
     * @param p player moving
     * @return true if player would move off the board
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
     * @param player selected player
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
    private static void doubleMove(){
        //reverse turn counter one?
    }

    
    /**
     * checks if tile is accessible by player
     * @param currTile
     * @param nextTile
     * @return boolean (is the next tile accessible)
     */
    private Boolean isAccessible(Tile currTile, Tile nextTile){
        if(!isOnFire(nextTile) && !isPlayerOnTile(nextTile)){
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


    private Coordinate oldCoord(Player currentPlayer, int player){
        //can peek stack Player.coordinateHistory
        return currentPlayer.getCoordinate();
    }

    private static void newCoord(){
        //can push new coords onto stack Player.coordinateHistory
    }


/*
 If you make a method that takes in a keyEvent type from javafx.scene.input.KeyEvent;
 and decides what to do with it that'd be great. Or make an algorithm that gets
 the tiles Matrix from Gameboard and the direction of the keypress
 and returns how many places they can move in that direction?
 you should have a Gameboard attribute in PlayerMovement
 have a look at the Matrix class to figure out how to use it
 its in core


 player, the arraylist that contains the coordinates of the player will become a Stack (java.util.Collections)
 so when you want to add the current coord use push, when you want
 the current coord use peek and when you want the position n turns ago use elementAt(n-1) i think
 have a look at the api for it
*/


}






/*
    private int dx;
    private int dy;
    private int x;
    private int y;

//TODO all methods should be static
    //non static attribute - "private gameboard" and the initialise
    //do we just want the key codes or do we want the gameboard controller to just call a method here which makes it easier for us...


//TODO load in image??

    private void loadPlayer(int num) {
        Style.getPlayerImage(num);
    }






//could just if player presses up, check if that tile is accessible or not instead of checking all possible options - might be a lot faster

// we can only use this if using awt things is allowed which i dont know if it is...

// this is happening in gameboard controller (key listener) - need to accsess that instead of creating it ourselves :)

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT && tileAccessable() == true){
            dx += -1;
        }

        if (key == KeyEvent.VK_RIGHT && tileAccessable() == true) {
            dx += 1;
        }

        if (key == KeyEvent.VK_UP && tileAccessable() == true) {
            dy += -1;
        }

        if (key == KeyEvent.VK_DOWN && tileAccessable() == true) {
            dy += 1;
        }
    }


    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean tileAccessable(){
        boolean tilesAlign = true;
        if (tilesAlign && Tile.isOnFire() == false){
            return true;
        } else {
            return false;
        }
    }

    //   Tile nextMoveTile = null;


    private Gameboard gameboard;

    //TODO gets current player position

    Tile currentTile = gameboard.getTile(Player.getCoordinate().getX(),Player.getCoordinate().getY());
    //        (1,2)
    //  (0,1) (1,1) (2,1)
    //        (1,0)


    //if(currentTile.)



//TODO check if tile is on fire

    if(nextMoveTile.isOnFire()){
        //block user from e}else if (nextMoveTile.rotaion == 1 || ...){
    }


    //currentPlayerPosition


    //not really sure where this came from but shes here now
    public Coordinate getCoordinate(int num) {
        return Player.coordinateArray.get(num);
    }



//TODO loop that checks who many tiles the player can still move out of 4

    Int count = 0;
    Boolean hasUserClickedTile = false
    while (hasUserClickedTile == false || count < 4) {}



//TODO check if surrounding tiles are accessible from current tile - there is a method that does this in tileType

    if(
    void nextMoveTile(){
        //block user from entering
    }else{

    } if(nextMoveTile.rotation()) {

    }

            //    public Coordinate getCoordinate(int i) { return coordinateArray.get(i);  }
            //    Boolean hasUserClickedTile = false;




//TODO take input from player - arrow keys or click on tile
//TODO check for double move? - does that happen in this class or another??????????
//TODO check for backwards movements for selected player - again does that happen in this class or another??????????
//TODO check if tile player wants to move to isn´t being occupied by another player








    public void Move(Player currentPlayer, int i){
        Tile currentTile;
        currentTile.setCoordinate(currentPlayer.getCoordinate(i));

        Tile tempMoveTile = null;
        //gamerBoardController will have buttons I guess? using these for now
        Button up = null;
        Button down = null;
        Button left = null;
        Button right = null;

        if(currentTile.getAngle() == ){
            //if the current tile has multiple directions, eg. player can go north and west
            //disable up and left arrows until accessible


        }

        int count = 0;
        Boolean hasUserFinishedMoving = false;

        while (hasUserFinishedMoving || count <= 4) {
            // if any of the arrow has been pressed then
            //update tempMoveTile to the location of the next tile
            if(!(updateTempTile(currentTile,up,down,left,right).isOnFire()){
                currentPlayer.= updateTempTile(currentTile,up,down,left,right);
                count++;
            } else {
                //block user from pressing an arrow to that tile.
            }
            //not sure how the user will tell the game the moves chosen has finished but
            //maybe button click done? then hasUserFinsihedMoving = true;

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




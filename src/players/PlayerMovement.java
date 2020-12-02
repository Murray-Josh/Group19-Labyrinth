package players;

import core.Coordinate;
import core.Gameboard;
import holdables.PlayerEffect;
import holdables.TileEffect;
import players.TurnCounter;
import javafx.scene.input.KeyCode;
import holdables.Tile;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Moves the players
 *
 * @author FungWah Westley & Isabelle Ludwig
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


//TODO key listener method


    /**
     * checks the key input and translates it to coordinate change - controls whether the sprite is moving
     * @param e KeyEvent identifier
     */
    public static void keyPressed(KeyEvent e){
        KeyCode key = e.getCode();

        switch(key){
            case W:
            case UP:
                dy = -1;
                break;
            case A:
            case LEFT:
                dx = -1;
                break;
            case S:
            case DOWN:
                dy = 1;
                break;
            case D:
            case RIGHT:
                dy = 1;;
                break;
            case SPACE:
            case ENTER:
                TurnCounter.switchPlayer();
                break;
        }
    }

    /**
     * changes players coordinates
     */
    public void move(){
            x += dx;
            y += dy;
        }


        // both of these methods occur in Coordinate.java as well - might be easier to just call them from there
    /**
     * gets x coordinate
     * @return x coordinate
     */
    public int getX(){
            return x;
        }

    /**
     * gets y coordinate
     * @return y coordinate
     */
    public int getY(){
            return y;
        }



   /* private Boolean canPlayerEnterTile(Player currentPlayer, Tile selectedTile){
        if(gameboard.getPlayers(i).getCoordinate().equals(checkTile.getCoordinate())) {
            return !(TileEffect.FIRE || !(tilesAligned(currentPlayer)) || isPlayerOnTile(selectedTile));

        }
*/


// TODO private method double move and backspace - pop coordinate stack


    private static void backMovement(){

    }

    private static void doubleMove(){

    }


    private Boolean isPlayerOnTile(Tile checkTile) {
        for(int i = 0; i < gameboard.getPlayersCount(); i++) {
            if(gameboard.getPlayers(i).getCoordinate() == checkTile.getCoordinate()) { //may fail, not entirely sure how the matrix works now
                return true;
            }
        }
        return false;
    }

    //Checks current tile's directions against surrounding tiles' directions. Returns boolean array
    private Boolean[] tilesAligned(Player currentPlayer){
        Tile currentTile = gameboard.getTiles().get(currentPlayer.getCoordinate());
        Coordinate prevCoord = currentTile.getCoordinate();
        checkAligns(currentTile, currentMovable);
        Boolean[] alignsArr = new Boolean[4];

        //May fail at board edges
        Tile southTile = this.gameboard.getTiles().get(currentTile.getCoordinate().getX(), currentTile.getCoordinate().getY()+1);
        Tile westTile = this.gameboard.getTiles().get(currentTile.getCoordinate().getX()-1, currentTile.getCoordinate().getY());
        Tile northTile = this.gameboard.getTiles().get(currentTile.getCoordinate().getX(), currentTile.getCoordinate().getY()-1);
        Tile eastTile = this.gameboard.getTiles().get(currentTile.getCoordinate().getX()+1, currentTile.getCoordinate().getY());

        //First checks if the current tile allows for travel in a direction,
        //then checks if next tile allows for travel in the opposite direction
        if(currentMovable.contains(0) && !isPlayerOnTile(southTile) && !isOnFire(southTile)) {
            checkAligns(southTile, nextMovable);
            if(nextMovable.contains(2)) {
                alignsArr[0] = true;
            }
        }
        if(currentMovable.contains(1) && !isPlayerOnTile(westTile) && !isOnFire(westTile)) {
            checkAligns(westTile, nextMovable);
            if(nextMovable.contains(3)) {
                alignsArr[1] = true;
            }
        }
        if(currentMovable.contains(2) && !isPlayerOnTile(northTile) && !isOnFire(northTile)) {
            checkAligns(northTile, nextMovable);
            if(nextMovable.contains(0)) {
                alignsArr[2] = true;
            }
        }
        if(currentMovable.contains(3) && !isPlayerOnTile(eastTile) && !isOnFire(eastTile)) {
            checkAligns(eastTile, nextMovable);
            if(nextMovable.contains(1)) {
                alignsArr[3] = true;
            }
        }
        return alignsArr;
    }

    private boolean isOnFire(Tile checkTile) {
        return checkTile.getEffect() == TileEffect.FIRE;
    }

    //Nested switch statements to determine which directions the player can move in
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
                        list.add(1);
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

//TODO move counter method 1-4 moves - old coord != nodprivate static static

    private static void moveCount(){
        int count = 0;
        while(isAccessable() && count <= 4 && canPlayerEnterTile()){
            //move
            count++;
        }
    }


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




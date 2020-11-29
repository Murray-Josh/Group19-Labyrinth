package players.dontwork;

import com.sun.media.jfxmedia.events.PlayerEvent;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import core.Coordinate;
import players.Player;
import styles.CarStyle;
import styles.Style;
import holdables.Tile;
import java.awt.event.KeyEvent;

/**
 * Moves the players
 *
 * @author FungWah Westley & Isabelle Ludwig
 * @version 1.0
 */

public class PlayerMovement () {

    private int dx;
    private int dy;
    private int x;
    private int y;




//TODO load in image??

    private void loadPlayer() {
        Style.getPlayerImage();
    }






//could just if player presses up, check if that tile is accessible or not instead of checking all possible options - might be a lot faster

// we can only use this if using awt things is allowed which i dont know if it is...

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT && tileAccessable() == true){
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT && tileAccessable() == true) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP && tileAccessable() == true) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN && tileAccessable() == true) {
            dy = 1;
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
        if (tilesAlign && Tile.isOnFire() == false){
            return true;
        } else {
            return false;
        }
    }













    //   Tile nextMoveTile = null;


    //TODO gets current player position
    Tile currentTile = Gameboard.getTile(Player.getCoordinate().getX(),Player.getCoordinate().getY());
    //        (1,2)
    //  (0,1) (1,1) (2,1)
    //        (1,0)


    //if(currentTile.)



//TODO check if tile is on fire
    if(nextMoveTile.isOnFire()){
        //block user from e}else if (nextMoveTile.rotaion == 1 || ...){
        move there
    }


    //currentPlayerPosition


    //not really sure where this came from but shes here now
    public Coordinate getCoordinate(int i) {
        return coordinateArray.get(i);
    }



//TODO loop that checks who many tiles the player can still move out of 4
    //Int count = 0;
    //Boolean hasUserClickedTile = false
    //while (hasUserClickedTile == false || count < 4) {}



//TODO check if surrounding tiles are accessible from current tile - there is a method that does this in tileType
    if(nextMoveTile()){
        //block user from entering
    }else{

    } if(nextMoveTile.rotation))

            //    public Coordinate getCoordinate(int i) { return coordinateArray.get(i);  }
            //    Boolean hasUserClickedTile = false;
            while




//TODO take input from player - arrow keys or click on tile
//TODO check for double move? - does that happen in this class or another??????????
//TODO check for backwards movements for selected player - again does that happen in this class or another??????????
//TODO check if tile player wants to move to isn´t being occupied by another player



}


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
            }else{
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
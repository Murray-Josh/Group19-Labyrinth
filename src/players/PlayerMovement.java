package players;

import com.sun.media.jfxmedia.events.PlayerEvent;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import core.Coordinate;
import players.Player;
import styles.CarStyle;
import styles.Style;
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

        if (key == KeyEvent.VK_LEFT && tileAccess() == true){
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
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

    private boolean tileAccess(){
        if (tilesAlign){
            return true;
        } else if{

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

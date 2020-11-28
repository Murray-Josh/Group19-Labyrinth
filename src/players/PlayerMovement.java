package players;

import com.sun.media.jfxmedia.events.PlayerEvent;
import com.sun.media.jfxmedia.events.PlayerStateEvent;
import core.Coordinate;
import players.Player;

/**
 * Moves the players
 *
 * @author FungWah Westley & Isabelle Ludwig
 * @version 1.0
 */

public class PlayerMovement () {
    Tile nextMoveTile = null;

//TODO gets current player position
    Tile currentTile = Gameboard.getTile(Player.getCoordinate().getX(),Player.getCoordinate().getY());
    //        (1,2)
    //  (0,1) (1,1) (2,1)
    //        (1,0)

    if(currentTile.)

//TODO check if tile is on fire
    if(nextMoveTile.isOnFire()){
         //block user from e}else if (nextMoveTile.rotaion == 1 || ...){
        move there
    }

    //currentPlayerPosition//


    public Coordinate getCoordinate(int i) {
        return coordinateArray.get(i);
    }

//TODO loop that checks who many tiles the player can still move
    //Int count = 0;
    //Boolean hasUserClickedTile = false
    //while (hasUserClickedTile == false || count < 4) {}

    if(nextMoveTi()){
        //block user from entering
    }else{

        } if(nextMoveTile.rotation))

    //    public Coordinate getCoordinate(int i) { return coordinateArray.get(i);  }
        //    Boolean hasUserClickedTile = false;
    while


    /**
    - find player coordinate
    - check four next places
            - check for fire
    - listens for key input
    - moves the player as many places as they can in that direction until they cant anymore or they've already moved 4 places
    - it also needs to take into account effects, but i havent got around to fixing them yet
     */

}

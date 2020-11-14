//automatically did this - needs changing
import javafx.scene.image.Image;

import javax.swing.text.GapContent;
import javax.swing.text.html.ImageView;

/**
 *
 *
 * @author Aaron Davies and Isabelle Ludwig
 * @version 1.0
 */
public class Style {

    private Image PLAYER_ONE;
    private Image PLAYER_TWO;
    private Image PLAYER_THREE;
    private Image PLAYER_FOUR;
    private Image STRAIGHT_TILE;
    private Image CORNER_TILE;
    private Image JUNCTION_TILE;
    private Image GOAL_TILE;
    private Image GAMEBOARD_BORDER;


    public PirateImages(){
        Image PiratePlayer1Icon = new Image ("patrick.jpg")
        Image PirateCornerPiece = new Image ("patrick.jpg")
    }
    public Image getPlayer_ONE(){
        return PLAYER_ONE;
    }
    public Image getCORNER_TILE(){
        return CORNER_TILE;
    }
    public Image getJUNCTION_TILE(){
        return JUNCTION_TILE;
    }
    public Image getGOAL_TILE(){
        return GOAL_TILE;
    }
    public Image getGAMEBOARD_BORDER(){
        return GAMEBOARD_BORDER;
    }
}

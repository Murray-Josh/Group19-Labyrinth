package controllers;

import constants.ErrorMsg;
import constants.Title;
import constants.Window;
import core.Gameboard;
import core.Level;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import players.Player;
import styles.Style;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GameboardController implements InitialiseWithArgs {
    @FXML
    private GridPane grdBoard;
    @FXML
    private VBox vboxPlayers;
    @FXML
    private VBox vboxEffects;
    @FXML
    private Button cmdActivate;
    @FXML
    private Label lblStatus;
    @FXML
    private Button cmdSilkBag;

    private Gameboard board;

    private ArrayList<Player> players;

    private Style style;

    private Level level;
    private Gameboard gameboard;

    public void cmdActionClick(MouseEvent mouseEvent) {

    }

    private void formatPlayers(){
        for(int pNum = 0; pNum < board.getPlayersCount(); pNum ++) {
            Label label = new Label("Player: " + pNum);
            Pane paneP1 = new Pane(label);
            paneP1.getChildren().add(createPlayerImageVbox(pNum));
            vboxPlayers.getChildren().add(paneP1);
        }
    }

    private VBox createPlayerImageVbox(int playerNum){
        ImageView playerImView = new ImageView();
        playerImView.setImage(board.getPlayers(playerNum).getPlayerImage());
        VBox vboxP1 = new VBox();
        vboxP1.getChildren().add(playerImView);
        return vboxP1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources, Object[] args) {
        formatPlayers();
        this.gameboard = (Gameboard) args[1];
    }


    /*
    public void keyPressed(KeyEvent keyEvent) {
        keyEvent.
    }

     */
}

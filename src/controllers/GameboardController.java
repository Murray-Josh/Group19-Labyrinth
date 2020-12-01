package controllers;

import constants.ErrorMsg;
import constants.Title;
import constants.Window;
import core.Gameboard;
import core.Level;
import core.Matrix;
import core.Matrix;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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


public class GameboardController implements InitialiseWithArgs, Initializable {
    @FXML
    private GridPane grdBoard;
    @FXML
    private VBox     vboxPlayers;
    @FXML
    private VBox     vboxEffects;
    @FXML
    private Button   cmdActivate;
    @FXML
    private Label    lblStatus;
    @FXML
    private Button   cmdSilkBag;

    private Gameboard board;

    private ArrayList<Player> players;

    private Style style;

    private Level     level;
    private Gameboard gameboard;

    public void cmdActionClick(MouseEvent mouseEvent) {

    }

    /*
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
    *
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatPlayers();

    }

    @Override
    public void initializeWithArgs(Object[] args) {
        this.gameboard = (Gameboard) args[1];
        gameboard.getTiles().forEach(tile -> {
            System.out.println(tile.toString());
            ImageView image = new ImageView();
            image.setImage(tile.getImage());
            image.setRotate(tile.getAngle().get());
            grdBoard.add(image, tile.getCoordinate().getX(), tile.getCoordinate().getY());
        });

    }


    private void formatPlayers() {
        this.vboxPlayers.setAlignment(Pos.CENTER);
        ArrayList<Player> players = this.gameboard.getPlayers();
        players.forEach(player -> {
            ImageView image = new ImageView(player.getPlayerImage());
            Label name = new Label(player.getProfile().getName());
            VBox playerPicture = new VBox(image, name);
            VBox.setMargin(image, new Insets(4, 0, 4, 0));
            VBox.setMargin(name, new Insets(4, 4, 4, 4));
            this.vboxPlayers.getChildren().add(playerPicture);
        });
    }
}

    /*
    public void keyPressed(KeyEvent keyEvent) {
        keyEvent.
    }

     */


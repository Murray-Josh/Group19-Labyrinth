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


public class GameboardController implements Initializable {
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

    public void cmdActionClick(MouseEvent mouseEvent) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatPlayers();


    }

    private void getLevel(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(new File(path));
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            Level level = (Level) objIn.readObject();
            importLevelData(path);
        } catch (Exception e) {
            e.printStackTrace();
            StageController.showError(ErrorMsg.LEVEL_READ_ERROR, Title.ERROR, false);
            StageController.changeScene(Window.SETUP);
        }
    }

    private void importLevelData(String levelPath) throws IOException, ClassNotFoundException {
        board = new Gameboard(levelPath,style) ;
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


    /*
    public void keyPressed(KeyEvent keyEvent) {
        keyEvent.
    }

     */
}

package controllers;

import constants.ErrorMsg;
import constants.Title;
import constants.Window;
import core.Gameboard;
import core.Level;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
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


    public void cmdActionClick(MouseEvent mouseEvent) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void getLevel(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(new File(path));
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            Level level = (Level) objIn.readObject();
            importLevelData(level);
        } catch (Exception e) {
            e.printStackTrace();
            StageController.showError(ErrorMsg.LEVEL_READ_ERROR, Title.ERROR, false);
            StageController.changeScene(Window.SETUP);
        }
    }

    private void importLevelData(Level level) {
        board = new Gameboard();
    }

    public void keyPressed(KeyEvent keyEvent) {
    }
}

package fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import core.FindMessage;

public class MainMenuController {
    @FXML
    private Button cmdStartGame;
    @FXML
    private Button cmdLoadGame;
    @FXML
    private Button cmdScoreboard;
    @FXML
    private Button cmdMakeProfile;
    @FXML
    private Button cmdMore;
    @FXML
    private Button cmdQuit;
    @FXML
    private ImageView logo;
    @FXML
    private Label msgDayMessage;

    public MainMenuController() {
        this.msgDayMessage.setText(FindMessage.getMessage());
    }

    public void newGameClicked(MouseEvent mouseEvent) {

    }

    public void loadGameClicked(MouseEvent mouseEvent) {
    }

    public void scoreboardClicked(MouseEvent mouseEvent) {
    }

    public void profileClicked(MouseEvent mouseEvent) {
    }

    public void moreClicked(MouseEvent mouseEvent) {

    }

    /**
     * Exits the Game
     *
     * @param mouseEvent When the quit button is clicked
     */
    public void quitClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }
}

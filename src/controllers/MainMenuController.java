package controllers;

import constants.Window;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import players.Profiles;

import java.net.URL;
import java.util.ResourceBundle;

import static controllers.StageController.*;

public class MainMenuController implements Initializable {
    @FXML
    private Button cmdNew;
    @FXML
    private Button cmdLoad;
    @FXML
    private Button cmdScoreboard;
    @FXML
    private Button cmdMakeProfile;
    @FXML
    private Button cmdDeleteProfile;
    @FXML
    private Button cmdQuit;
    @FXML
    private ImageView logo;
    @FXML
    private Label msgDayMessage;



    public void quitClicked(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void cmdDeleteProfileClick(MouseEvent mouseEvent) {
        Profiles.deleteProfileDialog();
    }

    public void cmdCreateProfileClick(MouseEvent mouseEvent) {
        Profiles.addProfileDialog();
    }

    public void cmdScoreboardClick(MouseEvent mouseEvent) {
        changeScene(Window.SCOREBOARD);
    }

    public void cmdLoadClick(MouseEvent mouseEvent) {
        changeScene(Window.LOAD);
    }

    public void cmdNewClick(MouseEvent mouseEvent) {
        changeScene(Window.SETUP);
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.msgDayMessage.setText(FindMessage.getMessage());
    }
}

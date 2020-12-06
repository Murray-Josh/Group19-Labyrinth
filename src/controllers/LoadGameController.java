package controllers;

import static controllers.StageController.changeScene;
import static controllers.StageController.home;
import static controllers.StageController.showError;

import constants.ErrorMessage;
import constants.Title;
import constants.Window;
import core.Save;
import core.Save.Key;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * Controls and handles the LoadGame scene
 *
 * @author Joseph Omar
 * @version 1.0
 */
@SuppressWarnings("unused")
public class LoadGameController implements Initializable {

    private static final Title title = Title.SAVE;
    @FXML
    private Button cmdRefresh;
    @FXML
    private Button cmdLoad;
    @FXML
    private Button cmdCancel;
    @FXML
    private ListView<File> lstSaves;

    private File selected;

    /**
     * refreshes the list content
     *
     * @param actionEvent Event
     */
    public void cmdRefreshClick(ActionEvent actionEvent) {
        refresh();
    }

    private void refresh() {
        lstSaves.getItems().clear();
        getSaves().forEach(file -> {
            try {
                System.out.println(file.getCanonicalFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        lstSaves.getItems().setAll(FXCollections.observableArrayList(getSaves()));
    }


    /**
     * Finds all the files in the save folder that have the extension .labyrinth and adds them to a
     * list
     *
     * @return List of save files
     */
    private ArrayList<File> getSaves() {
        File dir = new File("saves");
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(dir.listFiles())));
    }

    /**
     * Handles the event that occurs when a list item is selected
     *
     * @param mouseEvent Event
     */
    public void lstSavesClick(MouseEvent mouseEvent) {
        selected = lstSaves.getSelectionModel().getSelectedItem();

    }

    /**
     * Handles the event that occurs when load game is clicked. It checks if an item is selected and
     * if it is, sends the selected save file to the Logic class
     *
     * @param actionEvent Event
     */
    public void cmdLoadClick(ActionEvent actionEvent) {
        HashMap<Key, Object> data;
        try {
            data = Save.load(selected);
            changeScene(Window.BOARD, new Object[]{true, data});
        } catch (IOException | ClassNotFoundException e) {
            showError(ErrorMessage.SAVE_READ_ERROR, Title.LOAD, false);
            e.printStackTrace();
        }
    }

    /**
     * goes back to the main menu
     *
     * @param actionEvent Event
     */
    public void cmdCancelClick(ActionEvent actionEvent) {
        home();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }
}

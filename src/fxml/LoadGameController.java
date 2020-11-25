package fxml;

import core.Logic;
import core.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadGameController {
    @FXML
    private Button cmdRefresh;
    @FXML
    private Button cmdLoad;
    @FXML
    private Button cmdCancel;
    @FXML
    private ListView lstSaves;

    private File selected;
    public LoadGameController() {
        lstSaves.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
   setListData();
    }
    private List<File> getSaves() {
        File dir =  new File("/saves");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File[] dirFilteredContents = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".labyrinth");
            }
        });
        List<File> dirContentsList = Arrays.asList(dirFilteredContents);
        return dirContentsList;
    }

    private void setListData() {
        ObservableList<String> observableList = FXCollections.observableList(getSaves().forEach((File) -> toString()))
        lstSaves.setItems(observableList);
    }

    public void cmdRefreshClick(ActionEvent actionEvent) {
        setListData();
    }


    public void lstSavesClick(MouseEvent mouseEvent) {
        selected = (File) lstSaves.getSelectionModel().getSelectedItem();
    }

    public void cmdLoadClick(ActionEvent actionEvent) {
        if (selected == null && lstSaves.getSelectionModel().getSelectedItem() == null) {
            Main.showError("A Save file is needed for loading a game from file. Please select a Save to load.", "No Save Selected", "Load Save", false);
        } else {
            Logic.createGame(selected);
        }
    }
}

package controllers;

import core.Gameboard;
import holdables.Tile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MoveTileDialogController implements InitialisableWithParameters {
    public static final String COLUMN = "Column";
    public static final String ROW = "Row";
    @FXML
    private Button cmdConfirm;
    @FXML
    private ChoiceBox<String> comDirection;
    @FXML
    private ChoiceBox<Integer> comNumber;
    @FXML
    private Label lblLeft;
    @FXML
    private Label lblRight;
    @FXML
    private GridPane grdTiles;

    private Tile tile;
    private Gameboard gameboard;
    private GameboardController gameboardController;

    @Override
    public void initialiseWithParameters(Object[] parameters) {
        this.tile = (Tile) parameters[0];
        this.gameboard = (Gameboard) parameters[1];
        this.gameboardController = (GameboardController) parameters[2];

        this.grdTiles.getColumnConstraints().clear();
        this.grdTiles.getRowConstraints().clear();

        addListeners();
    }


    /**
     * Adds Events to the Choice boxes that trigger an event when the selected item changes
     */
    private void addListeners() {
        comDirection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateNumbers(newValue);
            }

    });
        comNumber.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                updateGrid(comDirection.getSelectionModel().getSelectedItem(), newValue);
            }
        });
    }

    /**
     * Update the available column/row numbers in the choicebox
     * @param axis Whether the numbers to display correspond to columns or rows
     */
    private void updateNumbers(String axis) {
    }

    /**
     * update the gridpane based on the axis and number selected
     */
    private void updateGrid(String axis, int number) {
        if (axis.equals(COLUMN)) {

        } else if (axis.equals(ROW)) {

        }
    }


    public void cmdConfirmClicked(MouseEvent mouseEvent) {
    }
}

package controllers;

import core.Gameboard;
import core.Matrix;
import holdables.Tile;
import holdables.TileEffect;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Controller class for the Select Tile Dialog. Allows the player to chose and apply a TileEffect to
 * a 3X3 grid of tiles.
 *
 * @author Joseph Omar
 * @version 1.2
 */
public class SelectTileDialogController implements InitialisableWithParameters {

   /**
    * Strings to be used in the window formatting
    */
   private static final String TITLE = "Select a Tile to ";
   private static final String FREEZE = "FREEZE";
   private static final String IGNITE = "IGNITE";
   private static final String FREEZE_LOWER = "Freeze";
   private static final String IGNITE_LOWER = "Ignite";
   private static final String HEADER = "SELECT A TILE TO ";

   /**
    * FXML controls being modified by the controller
    */
   @FXML
   public Label lblTitle;
   @FXML
   private GridPane grdTiles;
   @FXML
   private ChoiceBox<Integer> comColumn;
   @FXML
   private ChoiceBox<Integer> comRow;
   @FXML
   private Button cmdOK;

   /**
    * Class-wide fields
    */
   private Gameboard gameboard;
   private TileEffect effect;
   private Stage stage;
   private Integer selectedColumn;
   private Integer selectedRow;
   private GameboardController controller;
   private Matrix<Tile> range;

   /**
    * Initialises the Select Tile Window using a set of parameters passed from the calling
    * controller.
    *
    * @param parameters Parameters needed by the Select Tiles Window (GameBoard, Effect, Calling
    *                   Controller)
    * @param scene      This Window
    * @param stage      This windows's stage
    */
   @Override
   public void initialiseWithParameters(Object[] parameters, Scene scene, Stage stage) {
      this.gameboard = (Gameboard) parameters[0];
      this.effect = (TileEffect) parameters[1];
      this.controller = (GameboardController) parameters[2];
      this.stage = stage;
      if (effect.equals(TileEffect.ICE)) {
         stage.setTitle(TITLE + FREEZE_LOWER);
         cmdOK.setText(FREEZE);
         lblTitle.setText(HEADER + FREEZE);
      } else {
         stage.setTitle(TITLE + IGNITE_LOWER);
         cmdOK.setText(IGNITE);
         lblTitle.setText(HEADER + IGNITE);
      }
      cmdOK.setDisable(true);
      stage.setResizable(false);
      stage.centerOnScreen();
      startChoiceBoxListeners();
      populateChoiceBoxes();
   }

   /**
    * Adds all the rows and columns on the gameboard to the choice boxes
    */
   private void populateChoiceBoxes() {
      comColumn.getItems().clear();
      comRow.getItems().clear();
      for (int i = 0; i < gameboard.getWidth(); i++) {
         comColumn.getItems().add(i);
      }
      for (int i = 0; i < gameboard.getHeight(); i++) {
         comRow.getItems().add(i);
      }
   }

   /**
    * Creates new event listeners to trigger a method when the selected item is changed
    */
   private void startChoiceBoxListeners() {
      comColumn.getSelectionModel().selectedItemProperty()
           .addListener((observable, oldValue, newValue) -> {
              this.selectedColumn = newValue;
              populate();
           });
      comRow.getSelectionModel().selectedItemProperty()
           .addListener(((observable, oldValue, newValue) -> {
              this.selectedRow = newValue;
              populate();
           }));
   }

   /**
    * Uses the selected items from the choice boxes to get a 3x3 grid around the selected tile
    */
   private void populate() {
      if (selectedColumn != null && selectedRow != null) {
         Tile selectedTile = gameboard.getTiles().get(selectedColumn, selectedRow);
         range = new Matrix<>(3, 3);
         range.set(1, 1, selectedTile);
         int selectedX = selectedTile.getCoordinate().getX();
         int selectedY = selectedTile.getCoordinate().getY();
         /*One Above*/
         if (selectedY != 0) {
            range.set(1, 0, gameboard.getTiles().get(selectedX, selectedY - 1));
         }
         /*One Below*/
         if (selectedY != gameboard.getHeight() - 1) {
            range.set(1, 2, gameboard.getTiles().get(selectedX, selectedY + 1));
         }
         /*Three to Left*/
         if (selectedX != 0) {
            range.set(0, 1, gameboard.getTiles().get(selectedX - 1, selectedY));
            if (selectedY != 0) {
               if (selectedY != gameboard.getHeight() - 1) {
                  range.set(0, 2, gameboard.getTiles().get(selectedX - 1, selectedY + 1));
               }
               range.set(0, 0, gameboard.getTiles().get(selectedX - 1, selectedY - 1));
            } else {
               range.set(0, 2, gameboard.getTiles().get(selectedX - 1, selectedY + 1));
            }
         }
         /*Three to right*/
         if (selectedX != gameboard.getWidth() - 1) {
            range.set(2, 1, gameboard.getTiles().get(selectedX + 1, selectedY));
            if (selectedY != 0) {
               if (selectedY != gameboard.getHeight() - 1) {
                  range.set(2, 2, gameboard.getTiles().get(selectedX + 1, selectedY + 1));
               }
               range.set(2, 0, gameboard.getTiles().get(selectedX + 1, selectedY - 1));
            } else {
               range.set(2, 2, gameboard.getTiles().get(selectedX + 1, selectedY + 1));
            }
         }

         /* define a 3X3 grid */
         grdTiles.getColumnConstraints().clear();
         grdTiles.getRowConstraints().clear();
         for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints();
            ColumnConstraints column = new ColumnConstraints();
            row.setPercentHeight((double) 100 / 3);
            column.setPercentWidth((double) 100 / 3);
            grdTiles.getRowConstraints().add(row);
            grdTiles.getColumnConstraints().add(column);
         }

         /* put each tile in the grid */
         for (int i = 0; i < range.getWidth(); i++) {
            for (int j = 0; j < range.getHeight(); j++) {
               if (range.get(i, j) != null) {
                  Tile tile = range.get(i, j);
                  grdTiles.add(MoveTileDialogController
                       .createImageView(tile.getImage(), tile.getAngle().get()), i, j);
               }
            }
         }
         cmdOK.setDisable(false);
      }
   }

   /**
    * Handles the event of the OK button being clicked. Adding all the affected tiles to a
    * collection and giving it back to the parent controller, and then closing the dialog.
    *
    * @param mouseEvent Event received
    */
   @SuppressWarnings("unused")
   public void cmdOKClick(MouseEvent mouseEvent) {
      if (selectedColumn != null && selectedRow != null) {
         ArrayList<Tile> tiles = new ArrayList<>();
         range.forEach(tiles::add);
         controller.applyEffect(effect, tiles);
         stage.close();
      } else {
         cmdOK.setDisable(true);
      }
   }
}

package controllers;

import static controllers.StageController.changeScene;
import static controllers.StageController.showError;

import constants.ErrorMsg;
import constants.TileType;
import constants.Title;
import constants.Window;
import core.Gameboard;
import core.Level;
import core.Save;
import holdables.Effect;
import holdables.Holdable;
import holdables.PlayerEffect;
import holdables.Tile;
import holdables.TileEffect;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import players.Player;
import players.PlayerMovement;
import styles.Style;

/**
 * TODO Test Drawing Draw Players test move dialog make apply effects work method to apply effect to
 * a player events for selected effect image, draw a border? maybe listview? logic for activate
 */
public class GameboardController
     implements InitialisableWithParameters, Initializable {

   public static final String FORMATTING_PLAYERS =
        "Formatting Players";
   public static final String PLAYER_FORMATTING_COMPLETE =
        "Player Formatting Complete!";
   public static final String SILK_BAG_DRAW =
        "Draw from silk bag";
   public static final String PLACE_TILE =
        "Place your tile";
   public static final int TILE_SIZE = 100;
   private static final String REFRESHING =
        "Refreshing Gameboard";
   private static final String REFRESH_COMPLETE =
        "Refreshing Gameboard";
   public BorderPane root;
   @FXML
   private GridPane grdBoard;
   @FXML
   private VBox vboxPlayers;
   @FXML
   private ListView<String> lstEffects;
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

   private int tempPlayerCounter;

   private Holdable newTileToPlace;
   private PlayerMovement playerMovement;
   private Player activePlayer;

   /**
    * Creates a Container that holds the player and their data
    *
    * @param player Player to create a Container for
    * @return Formatted Vbox
    */
   private static VBox createPlayerContainer(Player player) {
      ImageView image = new ImageView(player.getPlayerImage());
      Label name = new Label(player.getProfile().getName());
      VBox playerPicture = new VBox(image, name);
      VBox.setMargin(image, new Insets(4, 0, 4, 0));
      VBox.setMargin(name, new Insets(4, 4, 4, 4));
      return playerPicture;
   }

   public void cmdActionClick(MouseEvent mouseEvent) {

   }

   /**
    * Initialises the controller and it's FXML window
    *
    * @param location
    * @param resources
    */
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      formatPlayers();
   }

   /**
    * Formats each player and adds them to the left panel of the board window
    */
   private void formatPlayers() {
      setStatus(FORMATTING_PLAYERS);
      this.vboxPlayers.setAlignment(Pos.CENTER);
      ArrayList<Player> players = this.gameboard.getPlayers();
      players.forEach(player -> {
         VBox playerPicture = createPlayerContainer(player);
         this.vboxPlayers.getChildren().add(playerPicture);
      });
      setStatus(PLAYER_FORMATTING_COMPLETE);
   }

   /**
    * Sets the value of the status message
    *
    * @param message Message to be displayed
    */
   public void setStatus(String message) {
      this.lblStatus.setText(message);
   }

   /**
    * Gets the currently active Player
    *
    * @return Currently active player
    */
   public Player getActivePlayer() {
      return this.activePlayer;
   }

   /**
    * Sets the currently active player
    *
    * @param player Player to set as active
    */
   public void setActivePlayer(Player player) {
      this.activePlayer = player;
   }

   public void initializeWithArgs(Object[] args) {
      this.gameboard = (Gameboard) args[0];
      tempPlayerCounter = 1;
      cmdSilkBag.setDisable(true);
      cmdActivate.setDisable(true);
      refresh();
      playerTurn();

   }

   /**
    * playerturn order stuff
    */
   private void playerTurn() {
      // Draw from silk bag
      cmdSilkBag.setDisable(false);
      setStatus(SILK_BAG_DRAW);

      //If effect finish
      if (newTileToPlace.getClass() == PlayerEffect.class ||
           newTileToPlace.getClass() == TileEffect.class) {
         players.get(tempPlayerCounter).addToHand((Effect) newTileToPlace);

         // If tile call methods to place it
      } else if (newTileToPlace.getClass() == Tile.class) {
         setStatus(PLACE_TILE);
         cmdSilkBag.setVisible(true);
         // tileMove(newTileToPlace, rowOrColumn, newTileToPlaceY); need a
         // way to get player click
         refresh();
      }

      //PlayerMovement(players(tempPlayerCounter)); Waiting for class to
      // finish
      if (winCheck()) {
         //somehow end the game
      } else {
         tempPlayerCounter += 1;
         refresh();
         playerTurn();
      }

   }

   /**
    * Adds each effect in the active player's hand to the listview of effects
    */
   public void displayPlayerHand() {
      ArrayList<String> hand = new ArrayList<>();
      activePlayer.getHand().forEach(item -> hand.add(item.toString()));
      lstEffects.getItems().setAll(hand);
   }

   /**
    * Called from the {@link StageController}, allows the passage of parameters between scenes
    *
    * @param parameters Parameters for this Controller
    */
   @Override
   public void initialiseWithParameters(Object[] parameters) {
      this.gameboard = (Gameboard) parameters[1];
      setGridSize(gameboard.getWidth(), gameboard.getHeight());
      playerMovement = new PlayerMovement(gameboard);
      refresh();
   }

   /**
    * Sets up the correct number of rows and columns according to parameters
    *
    * @param width  Width in Columns
    * @param height Height in Columns
    */
   private void setGridSize(int width, int height) {
      this.grdBoard.getColumnConstraints().clear();
      this.grdBoard.getRowConstraints().clear();
      for (int i = 0; i < width; i++) {
         ColumnConstraints columnConstraint = new ColumnConstraints();
         columnConstraint.setPercentWidth(100.0 / width);
         this.grdBoard.getColumnConstraints().add(columnConstraint);
      }
      for (int i = 0; i < height; i++) {
         RowConstraints rowConstraint = new RowConstraints();
         rowConstraint.setPercentHeight(100.0 / height);
         this.grdBoard.getRowConstraints().add(rowConstraint);
      }
      setWindowSize();
   }

   /**
    * Refreshes the GridPane, updating each tile from the {@link Gameboard}
    */
   private void refresh() {
      if (gameboard != null) {
         setStatus(REFRESHING);
         gameboard.getTiles().forEach(tile -> {
            System.out.println(tile.toString());
            ImageView image = new ImageView();
            image.setImage(tile.getImage());
            image.setRotate(tile.getAngle().get());
            image.setPreserveRatio(false);
            image.setFitHeight(TILE_SIZE);
            image.setFitWidth(TILE_SIZE);
            grdBoard.add(image, tile.getCoordinate().getX(),
                 tile.getCoordinate().getY());
         });
         setStatus(REFRESH_COMPLETE);
      } else {
         showError(ErrorMsg.BOARD_REFRESH_ERROR, Title.CRIT_ERROR, false);
         changeScene(Window.SETUP);
      }
   }

   /**
    * Sets the height of the window based on the size of the gameboard
    */
   private void setWindowSize() {
      double gridPaneWidth =
           TILE_SIZE * grdBoard.getColumnConstraints().size() * TILE_SIZE;
      double gridPaneHeight =
           TILE_SIZE * grdBoard.getRowConstraints().size() * TILE_SIZE;
      double prefWidth = gridPaneWidth + lstEffects.getMaxWidth() +
           vboxPlayers.getMaxWidth();
      double prefHeight = gridPaneHeight + lblStatus.getMaxHeight();
      this.root.setPrefSize(prefWidth, prefHeight);
   }

   /**
    * Handles the silk bag button click event
    *
    * @param mouseEvent
    */
   public void cmdSilkbagClick(MouseEvent mouseEvent) {
      cmdSilkBag.setVisible(false);
      drawTile();

   }

   public void drawTile() {
      setNewTileToPlace(gameboard.getSilkBag().getFirst());
   }

   public void setNewTileToPlace(Holdable newTileToPlace) {
      this.newTileToPlace = newTileToPlace;
   }

   /**
    * Handles the Activate button click event
    *
    * @param mouseEvent
    */
   public void cmdActivateClick(MouseEvent mouseEvent) {
   }


   /**
    * Will work when goalTile is variable in gameboard
    *
    * @return
    */
   public boolean winCheck() {
      return gameboard.getTiles().get(players.get(tempPlayerCounter).getCoordinate()).getType()
           .equals(
                TileType.GOAL);
   }

   public void keyPressed(KeyEvent keyEvent) {
      if (keyEvent.getCode().equals(KeyCode.ESCAPE)) {
         showExitDialog();
      } else if (keyEvent.getCode().equals(KeyCode.LEFT) || keyEvent.getCode().equals(KeyCode.RIGHT)
           || keyEvent.equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.DOWN)) {
         playerMovement.keyPressed(keyEvent.getCode());
      }
   }

   private void showExitDialog() {
      Scene scene = null;
      try {
         FXMLLoader loader = new FXMLLoader(StageController.class
              .getResource(
                   Window.EXIT
                        .getPath()));
         Parent root = loader.load();
         InitialisableWithParameters controller = loader.getController();
         controller.initialiseWithParameters(
              new Object[]{this});
         scene = new Scene(root);
         Stage stage = new Stage();
         stage.setTitle(Title.MAIN.name());
         stage.setScene(scene);
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.showAndWait();
      } catch (IOException e) {
         e.printStackTrace();
         showError(ErrorMsg.FXML_NOT_FOUND, Title.ERROR, false);
      } catch (IllegalStateException e) {
         e.printStackTrace();
      }
   }

   /**
    * Saves the game and exits the application
    */
   public void saveAndExit() {
      try {
         Save.saveGame(new Object[]{gameboard, this, playerMovement});
         System.exit(0);
      } catch (IOException e) {
         showError(ErrorMsg.SAVE_WRITE_ERROR, Title.ERROR, false);
         e.printStackTrace();
      }
   }

   /**
    * Exits the application without saving
    */
   public void exit() {
      System.exit(0);
   }

   /**
    * Shows the Place Tile dialog
    *
    * @param tile Tile to place onto the board
    */
   public void showTileShifts(Tile tile, Gameboard gameboard) {
      Scene scene = null;
      try {
         FXMLLoader loader = new FXMLLoader(StageController.class
              .getResource(
                   Window.TILE_SHIFT
                        .getPath()));
         Parent root = loader.load();
         InitialisableWithParameters controller = loader.getController();
         controller.initialiseWithParameters(
              new Object[]{tile, gameboard, this});
         scene = new Scene(root);
         Stage stage = new Stage();
         stage.setTitle(Title.PLACE_TILE.name());
         stage.setScene(scene);
         stage.initModality(Modality.APPLICATION_MODAL);
         stage.showAndWait();
      } catch (IOException e) {
         e.printStackTrace();
         showError(ErrorMsg.FXML_NOT_FOUND, Title.ERROR, false);
      } catch (IllegalStateException e) {
         e.printStackTrace();
      }
   }

   public void lstEffectClick(MouseEvent mouseEvent) {
      lstEffects.getSelectionModel().getSelectedItem();
   }
}


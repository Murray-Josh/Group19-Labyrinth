
package controllers;

import static controllers.StageController.changeScene;
import static controllers.StageController.showConfirmation;
import static controllers.StageController.showError;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;
import constants.ErrorMessage;
import constants.TileType;
import constants.Title;
import constants.Window;
import core.Coordinate;
import core.Gameboard;
import core.Level;
import core.Save;
import core.Save.Key;
import holdables.Effect;
import holdables.Holdable;
import holdables.PlayerEffect;
import holdables.Tile;
import holdables.TileEffect;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        implements InitialisableWithParameters, Serializable {

    private static final int TILE_SIZE = 80;
    private static final double WINDOW_HEIGHT = 34;
    private static final double WINDOW_WIDTH = 340;
    private static final int PLAYER_SIZE = 60;
    private final int MOVE_COUNT = 4;

    private static final String FORMATTING_PLAYERS =
            "Formatting Players";
    private static final String PLAYER_FORMATTING_COMPLETE =
            "Player Formatting Complete!";
    private static final String SILK_BAG_DRAW =
            "Draw from silk bag";
    private static final String PLACE_TILE =
            "Place your tile";

    private static final String REFRESHING =
            "Refreshing Gameboard";
    private static final String REFRESH_COMPLETE =
            "Refreshing Gameboard";

    @FXML
    private BorderPane root;
    @FXML
    private GridPane grdBoard;
    @FXML
    private VBox vboxPlayers;
    @FXML
    private ListView<Effect> lstEffects;
    @FXML
    private Button cmdActivate;
    @FXML
    private Label lblStatus;

    private ArrayList<Player> players;
    private Gameboard gameboard;
    private int tempPlayerCounter = 0;
    private Holdable newTileToPlace;
    private PlayerMovement playerMovement;
    private Player activePlayer;
    private boolean playerMoving;
    private int activePlayerMovementLeft = MOVE_COUNT;
    private Coordinate temp = null;
    private PlayerMovement pMove;
    private boolean skip = false;


    /**
     * Called from the {@link StageController}, allows the passage of parameters between scenes
     *
     * @param parameters Parameters for this Controller
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialiseWithParameters(Object[] parameters, Scene scene, Stage stage) {
        boolean loadingFromSave = (boolean) parameters[0];
        if (loadingFromSave) {
            initialiseFromSave((HashMap<Key, Object>) parameters[1], scene);
        } else {
            initialiseFromSetup((Gameboard) parameters[1], scene);
        }
        playerTurn();
    }

    private void initialiseFromSave(HashMap<Key, Object> data, Scene scene) {
        this.skip = (boolean) data.get(Key.SKIP);
        this.playerMovement = (PlayerMovement) data.get(Key.MOVEMENT);
      this.activePlayerMovementLeft = (int)  data.get(Key.MOVEMENTS_LEFT);
     this.activePlayer = (Player)   data.get(Key.ACTIVE_PLAYER);
this.temp = (Coordinate) data.get(Key.TEMPORARY_COORDINATE);
this.tempPlayerCounter = (int) data.get(Key.PLAYER_COUNTER);

this.gameboard = playerMovement.getGameboard();
this.players=gameboard.getPlayers();

setGridSize(gameboard.getWidth(), gameboard.getHeight());
refresh();
formatPlayers();
startKeyListener(scene);


    }

    private void initialiseFromSetup(Gameboard gameboard, Scene scene) {
        this.gameboard = gameboard;
        setGridSize(gameboard.getWidth(), gameboard.getHeight());
        playerMovement = new PlayerMovement(gameboard);
        pMove = new PlayerMovement(gameboard);
        refresh();
        formatPlayers();
        startKeyListener(scene);
        activePlayer = players.get(tempPlayerCounter);
        playerTurn();
    }

    /**
     * Creates a Container that holds the player and their data
     *
     * @param player Player to create a Container for
     * @return Formatted Vbox
     */
    private static VBox createPlayerContainer(Player player, Style style) {
        ImageView image = new ImageView(Style.getPlayerImage(player.getPlayerNum()));
        image.setPreserveRatio(false);
        image.setFitHeight(TILE_SIZE);
        image.setFitWidth(TILE_SIZE);
        Label name = new Label(player.getProfile().getName());
        VBox playerPicture = new VBox(image, name);
        playerPicture.setMaxWidth(TILE_SIZE);
        VBox.setMargin(image, new Insets(4, 0, 4, 0));
        VBox.setMargin(name, new Insets(4, 4, 4, 4));
        return playerPicture;
    }

    public void cmdActionClick(MouseEvent mouseEvent) {
    }

    /**
     * Places players at new coordinates
     *
     * @param pList List of players to place
     */
    private void placePlayer(ArrayList<Player> pList) {
        for (Player player : pList) {
            placePlayer(player);
        }
    }

    /**
     * Places single player at new coordinate
     *
     * @param p Player to place
     */
    private void placePlayer(Player p) {
        ImageView image = new ImageView(Style.getPlayerImage(p.getPlayerNum()));
        image.setPreserveRatio(false);
        image.setRotate(p.getCurrentDirection().get());
        image.setFitHeight(PLAYER_SIZE);
        image.setFitWidth(PLAYER_SIZE);
        grdBoard.add(image, p.getCoordinate().getX(), p.getCoordinate().getY());
    }

    /**
     * Formats each player and adds them to the left panel of the board window
     */
    private void formatPlayers() {
        setStatus(FORMATTING_PLAYERS);
        this.vboxPlayers.setAlignment(Pos.CENTER);
        players = new ArrayList<>(this.gameboard.getPlayers());
        players.forEach(player -> {
            VBox playerPicture = createPlayerContainer(player, this.gameboard.getStyle());
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

    /**
     * playerturn order stuff
     */
    private void playerTurn() {
        // Is silk bag working?
        if (activePlayer != null) {
            cmdActivate.setDisable(false);
            setStatus(SILK_BAG_DRAW + " player " + activePlayer.getPlayerNum());
            System.out.println("player num" + activePlayer.getPlayerNum());
            drawTile();
            if (newTileToPlace instanceof PlayerEffect || newTileToPlace instanceof TileEffect) {
                activePlayer.addToHand((Effect) newTileToPlace);
                displayPlayerHand();
            } else if (newTileToPlace instanceof Tile) {
                System.out.println("CLASS TILES");
                displayTileMovementDialog((Tile) newTileToPlace, gameboard);
            }

            String playerMoveText = "";
            if (skip && tempPlayerCounter != 0) {
                playerMoveText = "Player " + (tempPlayerCounter) + " could not move! ";
            } else if (skip && tempPlayerCounter == 0) {
                playerMoveText = "Player " + (tempPlayerCounter + 1) + " could not move! ";
            }
            playerMoveText += "Player " + (tempPlayerCounter + 1) + "'s move";
            setStatus(playerMoveText);
            activePlayer = players.get(tempPlayerCounter);
            activePlayerMovementLeft = MOVE_COUNT;
            skip = false;
            temp = null;

            refresh();
        }
    }

    /**
     * Adds each effect in the active player's hand to the listview of effects
     */
    public void displayPlayerHand() {
        lstEffects.getItems().clear();
        lstEffects.getItems().setAll(activePlayer.getHand());
    }

    /**
     * limits each player to being able to be the target of backtrack only once per game
     * @param targetPlayer
     * @param player
     */
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
    public void refresh() {
        if (gameboard != null) {
            InnerShadow innerShadow = new InnerShadow(5, Color.BLACK);
            gameboard.getTiles().forEach(tile -> {
                ImageView image = new ImageView();
                image.setImage(tile.getImage());
                if (tile.isFixed()) {
                    image.setEffect(innerShadow);
                }
                image.setRotate(tile.getAngle().get());
                image.setPreserveRatio(false);
                image.setFitHeight(TILE_SIZE);
                image.setFitWidth(TILE_SIZE);
                grdBoard.add(image, tile.getCoordinate().getX(), tile.getCoordinate().getY());
                placePlayer(gameboard.getPlayers());
            });
            /*
            if(activePlayerMovementLeft == 0) {
                activePlayer.setMoves(MOVE_COUNT - activePlayerMovementLeft);
                iterateTempPlayerCounter();
                playerTurn();
            }

             */

        } else {
            showError(ErrorMessage.BOARD_REFRESH_ERROR, Title.CRITICAL_ERROR, false);
            changeScene(Window.SETUP);
        }
    }

    /**
     * Sets the height of the window based on the size of the gameboard
     */
    private void setWindowSize() {
        double gridPaneWidth =
                TILE_SIZE * grdBoard.getColumnConstraints().size();
        double gridPaneHeight =
                TILE_SIZE * grdBoard.getRowConstraints().size();
        grdBoard.setMaxSize(gridPaneWidth, gridPaneHeight);
        grdBoard.setMinSize(gridPaneWidth, gridPaneHeight);
        grdBoard.setPrefSize(gridPaneWidth, gridPaneHeight);
        root.setMaxSize(gridPaneWidth + WINDOW_WIDTH, gridPaneHeight + WINDOW_HEIGHT);
        root.setMinSize(gridPaneWidth + WINDOW_WIDTH, gridPaneHeight + WINDOW_HEIGHT);
        root.setPrefSize(gridPaneWidth + WINDOW_WIDTH, gridPaneHeight + WINDOW_HEIGHT);
    }

    public void drawTile() {
        if (activePlayer.getHand() == null) {
            activePlayer.setHand(new ArrayList<>());
        }
        setNewTileToPlace(gameboard.getSilkBag().poll());
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
        Effect chosenEffect = lstEffects.getSelectionModel().getSelectedItem();
        if (chosenEffect instanceof TileEffect) {
            displayTileSelectionDialog((TileEffect) chosenEffect);
        } else if (chosenEffect instanceof PlayerEffect) {
            if (chosenEffect.equals(PlayerEffect.DOUBLE_MOVE)) {
                activePlayerMovementLeft =
                        (2 * MOVE_COUNT) - (MOVE_COUNT - activePlayerMovementLeft);
            } else if (chosenEffect.equals(PlayerEffect.BACKTRACK)) {
                Player chosenPlayer = displayPlayerSelectionDialog();
                applyBackTrack(chosenPlayer, activePlayer);
                refresh();
            }
        }
        lstEffects.getItems().remove(chosenEffect);
        activePlayer.getHand().remove(chosenEffect);
        cmdActivate.setDisable(true);
        /*TODO: Stop the player from moving! */
    }

    public Player displayPlayerSelectionDialog() {
        ChoiceDialog dialog = new ChoiceDialog();
        dialog.setGraphic(null);
        dialog.setHeaderText("Select a Player to Backtrack");
        dialog.setContentText(null);
        dialog.setTitle("Backtrack Player Selection");
        dialog.getDialogPane().getStylesheets().add("./resources/css/dialog.css");
        dialog.getItems().addAll(gameboard.getPlayers());
        dialog.getItems().remove(activePlayer);
        Optional<Player> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        } else {
            dialog.close();
            return null;
        }
    }

    private void displayTileSelectionDialog(TileEffect chosenEffect) {
        Scene scene = null;
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(
                    StageController.class.getResource(Window.TILE_EFFECT.getPath()));
            Parent root = loader.load();
            InitialisableWithParameters controller = loader.getController();
            scene = new Scene(root);
            controller.initialiseWithParameters(
                    new Object[]{gameboard, chosenEffect, this}, scene, stage);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            showError(ErrorMessage.FXML_NOT_FOUND, Title.ERROR, false);
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    /**
     * Will work when goalTile is variable in gameboard
     *
     * @return
     */
    public boolean winCheck() {
        return gameboard.getTiles().get(activePlayer.getCoordinate()).getType()
                .equals(TileType.GOAL);
    }

    private void startKeyListener(Scene scene) {
        scene.setOnKeyReleased(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent event) {
        Player p = getActivePlayer();
        playerMoving = true;
        if (playerMoving && activePlayerMovementLeft > 0 && checkTilesAligned()) {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                displayExitDialog();
            } else if (event.getCode().equals(KeyCode.SPACE) && temp != null) {
                activePlayer.setMoves(MOVE_COUNT - activePlayerMovementLeft);
                iterateTempPlayerCounter();
                System.out.println("NEXT TURN");
                playerTurn();
            } else if (event.getCode().isArrowKey()) {
                temp = activePlayer.getCoordinate();
                playerMovement.keyPressed(event.getCode(), p);
                if (winCheck()) {
                    winGame();
                }
                if (temp != activePlayer.getCoordinate()) {
                    activePlayerMovementLeft -= 1;
                }
                refresh();
            }
        } else {
            activePlayer.setMoves(MOVE_COUNT - activePlayerMovementLeft);
            iterateTempPlayerCounter();
            skip = true;
            refresh();
            playerTurn();
        }
    }

    private void winGame() {
        showConfirmation("Congratulations " + activePlayer.getProfile().getName() + "!",
                "Player " + activePlayer.getPlayerNum() + " Has Won!", Title.MAIN.toString());
        for (Player ps : players) {
            ps.getProfile().setNumOfGames(ps.getProfile().getNumOfGames() + 1);
            ps.getProfile().setNumOfLosses(ps.getProfile().getNumOfLosses() + 1);
        }

        activePlayer.getProfile()
                .setNumOfLosses(activePlayer.getProfile().getNumOfLosses() - 1);
        activePlayer.getProfile()
                .setNumOfWins(activePlayer.getProfile().getNumOfWins() + 1);
        StageController.home();
    }

    private boolean checkTilesAligned() {
        Boolean[] t = pMove.tilesAligned(activePlayer, this.gameboard);
        for (int i = 0; i < MOVE_COUNT; i++) {
            if (t[i]) {
                return true;
            }
        }
        return false;
    }

    private void displayExitDialog() {
        Scene scene = null;
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(
                    StageController.class.getResource(Window.EXIT.getPath()));
            Parent root = loader.load();
            InitialisableWithParameters controller = loader.getController();
            scene = new Scene(root);
            controller.initialiseWithParameters(
                    new Object[]{this}, scene, stage);
            stage.setTitle(Title.MAIN.toString());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showError(ErrorMessage.FXML_NOT_FOUND, Title.ERROR, false);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the game and exits the application
     */
    public void saveAndExit() {
        try {
            Save.save(skip, activePlayer, tempPlayerCounter, activePlayerMovementLeft, temp,
                 playerMovement);
            System.exit(0);
        } catch (IOException e) {
            showError(ErrorMessage.SAVE_WRITE_ERROR, Title.SAVE, false);
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
    public void displayTileMovementDialog(Tile tile, Gameboard gameboard) {
        Scene scene = null;
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(StageController.class
                    .getResource(
                            Window.TILE_SHIFT
                                    .getPath()));
            Parent root = loader.load();
            InitialisableWithParameters controller = loader.getController();
            scene = new Scene(root);
            controller.initialiseWithParameters(new Object[]{tile, gameboard, this}, scene, stage);
            stage.setTitle(Title.MAIN.toString());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showError(ErrorMessage.FXML_NOT_FOUND, Title.ERROR, false);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enable the activate button when an item has been clicked
     *
     * @param mouseEvent Event
     */
    public void lstEffectClick(MouseEvent mouseEvent) {
        lstEffects.getSelectionModel().getSelectedItem();
        if (lstEffects.getSelectionModel().getSelectedItem() != null) {
            cmdActivate.setDisable(false);
        } else {
            cmdActivate.setDisable(true);
        }
    }


    private void applyBackTrack(Player targetPlayer, Player player) {
        if (!targetPlayer.hasBeenBackTracked()) {
            playerMovement.backMovement(targetPlayer);
            targetPlayer.setBeenBackTracked(true);
        }
        boolean everyoneBackTracked = true;
        for (Player gamePlayer : players) {
            if (!gamePlayer.hasBeenBackTracked()) {
                everyoneBackTracked = false;
            }
        }
        if (everyoneBackTracked) {
            player.getHand().removeAll(Collections.singleton(PlayerEffect.BACKTRACK));
            gameboard.getSilkBag().removeAll(Collections.singleton(PlayerEffect.BACKTRACK));
        }
    }


    public void iterateTempPlayerCounter() {
        if (tempPlayerCounter < this.gameboard.getPlayersCount() - 1) {
            tempPlayerCounter += 1;
        } else if (tempPlayerCounter == this.gameboard.getPlayersCount()) {
            tempPlayerCounter = 0;
        } else {
            tempPlayerCounter = 0;
        }
    }

    /*
    TODO
    Make this work pretty please!!! <3
     */
    public void applyEffect(TileEffect effect, ArrayList<Tile> tiles) {

    }

    public void isNextTurn() {
        if (activePlayerMovementLeft == 0) {
            activePlayer.setMoves(MOVE_COUNT - activePlayerMovementLeft);
            iterateTempPlayerCounter();
            System.out.println("NEXT TURN REFRESH");
            playerTurn();
        }
    }


}

package controllers;

import static controllers.StageController.changeScene;
import static controllers.StageController.showConfirmation;
import static controllers.StageController.showError;
import static styles.Style.getCornerFire;
import static styles.Style.getCornerIce;
import static styles.Style.getJunctionFire;
import static styles.Style.getJunctionIce;
import static styles.Style.getStraightFire;
import static styles.Style.getStraightIce;

import constants.ErrorMessage;
import constants.LevelType;
import constants.TileType;
import constants.Title;
import constants.Window;
import core.Coordinate;
import core.Gameboard;
import core.Save;
import core.Save.Key;
import holdables.Effect;
import holdables.Holdable;
import holdables.PlayerEffect;
import holdables.Tile;
import holdables.TileEffect;
import java.io.IOException;
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
import javafx.scene.image.Image;
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
import players.PlayerProfile;
import styles.Style;

/**
 * Controls the Gameboard, the game's progression and its associated GUI
 *
 * @author Joshua Murray
 * @author Jordy Martinson
 * @author Martin Samm
 * @author Fungwah Westley
 * @author Issi Ludwig
 * @author Joseph Omar
 * @author Aaron Davies
 * @version 1.3
 */
public class GameboardController
        implements InitialisableWithParameters, Serializable {

    private static final int TILE_SIZE = 80;
    private static final double WINDOW_HEIGHT = 34;
    private static final double WINDOW_WIDTH = 340;
    private static final int PLAYER_SIZE = 60;
    private static final String FORMATTING_PLAYERS =
            "Formatting Players";
    private static final String PLAYER_FORMATTING_COMPLETE =
            "Player Formatting Complete!";
    private static final String SILK_BAG_DRAW =
            "Draw from silk bag";
    private final int MOVE_COUNT = 4;
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
    private int playerCounter = 0;
    private Holdable newTileToPlace;
    private PlayerMovement playerMovement;
    private Player activePlayer;
    private int activePlayerMovementLeft = MOVE_COUNT;
    private Coordinate temp = null;
    private boolean skip = false;

    /**
     * Creates a Container that holds the player, their name and their sprite.
     *
     * @param player Player to create a Container for
     * @return Formatted Player in a VBox
     */
    private static VBox createPlayerContainer(Player player) {
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

    /**
     * Called from the {@link StageController}, allows the passage of parameters between the
     * SetUpGameController and the GameboardController
     *
     * @param parameters Parameters needed for this controller
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
        refresh();
    }

    /**
     * Initialises the GameboardController and its attributes from a loaded save file
     *
     * @param data  HashMap containing the data from the saved game
     * @param scene This window's Scene
     */
    private void initialiseFromSave(HashMap<Key, Object> data, Scene scene) {
        this.skip = (boolean) data.get(Key.SKIP);
        this.playerMovement = (PlayerMovement) data.get(Key.MOVEMENT);
        this.activePlayerMovementLeft = (int) data.get(Key.MOVEMENTS_LEFT);
        this.activePlayer = (Player) data.get(Key.ACTIVE_PLAYER);
        this.temp = (Coordinate) data.get(Key.TEMPORARY_COORDINATE);
        this.playerCounter = (int) data.get(Key.PLAYER_COUNTER);

        this.gameboard = playerMovement.getGameboard();
        this.players = gameboard.getPlayers();

        setGridSize(gameboard.getWidth(), gameboard.getHeight());
        refresh();
        formatPlayers();
        startKeyListener(scene);


    }

    /**
     * Initialises the gameboard from new game setup
     *
     * @param gameboard The gameboard to be played on
     * @param scene     This window's scene
     */
    private void initialiseFromSetup(Gameboard gameboard, Scene scene) {
        this.gameboard = gameboard;
        setGridSize(gameboard.getWidth(), gameboard.getHeight());
        playerMovement = new PlayerMovement(gameboard);
        refresh();
        formatPlayers();
        startKeyListener(scene);
        activePlayer = players.get(playerCounter);
        playerTurn();
    }

    /**
     * Places multiple players at their coordinates
     *
     * @param players List of players to place
     */
    private void placePlayer(ArrayList<Player> players) {
        for (Player player : players) {
            placePlayer(player);
        }
    }

    /**
     * Places single player at new coordinate
     *
     * @param player Player to place
     */
    private void placePlayer(Player player) {
        ImageView image = new ImageView(Style.getPlayerImage(player.getPlayerNum()));
        image.setPreserveRatio(false);
        image.setRotate(player.getCurrentDirection().get());
        image.setFitHeight(PLAYER_SIZE);
        image.setFitWidth(PLAYER_SIZE);
        grdBoard.add(image, player.getCoordinate().getX(), player.getCoordinate().getY());
    }

    /**
     * Formats each player and adds them to the left panel of the board window
     */
    private void formatPlayers() {
        setStatus(FORMATTING_PLAYERS);
        this.vboxPlayers.setAlignment(Pos.CENTER);
        players = new ArrayList<>(this.gameboard.getPlayers());
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
     * Systematically goes through the events that occur during a player's turn.
     */
    private void playerTurn() {
        if (activePlayer != null) {
            cmdActivate.setDisable(false);
            activePlayer = players.get(playerCounter);
            setStatus(SILK_BAG_DRAW + " player " + activePlayer.getPlayerNum());
            System.out.println("player num" + activePlayer.getPlayerNum());
            drawTileFromSilkBag();

            /* If the Tile is an Effect Tile */
            if (newTileToPlace instanceof PlayerEffect || newTileToPlace instanceof TileEffect) {
                activePlayer.addToHand((Effect) newTileToPlace);
                lstEffects.getItems().clear();
                displayPlayerHand();
                /* If the tile is a gameboard tile*/
            } else if (newTileToPlace instanceof Tile) {
                System.out.println("CLASS TILES");
                displayTileMovementDialog((Tile) newTileToPlace, gameboard);
            }

            String playerMoveText = "";
            if (skip && playerCounter != 0) {
                playerMoveText = "Player " + (playerCounter) + " could not move! ";
            } else if (skip && playerCounter == 0) {
                playerMoveText = "Player " + (playerCounter + players.size()) + " could not move! ";
            }
            playerMoveText += "Player " + (playerCounter + 1) + "'s move";
            setStatus(playerMoveText);
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
            /* For Each Tile in the Gameboard*/
            gameboard.getTiles().forEach(tile -> {
                ImageView image = new ImageView();
                image.setImage(tile.getImage());
                if (tile.getEffect() != TileEffect.NONE) {
                    image.setImage(applyTileEffect(tile, image));
                }
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
        } else {
            showError(ErrorMessage.BOARD_REFRESH_ERROR, Title.CRITICAL_ERROR, false);
            changeScene(Window.SETUP);
        }
    }

    /**
     * Applies a tile effect and style to a given tile
     *
     * @param tile  Tile to affect
     * @param image Image of tile
     * @return Tile image with effect added
     */
    private Image applyTileEffect(Tile tile, ImageView image) {
        TileType[] types = new TileType[]{TileType.CORNER, TileType.STRAIGHT, TileType.JUNCTION};
        Image[] iceTiles = new Image[]{getCornerIce(), getStraightIce(), getJunctionIce()};
        Image[] fireTiles = new Image[]{getCornerFire(), getStraightFire(), getJunctionFire()};

        if (tile.getEffect() == TileEffect.ICE) {
            for (int i = 0; i < types.length; i++) {
                if (tile.getType() == types[i]) {
                    image.setImage(iceTiles[i]);
                    tile.setFixed();
                }
            }
        } else if (tile.getEffect() == TileEffect.FIRE) {
            for (int i = 0; i < types.length; i++) {
                if (tile.getType() == types[i]) {
                    image.setImage(fireTiles[i]);
                }
            }
        }
        return image.getImage();
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

    /**
     * Draws a tile from the Gameboard's Silk Bag
     */
    public void drawTileFromSilkBag() {
        if (activePlayer.getHand() == null) {
            activePlayer.setHand(new ArrayList<>());
        }
        this.newTileToPlace = gameboard.getSilkBag().poll();
    }

    /**
     * Handles the Activate button click event
     *
     * @param mouseEvent Event
     */
    @SuppressWarnings("unused")
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
    }

    /**
     * Displays a Dialog box asking the player to choose another player to apply the backtrack
     * effect to
     *
     * @return Player to apply the effect to
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
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

    /**
     * Shows the Dialog allowing the player to select a tile to apply their tile effect to
     *
     * @param chosenEffect Tile effect to apply to a range of tiles
     */
    private void displayTileSelectionDialog(TileEffect chosenEffect) {
        Scene scene;
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
     * Checks if the active player is on a goal tile
     *
     * @return Is the active player on the goal tile
     */
    public boolean winCheck() {
        return gameboard.getTiles().get(activePlayer.getCoordinate()).getType()
                .equals(TileType.GOAL);
    }

    /**
     * Creates an event listener on key press
     *
     * @param scene This window's scene
     */
    private void startKeyListener(Scene scene) {
        scene.setOnKeyReleased(this::handleKeyPress);
    }

    /**
     * Handles a KeyPress event based on the game's state
     *
     * @param event The KeyPress event
     */
    private void handleKeyPress(KeyEvent event) {
        Player player = getActivePlayer();

        /* If the player can move */
        if (activePlayerMovementLeft > 0 && checkTilesAligned()) {
            /*If the key pressed is escape */
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                displayExitDialog();

                /*Ends the active player's turn */
            } else if (event.getCode().equals(KeyCode.SPACE) && temp != null) {
                activePlayer.setMoves(MOVE_COUNT - activePlayerMovementLeft);
                iteratePlayerCounter();
                System.out.println("NEXT TURN");
                playerTurn();

                /*If the key is an arrow key */
            } else if (event.getCode().isArrowKey()) {
                temp = activePlayer.getCoordinate();
                playerMovement.keyPressed(event.getCode(), player);
                /* Check if the tile the player has moved to is the goal tile */
                if (winCheck()) {
                    winGame();
                }
                /* Reduce the number of moves they have left on their turn by one */
                if (temp != activePlayer.getCoordinate()) {
                    activePlayerMovementLeft -= 1;
                }
                refresh();
            }
        } else {
            activePlayer.setMoves(MOVE_COUNT - activePlayerMovementLeft);
            iteratePlayerCounter();
            skip = true;
            refresh();
            playerTurn();
        }
    }

    /**
     * Changes the player's statistics and returns to the main menu
     */
    private void winGame() {
        showConfirmation("Congratulations " + activePlayer.getProfile().getName() + "!",
                "Player " + activePlayer.getPlayerNum() + " Has Won!", Title.MAIN.toString());
        for (Player player : players) {
            PlayerProfile profile = player.getProfile();
            profile.editLosses(gameboard.getLevelType(),
                    profile.getLosses(gameboard.getLevelType()) + 1);
            profile.editGames(gameboard.getLevelType(),
                    profile.getGames(gameboard.getLevelType()) + 1);
        }
        LevelType levelType = gameboard.getLevelType();
        PlayerProfile profile = activePlayer.getProfile();
        profile.editWins(levelType, profile.getWins(levelType) + 1);
        profile.editLosses(levelType, profile.getLosses(levelType) - 1);
        StageController.home();
    }

    /**
     * Checks if the player can move to the next tile
     *
     * @return If the player can move to the next tile
     */
    private boolean checkTilesAligned() {
        Boolean[] tilesAligned = playerMovement.tilesAligned(activePlayer, this.gameboard);
        for (int i = 0; i < MOVE_COUNT; i++) {
            if (tilesAligned[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Shows the Exit Game Dialog
     */
    private void displayExitDialog() {
        Scene scene;
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
            Save.save(skip, activePlayer, playerCounter, activePlayerMovementLeft, temp,
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
        Scene scene;
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
    @SuppressWarnings("unused")
    public void lstEffectClick(MouseEvent mouseEvent) {
        lstEffects.getSelectionModel().getSelectedItem();
        cmdActivate.setDisable(lstEffects.getSelectionModel().getSelectedItem() == null);
    }


    /**
     * Applies backtrack to the the target player
     *
     * @param targetPlayer The Target Player
     * @param player       The current Player
     */
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

    /**
     * Increases the player counter or loops back to 0 if the counter is at its maximum value
     */
    public void iteratePlayerCounter() {
        if (playerCounter < this.gameboard.getPlayersCount() - 1) {
            playerCounter += 1;
        } else if (playerCounter == this.gameboard.getPlayersCount() - 1) {
            playerCounter = 0;
            effectCountdown();
        } else {
            playerCounter = 0;
        }
    }

    /**
     * Iterates the countdown for effect timers
     */
    private void effectCountdown() {
        for (Tile tile : gameboard.getTiles()) {
            if (tile.getEffect() != TileEffect.NONE) {
                tile.decrementTimer();
                if (tile.getEffectTimer() == 0) {
                    tile.setEffect(TileEffect.NONE);
                }
            }
        }
    }

    /**
     * Applies the given effect to the given list of tiles
     *
     * @param effect Effect to apply
     * @param tiles  Tiles to apply to
     */
    public void applyEffect(TileEffect effect, ArrayList<Tile> tiles) {
        for (Tile t : tiles) {
            t.setEffect(effect);
            if (t.getEffect().equals(TileEffect.ICE)) {
                t.setEffectTimer(1);
            } else {
                t.setEffectTimer(2);
            }
        }
        refresh();
    }


}

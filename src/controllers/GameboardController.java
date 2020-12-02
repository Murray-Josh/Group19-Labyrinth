package controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import constants.ErrorMsg;
import constants.Title;
import constants.Window;
import core.Coordinate;
import core.Gameboard;
import core.Level;
import holdables.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import players.Player;
//import players.PlayerMovement;
import styles.Style;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.StageController.*;


public class GameboardController implements InitialisableWithParameters, Initializable {
    private static final String REFRESHING = "Refreshing Gameboard";
    private static final String REFRESH_COMPLETE = "Refreshing Gameboard";
    public static final String FORMATTING_PLAYERS = "Formatting Players";
    public static final String PLAYER_FORMATTING_COMPLETE = "Player Formatting Complete!";
    public static final String SILK_BAG_DRAW = "Draw from silk bag";
    public static final String PLACE_TILE = "Place your tile";

    public static final int TILE_SIZE = 50;
    public BorderPane root;
    public ButtonBar butttonBar;
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

    private ArrayList<Player> players;

    private Style style;

    private Level level;
    private Gameboard gameboard;

    private int tempPlayerCounter;

    private Holdable newTileToPlace;
    private int newTileToPlaceX;
    private int newTileToPlaceY;


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


    public void initializeWithArgs(Object[] args) {
        this.gameboard = (Gameboard) args[1];
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
        if (newTileToPlace.getClass() == PlayerEffect.class || newTileToPlace.getClass() == TileEffect.class) {
            players.get(tempPlayerCounter).addToHand((Effect) newTileToPlace);

        // If tile call methods to place it
        } else if (newTileToPlace.getClass() == Tile.class) {
            setStatus(PLACE_TILE);
            cmdSilkBag.setVisible(true);
            // tileMove(newTileToPlace, rowOrColumn, newTileToPlaceY); need a way to get player click
            refresh();
        }

        //PlayerMovement(players(tempPlayerCounter)); Waiting for class to finish
        if(winCheck()){
            //somehow end the game
        }
        else {
            tempPlayerCounter += 1;
            refresh();
            playerTurn();
        }

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
                grdBoard.add(image, tile.getCoordinate().getX(), tile.getCoordinate().getY());
            });
            setStatus(REFRESH_COMPLETE);
        } else {
            showError(ErrorMsg.BOARD_REFRESH_ERROR, Title.CRIT_ERROR, false);
            changeScene(Window.SETUP);
        }
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

    /**
     * Sets the value of the status message
     *
     * @param message Message to be displayed
     */
    public void setStatus(String message) {
        this.lblStatus.setText(message);
    }

    /**
     * formats and displays a players hand in the right hand  {@link javafx.scene.control.ScrollPane}
     *
     * @param player Current {@link Player}
     */
    public void displayPlayerHand(Player player) {
        player.getHand().forEach(item -> this.vboxEffects.getChildren().add(formatActionTile(item)));
    }

    /**
     * Formats the Action tiles in a players hand into an icon and a name
     *
     * @param item
     * @return
     */
    private VBox formatActionTile(Effect item) {
        ImageView image = new ImageView(item.getImage(this.style));
        Label name = new Label(item.toString());
        VBox actionTile = new VBox(image, name);
        VBox.setMargin(image, new Insets(4, 0, 4, 0));
        VBox.setMargin(name, new Insets(4, 4, 4, 4));
        return actionTile;
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
     * Sets the height of the window based on the size of the gameboard
     */
    private void setWindowSize() {
        double gridPaneWidth = TILE_SIZE * grdBoard.getColumnConstraints().size() * TILE_SIZE;
        double gridPaneHeight = TILE_SIZE * grdBoard.getRowConstraints().size() * TILE_SIZE;
        double prefWidth = gridPaneWidth + vboxEffects.getMaxWidth() + vboxPlayers.getMaxWidth();
        double prefHeight = gridPaneHeight + lblStatus.getMaxHeight() + butttonBar.getMaxHeight();
        this.root.setPrefSize(prefWidth, prefHeight);
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
        refresh();
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

    public void drawTile(){
        setNewTileToPlace(gameboard.getSilkBag().getFirst());
    }

    /**
     * Handles the Activate button click event
     *
     * @param mouseEvent
     */
    public void cmdActivateClick(MouseEvent mouseEvent) {
    }

    public void setNewTileToPlace(Holdable newTileToPlace) {
        this.newTileToPlace = newTileToPlace;
    }

    public void tileMove(Holdable tile, String rowOrColumn, int num) {
        Tile newTile = (Tile) tile;
        if (rowOrColumn.equals("Column")) {
            for (int y = 0; y < gameboard.getHeight(); y++) {
                Tile tempTile = gameboard.getTiles().get(num, y);
                newTile.setCoordinate(new Coordinate(num, y));
                gameboard.setGameboardTile(newTile.getCoordinate(), newTile);
                newTile = tempTile;
            }

        } else if (rowOrColumn.equals("Row")) {
            for (int x = 0; x < gameboard.getWidth(); x++) {
                Tile tempTile = gameboard.getTiles().get(x, num);
                newTile.setCoordinate(new Coordinate(x, num));
                gameboard.setGameboardTile(newTile.getCoordinate(), newTile);
                newTile = tempTile;
            }
        }
    }

    /**
     * Will work when goalTile is variable in gameboard
     * @return
     */
    public boolean winCheck(){
        if(players.get(tempPlayerCounter).getCoordinate()==gameboard.getGoal()){
            return true;
        }
        else {
            return false;
        }
    }

    public void keyPressed(KeyEvent keyEvent) {

        //PlayerMovement.keyPressed(keyEvent);

    }
}


package controllers;

import constants.ErrorMsg;
import constants.Title;
import constants.Window;
import core.Gameboard;
import core.Level;
import holdables.Holdable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import players.Player;
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

    /**
     * Handles the Activate button click
     * @param mouseEvent
     */
    public void cmdActionClick(MouseEvent mouseEvent) {

    }

    /**
     * Initialises the controller and it's FXML window
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatPlayers();
    }

    /**
     * Called from the {@link StageController}, allows the passage of parameters between scenes
     * @param parameters Parameters for this Controller
     */
    @Override
    public void initialiseWithParameters(Object[] parameters) {
        this.gameboard = (Gameboard) parameters[1];
        refresh();
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
     * @param message Message to be displayed
     */
    public void setStatus(String message) {
        this.lblStatus.setText(message);
    }

    public void displayPlayerHand(Player player) {
        player.getHand().forEach(item -> formatTile(item));
    }

    private void formatTile(Holdable item) {
        VBox
    }
}


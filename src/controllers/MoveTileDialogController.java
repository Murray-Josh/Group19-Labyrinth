package controllers;

import constants.Angle;
import core.Gameboard;
import holdables.Tile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import static controllers.MoveTileDialogController.Axis.COLUMN;
import static controllers.MoveTileDialogController.Axis.ROW;

/**
 * Controls the MoveTileDialog, which allows the player to add a tile onto the
 * board.
 *
 * @author Joseph Omar
 * @version 1.4
 */
public class MoveTileDialogController implements InitialisableWithParameters {
    public static final  int    PREVIEW_WIDTH       = 128;
    public static final  int    WINDOW_MIN_WIDTH    = 766;
    private static final double TILE_SIZE           = 100;
    private static final String ARROW_PATH_ROLLOVER =
            "../resources/menu/arrow_rollover.png";
    private static final double WINDOW_HEIGHT       = TILE_SIZE + 219;
    private static final String ARROW_PATH          =
            "../resources/menu/arrow.png";

    private static final String             RIGHT  = "Right";
    private static final String             LEFT   = "Left";
    private static final String             TOP    = "Top";
    private static final String             BOTTOM = "Bottom";
    private              ImageView          addLeft;
    private              ImageView          addRight;
    @FXML
    private              Button             cmdConfirm;
    @FXML
    private              ChoiceBox<Axis>    comDirection;
    @FXML
    private              ChoiceBox<Integer> comNumber;
    @FXML
    private              Label              lblLeft;
    @FXML
    private              Label              lblRight;
    @FXML
    private              GridPane           grdTiles;
    @FXML
    private              AnchorPane         rootPane;

    private Tile             tile;
    private Gameboard        gameboard;
    private ArrayDeque<Tile> selectedTiles;

    /**
     * Initialises the MoveTile {@link constants.Window}
     *
     * @param parameters Objects the method needs to initialise the window
     */
    @Override
    public void initialiseWithParameters(Object[] parameters) {
        this.tile = (Tile) parameters[0];
        this.gameboard = (Gameboard) parameters[1];

        resetWindow();

    }

    /**
     * Clears the window to default values
     */
    private void resetWindow() {
        this.grdTiles.getColumnConstraints().clear();
        this.grdTiles.getRowConstraints().clear();
        this.comDirection.getItems().clear();
        sizeWindow();
        this.comDirection.getItems().addAll(COLUMN, ROW);
        this.comDirection.getSelectionModel().selectFirst();
        addListeners();
        enableChoiceBoxes();
        this.cmdConfirm.setDisable(true);
    }

    /**
     * Adds Events to the Choice boxes that trigger an event when the selected
     * item changes
     */
    private void addListeners() {
        comDirection.getSelectionModel().selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        updateNumbers(newValue);
                        if (newValue == COLUMN) {
                            lblLeft.setText(TOP);
                            lblRight.setText(BOTTOM);
                        } else {
                            lblLeft.setText(LEFT);
                            lblRight.setText(RIGHT);
                        }
                    });
        comNumber.getSelectionModel().selectedItemProperty()
                 .addListener((observable, oldValue, newValue) -> {
                     updateGrid(
                             comDirection.getSelectionModel().getSelectedItem(),
                             newValue);
                     sizeWindow();
                 });
    }

    /**
     * Update the available column/row numbers in the choice box
     *
     * @param axis Whether the numbers to display correspond to columns or rows
     */
    private void updateNumbers(Axis axis) {
        comNumber.getItems().clear();
        getIndexes(axis);
    }

    /**
     * Loops through each index on the specified Axis
     *
     * @param axis The selected Axis to get the available indexes from
     */
    private void getIndexes(Axis axis) {
        for (int i = 0; i < gameboard.getTiles().getWidth(); i++) {
            ArrayList<Tile> tiles;
            if (axis.equals(COLUMN)) {
                tiles = new ArrayList<>(
                        Arrays.asList(gameboard.getTiles().getColumn(i)));
            } else {
                tiles = new ArrayList<>(
                        Arrays.asList(gameboard.getTiles().getRow(i)));
            }

            AtomicBoolean hasFixed = new AtomicBoolean(false);

            tiles.forEach(tile -> {
                if (tile.isFixed() && !hasFixed.get()) {
                    hasFixed.set(true);
                }
            });

            if (!hasFixed.get()) {
                comNumber.getItems().add(i);
            }
        }
    }

    /**
     * update the gridpane based on the axis and number selected
     */
    private void updateGrid(Axis axis, int number) {
        ArrayList<Tile> tiles;
        if (axis.equals(COLUMN)) {
            tiles = new ArrayList<>(
                    Arrays.asList(this.gameboard.getTiles().getColumn(number)));
        } else {
            tiles = new ArrayList<>(
                    Arrays.asList(this.gameboard.getTiles().getRow(number)));
        }
        this.selectedTiles = new ArrayDeque<>(tiles);
        defineGrid(tiles.size());
        addToGrid(formatTiles(tiles, axis));
    }

    /**
     * Takes Tiles and formats them into stack panes as well as adding an add
     * button to the first and last grid squares
     *
     * @param tiles Tile to be formatted
     *
     * @return Queue of ImageView items containing the Tiles passed to the
     * method
     */
    private ArrayDeque<ImageView> formatTiles(ArrayList<Tile> tiles,
                                              Axis axis) {
        ArrayDeque<ImageView> formattedTiles = new ArrayDeque<>();
        /* For each tile, create an imageview with the tile's image inside */
        tiles.forEach(tile -> {
            ImageView imageView = createImageView(tile.getImage());
            imageView.setRotate(tile.getAngle().get());
            /* If the axis is x, rotate the tile anticlockwise */
            if (axis == COLUMN) {
                imageView.setRotate(imageView.getRotate() - 90);
            }
            formattedTiles.add(imageView);
        });
        /* Create the addLeft and addRight Buttons */
        this.addLeft = createImageView(new Image(ARROW_PATH));
        this.addRight = createImageView(new Image(ARROW_PATH));
        addRight.setRotate(Angle.UP.get());

        enableShiftRollover();
        enableShiftButtons();
        disableChoiceBoxes();
        return formattedTiles;
    }

    /**
     * Enables the addLeft and addRight button events
     */
    private void enableShiftButtons() {
        this.addLeft.setDisable(false);
        this.addLeft.setOnMouseClicked(event -> addToLeft());
        this.addRight.setDisable(false);
        this.addRight.setOnMouseClicked(event -> addToRight());
    }

    /**
     * Adds the tile to the right of the grid
     */
    private void addToRight() {
        this.selectedTiles.addLast(this.tile);
        this.selectedTiles.removeFirst();
        updateGrid(comDirection.getSelectionModel().getSelectedItem(),
                   comNumber.getSelectionModel().getSelectedItem());
        disableShiftRollover();
        disableShiftButtons();
        disableChoiceBoxes();
        cmdConfirm.setDisable(false);
    }

    /**
     * Adds the tile to the left of the grid
     */
    private void addToLeft() {
        this.selectedTiles.addFirst(this.tile);
        this.selectedTiles.removeLast();
        updateGrid(comDirection.getSelectionModel().getSelectedItem(),
                   comNumber.getSelectionModel().getSelectedItem());
        disableShiftRollover();
        disableShiftButtons();
        disableChoiceBoxes();
        cmdConfirm.setDisable(false);
    }

    /**
     * Disables the Choice Boxes
     */
    private void disableChoiceBoxes() {
        Axis selectedAxis = comDirection.getSelectionModel().getSelectedItem();
        Integer selectedNumber =
                comNumber.getSelectionModel().getSelectedItem();
        this.comDirection.setDisable(true);
        this.comDirection.getSelectionModel().select(selectedAxis);
        this.comNumber.setDisable(true);
        this.comNumber.getSelectionModel().select(selectedNumber);
    }

    /**
     * Enables the Choice Boxes
     */
    private void enableChoiceBoxes() {
        this.comDirection.setDisable(false);
        this.comDirection.getSelectionModel().selectFirst();
        this.comNumber.setDisable(false);
        this.comNumber.getSelectionModel().selectFirst();
    }

    /**
     * Disables the Shift Buttons (addLeft & addRight)
     */
    private void disableShiftButtons() {
        this.addLeft.setDisable(true);
        this.addRight.setDisable(true);
    }

    /**
     * Enables the rollover events for the add left and add right buttons
     */
    private void enableShiftRollover() {

        addRight.setOnMouseEntered(
                event -> addRight.setImage(new Image(ARROW_PATH_ROLLOVER)));
        addRight.setOnMouseExited(
                event -> addRight.setImage(new Image(ARROW_PATH)));
        addLeft.setOnMouseEntered(
                event -> addLeft.setImage(new Image(ARROW_PATH_ROLLOVER)));
        addLeft.setOnMouseExited(
                event -> addLeft.setImage(new Image(ARROW_PATH)));

    }

    /**
     * Disables the rollover events for the add left and add right buttons
     */
    private void disableShiftRollover() {
        addLeft.setOnMouseEntered(null);
        addRight.setOnMouseEntered(null);

        addLeft.setOnMouseExited(null);
        addRight.setOnMouseExited(null);
    }

    /**
     * Adds each Tile to the GridPane
     *
     * @param formattedTiles All the Tiles to add to the grid
     */
    private void addToGrid(ArrayDeque<ImageView> formattedTiles) {
        for (int i = 0; i < formattedTiles.size(); i++) {
            grdTiles.add(formattedTiles.poll(), i, 0);
        }
    }

    /**
     * Creates an ImageView Node from a specified image, applying the
     * appropriate properties
     *
     * @param image Image to be place in the tile
     *
     * @return ImageView containing the specified image
     */
    private ImageView createImageView(Image image) {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(false);
        imageView.setFitHeight(TILE_SIZE);
        imageView.setFitWidth(TILE_SIZE);
        return imageView;
    }

    /**
     * Adds the appropriate amount of columns to the grid based on the players
     * selection
     *
     * @param numberOfTiles The Number of tiles on the selected axis
     */
    private void defineGrid(int numberOfTiles) {
        final int PERCENT_WIDTH = 100 / numberOfTiles;
        grdTiles.getColumnConstraints().clear();
        grdTiles.getRowConstraints().clear();
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100);
        grdTiles.getRowConstraints().add(rowConstraints);

        for (int i = 0; i < numberOfTiles; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(PERCENT_WIDTH);
            grdTiles.getColumnConstraints().add(columnConstraints);
        }
    }

    /**
     * Resizes the window based on the number of tiles in the grid. If there are
     * no tiles, the window is sized to its minimum without the grid
     */
    private void sizeWindow() {
        if (grdTiles.getColumnConstraints().size() == 0) {
            rootPane.setMinSize(WINDOW_MIN_WIDTH, WINDOW_HEIGHT - TILE_SIZE);
        } else if ((grdTiles.getColumnConstraints().size() * TILE_SIZE) +
                   PREVIEW_WIDTH < WINDOW_MIN_WIDTH) {
            rootPane.setMinSize(WINDOW_MIN_WIDTH, WINDOW_HEIGHT);
        } else {
            rootPane.setMinSize(
                    (grdTiles.getColumnConstraints().size() * TILE_SIZE) +
                    PREVIEW_WIDTH, WINDOW_HEIGHT);
        }

        rootPane.setMaxSize(rootPane.getMinWidth(), rootPane.getMinHeight());
        rootPane.setPrefSize(rootPane.getMinWidth(), rootPane.getMinHeight());
    }

    /**
     * Handles the Confirm Button click event
     *
     * @param mouseEvent Event Object
     */
    @SuppressWarnings("unused")
    public void cmdConfirmClicked(MouseEvent mouseEvent) {
        selectedTiles.forEach(
                tile -> gameboard.getTiles().set(tile.getCoordinate(), tile));
        Stage stage = (Stage) cmdConfirm.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the Reset Button click event
     *
     * @param mouseEvent Event Object
     */
    @SuppressWarnings("unused")
    public void cmdResetClicked(MouseEvent mouseEvent) {
        resetWindow();
    }


    /**
     * Defines available axes and a corresponding String value for it
     */
    protected enum Axis {
        COLUMN("Column"),
        ROW("Row");
        final String AXIS;

        Axis(String axis) {
            this.AXIS = axis;
        }
    }


}

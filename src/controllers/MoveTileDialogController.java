package controllers;

import constants.Angle;
import core.Gameboard;
import holdables.Tile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import static controllers.MoveTileDialogController.Axis.COLUMN;
import static controllers.MoveTileDialogController.Axis.ROW;

/**
 * TODO
 * -Make sure rotations are right depending on axis and rotation of tile
 * -accept button
 * -push in tile Button
 * -assets for the left and right buttons as well as cross
 * -cancel button for placement
 * -tile in the preview
 * -Make sure players aren't on the tile? or make sure that tiles with players on become fixed
 * -code to change the gameboard and send it back
 * -other things
 */
public class MoveTileDialogController implements InitialisableWithParameters {
    public static final int PREVIEW_WIDTH = 128;
    public static final int WINDOW_MIN_WIDTH = 766;
    private static final double TILE_SIZE = 100;
    private static final String ARROW_PATH_ROLLOVER = "../resources/menu/arrow_rollover.png";
    private static final double WINDOW_HEIGHT = TILE_SIZE + 219;
    private static final String ARROW_PATH = "../resources/menu/arrow.png";
    private ImageView addLeft;
    private ImageView addRight;
    @FXML
    private Button cmdConfirm;
    @FXML
    private ChoiceBox<Axis> comDirection;
    @FXML
    private ChoiceBox<Integer> comNumber;
    @FXML
    private Label lblLeft;
    @FXML
    private Label lblRight;
    @FXML
    private GridPane grdTiles;
    @FXML
    private AnchorPane rootPane;

    private Tile tile;
    private Gameboard gameboard;
    private GameboardController gameboardController;

    /**
     * Initialises the MoveTile {@link constants.Window}
     *
     * @param parameters Objects the method needs to initialise the window
     */
    @Override
    public void initialiseWithParameters(Object[] parameters) {
        this.tile = (Tile) parameters[0];
        this.gameboard = (Gameboard) parameters[1];
        this.gameboardController = (GameboardController) parameters[2];

        this.grdTiles.getColumnConstraints().clear();
        this.grdTiles.getRowConstraints().clear();
        sizeWindow();
        this.comDirection.getItems().addAll(COLUMN, ROW);
        addListeners();

    }

    /**
     * Adds Events to the Choice boxes that trigger an event when the selected item changes
     */
    private void addListeners() {
        comDirection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Axis>() {
            @Override
            public void changed(ObservableValue<? extends Axis> observable, Axis oldValue, Axis newValue) {
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
     * @param axis
     */
    private void getIndexes(Axis axis) {
        for (int i = 0; i < gameboard.getTiles().getWidth(); i++) {
            ArrayList<Tile> tiles;
            if (axis.equals(COLUMN)) {
                tiles = new ArrayList<>(Arrays.asList(gameboard.getTiles().getColumn(i)));
            } else {
                tiles = new ArrayList<>(Arrays.asList(gameboard.getTiles().getRow(i)));
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
            tiles = new ArrayList<Tile>(Arrays.asList(this.gameboard.getTiles().getColumn(number)));
        } else {
            tiles = new ArrayList<Tile>(Arrays.asList(this.gameboard.getTiles().getRow(number)));
        }
        defineGrid(tiles.size());
        addToGrid(formatTiles(tiles));
    }

    /**
     * Takes Tiles and formats them into stack panes as well as adding an add button to the first and last grid squares
     *
     * @param tiles Tile to be formatted
     * @return
     */
    private ArrayDeque<ImageView> formatTiles(ArrayList<Tile> tiles) {
        ArrayDeque<ImageView> formattedTiles = new ArrayDeque<>();
        tiles.forEach(tile -> {

            formattedTiles.add(createImageView(tile.getImage()));
        });
        this.addLeft = createImageView(new Image(ARROW_PATH));
        this.addRight = createImageView(new Image(ARROW_PATH));
        addRight.setRotate(Angle.UP.get());

        enableRollover();

        return formattedTiles;
    }

    /**
     * Enables the rollover events for the add left and add right buttons
     */
    private void enableRollover() {
        addLeft.setOnMouseEntered(event -> addLeft.setImage(new Image(ARROW_PATH_ROLLOVER)));
        addRight.setOnMouseEntered(event -> addRight.setImage(new Image(ARROW_PATH_ROLLOVER)));

        addLeft.setOnMouseExited(event -> addLeft.setImage(new Image(ARROW_PATH)));
        addRight.setOnMouseExited(event -> addRight.setImage(new Image(ARROW_PATH)));
    }

    /**
     * Disables the rollover events for the add left and add right buttons
     */
    private void disableRollover() {
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
     * Creates an ImageView Node from a specified image, applying the appropriate properties
     *
     * @param image Image to be place in the tile
     * @return ImageView containing the specified image
     */
    private ImageView createImageView(Image image) {
        ImageView imageView = new ImageView();
        imageView.setImage(tile.getImage());
        imageView.setRotate(tile.getAngle().get());
        imageView.setPreserveRatio(false);
        imageView.setFitHeight(TILE_SIZE);
        imageView.setFitWidth(TILE_SIZE);
        return imageView;
    }

    /**
     * Adds the appropriate amount of columns to the grid based on the players selection
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
     * Resizes the window based on the number of tiles in the grid. If there are no tiles, the window is sized to its minimum without the grid
     */
    private void sizeWindow() {
        if (grdTiles.getColumnConstraints().size() == 0) {
            rootPane.setMinSize(WINDOW_MIN_WIDTH, WINDOW_HEIGHT - TILE_SIZE);
        } else if ((grdTiles.getColumnConstraints().size() * TILE_SIZE) + PREVIEW_WIDTH < WINDOW_MIN_WIDTH) {
            rootPane.setMinSize(WINDOW_MIN_WIDTH, WINDOW_HEIGHT);
        } else {
            rootPane.setMinSize((grdTiles.getColumnConstraints().size() * TILE_SIZE) + PREVIEW_WIDTH, WINDOW_HEIGHT);
        }

        rootPane.setMaxSize(rootPane.getMinWidth(), rootPane.getMinHeight());
        rootPane.setPrefSize(rootPane.getMinWidth(), rootPane.getMinHeight());
    }

    public void cmdConfirmClicked(MouseEvent mouseEvent) {
    }


    /**
     * Defines available axes and a corresponding String value for it
     */
    protected enum Axis {
        COLUMN("Column"), ROW("Row");
        final String AXIS;

        Axis(String axis) {
            this.AXIS = axis;
        }
    }


}

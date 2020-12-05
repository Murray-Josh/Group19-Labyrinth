package controllers;


import static controllers.MoveTileDialogController.Axis.COLUMN;
import static controllers.MoveTileDialogController.Axis.ROW;

import constants.Angle;
import core.Coordinate;
import core.Gameboard;
import holdables.Tile;
import holdables.TileEffect;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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

public class MoveTileDialogController implements InitialisableWithParameters {

    /**
     * Window and Tile sizes used in this class
     */
    private static final double TILE_PREVIEW_WIDTH = 128;
    private static final double WINDOW_MIN_WIDTH = 811;
    private static final double TILE_SIZE = 100;
    private static final double WINDOW_MAX_HEIGHT = 293;
    /**
     * Paths to the add tile button assets
     */
    private static final String ADD_PATH = "resources/menu/add.png";
    private static final String ADD_PATH_ROLLOVER = "resources/menu/add_rollover.png";
    /**
     * Strings indicating the direction of the axis
     */
    private static final String TOP = "Top";
    private static final String BOTTOM = "Bottom";
    private static final String LEFT = "Left";
    private static final String RIGHT = "Right";
    /**
     * Fields common to most methods in this Controller
     */
    private Tile tileToPlace;
    private Gameboard gameboard;
    private LinkedList<Tile> axisContents = new LinkedList<>();
    private LinkedList<ImageView> formattedTiles;
    private Axis axis;
    private Integer index;
    private ImageView addLeft;
    private ImageView addRight;

    /**
     * parameters
     */
    private GameboardController controller;
    private Stage stage;


    /**
     * FXML Controls
     */
    @FXML
    private ChoiceBox<Axis> comAxis;
    @FXML
    private ChoiceBox<Integer> comIndex;
    @FXML
    private Label lblLeft;
    @FXML
    private Label lblRight;
    @FXML
    private GridPane grdTiles;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView imgTile;


    /**
     * Creates an ImageView Node from a specified image, applying the appropriate properties
     *
     * @param image Image to be place in the tile
     * @return ImageView containing the specified image
     */
    public static ImageView createImageView(Image image, double angle) {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(false);
        imageView.setFitHeight(TILE_SIZE);
        imageView.setFitWidth(TILE_SIZE);
        imageView.setRotate(angle);
        return imageView;
    }

    /**
     * Initialises the Place a Tile dialog with parameters passed from the initialising class
     *
     * @param parameters Parameters of this Place a tile Dialog
     */
    @Override
    public void initialiseWithParameters(Object[] parameters, Scene scene, Stage stage) {
        this.stage = stage;

        this.tileToPlace = (Tile) parameters[0];
        this.gameboard = (Gameboard) parameters[1];
        this.controller = (GameboardController) parameters[2];
        initialise();
    }

    /**
     * Resets the window and its controls to their default values
     */
    private void initialise() {
        imgTile.setImage(tileToPlace.getImage());
        imgTile.setRotate(tileToPlace.getAngle().get());
        grdTiles.getRowConstraints().clear();
        grdTiles.getColumnConstraints().clear();
        comIndex.getItems().clear();
        comAxis.setItems(FXCollections.observableArrayList(COLUMN, ROW));
        comIndex.getItems().clear();
        comIndex.setDisable(true);
        enableChoiceBoxes();
        defineChoiceBoxListeners();
        updateWindowSize();
    }

    /**
     * Creates event listeners for bot the Axis and the Index Choice Boxes
     */
    private void defineChoiceBoxListeners() {

        /*Direction on change Event */
        comAxis.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    comIndex.getItems().clear();
                    this.axis = newValue;
                    if (newValue == COLUMN) {
                        lblLeft.setText(TOP);
                        lblRight.setText(BOTTOM);
                    } else if (newValue == ROW) {
                        lblLeft.setText(LEFT);
                        lblRight.setText(RIGHT);
                    }
                    comIndex.setDisable(false);
                    populateIndexes();
                });

        /* Index on change Event */
        comIndex.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    this.index = newValue;
                    populateGrid(true);
                    enableShiftButtons();
                });
    }

    /**
     * Adds all the tiles at the selected index to the grid, rotating them if there are in a column
     */
    private void populateGrid(boolean shiftButtons) {
        formattedTiles = new LinkedList<>();
        axisContents = new LinkedList<>();
        if (axis == COLUMN) {
            axisContents = getTilesColumn();
            axisContents.forEach(tile -> formattedTiles
                    .add(createImageView(tile.getImage(), tile.getAngle().get() - 90)));
        } else if (axis == ROW) {
            axisContents = getTilesRow();
            axisContents.forEach(tile -> formattedTiles
                    .add(createImageView(tile.getImage(), tile.getAngle().get())));
        }
        if (shiftButtons) {
            this.addLeft = createImageView(new Image(ADD_PATH), 0);
            this.addRight = createImageView(new Image(ADD_PATH), 180);
            addRight.setRotate(Angle.UP.get());
            formattedTiles.addFirst(addLeft);
            formattedTiles.addLast(addRight);
        }
        defineGridSize();
        for (int i = 0; i < formattedTiles.size(); i++) {
            grdTiles.add(formattedTiles.get(i), i, 0);
        }
        updateWindowSize();
    }

    /**
     * Calculates and sets the Window size based on the amount of tiles in the Grid
     */
    private void updateWindowSize() {
        stage.setResizable(false);
        double width = (grdTiles.getColumnConstraints().size() * TILE_SIZE) + TILE_PREVIEW_WIDTH;
        if (WINDOW_MIN_WIDTH > width) {
            stage.setWidth(WINDOW_MIN_WIDTH);
        } else {
            stage.setWidth(width);
        }
        stage.centerOnScreen();
    }

    /**
     * Gets the tiles on a specified row and returns them in a linked list
     *
     * @return List of tiles on selected row index
     */
    private LinkedList<Tile> getTilesRow() {
        return new LinkedList<>(gameboard.getTiles().getRow(index));
    }

    /**
     * Gets the tiles on a specified column and returns them in a linked list
     *
     * @return List of tiles on selected column index
     */
    private LinkedList<Tile> getTilesColumn() {
        return new LinkedList<>(gameboard.getTiles().getColumn(index));
    }

    /**
     * Finds all indexes on the selected axis where there are no fixed tiles or the Ice Effect isn't
     * active;
     */
    private void populateIndexes() {
        /* If the selected axis is a column */
        if (this.axis == COLUMN) {
            /* Loop through each column */
            for (int i = 0; i < gameboard.getWidth(); i++) {
                AtomicBoolean hasFixed = new AtomicBoolean(false);
                /*Loop throw each tile in column i and check if any are fixed or have the ice effect*/
                for (Tile tile : gameboard.getTiles().getColumn(i)) {
                    if (tile.isFixed() || tile.getEffect().equals(TileEffect.ICE) || hasFixed
                            .get()) {
                        hasFixed.set(true);
                    }
                }
                /*If there are ot fixed tiles in the column, add it to the ChoiceBox*/
                if (!hasFixed.get()) {
                    comIndex.getItems().add(i);
                }
            }
            /*If the axis is rows */
        } else {
            /*Loop through each row */
            for (int i = 0; i < gameboard.getHeight(); i++) {
                AtomicBoolean hasFixed = new AtomicBoolean(false);
                /*Check if there is a fixed tile in that row*/
                for (Tile tile : gameboard.getTiles().getRow(i)) {
                    if (tile.isFixed() || tile.getEffect().equals(TileEffect.ICE) || hasFixed
                            .get()) {
                        hasFixed.set(true);
                    }
                }
                /*If there are ot fixed tiles in the row, add it to the ChoiceBox*/
                if (!hasFixed.get()) {
                    comIndex.getItems().add(i);
                }
            }
        }
    }

    /**
     * Defines events for addLeft and addRight and enables them
     */
    private void enableShiftButtons() {
        addLeft.setDisable(false);
        addLeft.setOnMouseClicked(event -> addToLeft());
        addRight.setDisable(false);
        addRight.setOnMouseClicked(event -> addToRight());

        addRight.setOnMouseEntered(
                event -> addRight.setImage(new Image(ADD_PATH)));

        addRight.setOnMouseExited(
                event -> addRight.setImage(new Image(ADD_PATH_ROLLOVER)));
        addLeft.setOnMouseEntered(
                event -> addLeft.setImage(new Image(ADD_PATH)));

        addLeft.setOnMouseExited(
                event -> addLeft.setImage(new Image(ADD_PATH_ROLLOVER)));
    }

    /**
     * Adds the Tile to the right of the grid
     */
    private void addToRight() {
        Tile pushedOff = axisContents.removeFirst();
        gameboard.getSilkBag().addLast(pushedOff);
        gameboard.getTiles().remove(pushedOff.getCoordinate());
        axisContents.forEach(tile -> {
            if (axis == COLUMN) {
                tile.getCoordinate().setX(index);
                tile.getCoordinate().decreaseY(1);
            } else if (axis == ROW) {
                tile.getCoordinate().decreaseX(1);
                tile.getCoordinate().setY(index);
            }
            gameboard.getTiles().set(tile.getCoordinate(), tile);
        });
        if (axis == COLUMN) {
            tileToPlace.setCoordinate(new Coordinate(index, gameboard.getHeight() - 1));
        } else if (axis == ROW) {
            tileToPlace.setCoordinate(new Coordinate(gameboard.getWidth() - 1, index));
        }
        gameboard.getTiles().set(tileToPlace.getCoordinate(), tileToPlace);
        controller.refresh();
        stage.close();
    }

    /**
     * Adds the tile to the left of the grid
     */
    private void addToLeft() {
        Tile pushedOff = axisContents.removeLast();
        gameboard.getSilkBag().addLast(pushedOff);
        gameboard.getTiles().remove(pushedOff.getCoordinate());
        axisContents.forEach(tile -> {
            if (axis == COLUMN) {
                tile.getCoordinate().setX(index);
                tile.getCoordinate().increaseY(1);

            } else if (axis == ROW) {
                tile.getCoordinate().increaseX(1);
                tile.getCoordinate().setY(index);
            }
            gameboard.getTiles().set(tile.getCoordinate(), tile);
        });
        if (axis == COLUMN) {
            tileToPlace.setCoordinate(new Coordinate(index, 0));
        } else if (axis == ROW) {
            tileToPlace.setCoordinate(new Coordinate(0, index));
        }
        gameboard.getTiles().set(tileToPlace.getCoordinate(), tileToPlace);
        controller.refresh();
        stage.close();
    }

    /**
     * Disables the shift buttons
     */
    private void disableShiftButtons() {
        addLeft.setDisable(true);
        addRight.setDisable(true);
    }

    /**
     * Enables the ChoiceBoxes
     */
    private void enableChoiceBoxes() {
        comAxis.setDisable(false);
        comIndex.setDisable(false);
    }

    /**
     * Disables the ChoiceBoxes
     */
    private void disableChoiceBoxes() {
        Axis selectedAxis = comAxis.getSelectionModel().getSelectedItem();
        Integer selectedNumber =
                comIndex.getSelectionModel().getSelectedItem();
        comAxis.setDisable(true);
        comAxis.getSelectionModel().select(selectedAxis);
        comIndex.setDisable(true);
        comIndex.getSelectionModel().select(selectedNumber);
    }

    /**
     * Redefines the number of columns in the grid to the amount of Tiles on the selected Axis + the
     * AddLeft and AddRight Buttons
     */
    private void defineGridSize() {
        grdTiles.getColumnConstraints().clear();
        grdTiles.getRowConstraints().clear();
        final int PERCENT_WIDTH = 100 / axisContents.size() + 2;

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        grdTiles.getRowConstraints().setAll(row);

        for (int i = 0; i < axisContents.size() + 2; i++) {
            ColumnConstraints c = new ColumnConstraints();
            c.setPercentWidth(PERCENT_WIDTH);
            grdTiles.getColumnConstraints().add(c);
        }
        updateWindowSize();
    }

    /*
    public void cmdConfirmClicked(MouseEvent mouseEvent) {
       axisContents.forEach(tile -> gameboard.getTiles().set(tile.getCoordinate(), tile));
       this.stage.close();
    }
 */
    public void cmdRotateClick(MouseEvent mouseEvent) {
        imgTile.setRotate(imgTile.getRotate() + 90);
        tileToPlace.setAngle(Angle.toAngle(imgTile.getRotate()));
    }


    /**
     * Creates a type called Axis to be used to identify the axis of the selected linear array of
     * tiles
     */
    protected enum Axis {
        COLUMN("Column"), ROW("ROW");
        private final String AXIS;

        Axis(String axis) {
            this.AXIS = axis;
        }

        private String get() {
            return this.AXIS;
        }

    }


}

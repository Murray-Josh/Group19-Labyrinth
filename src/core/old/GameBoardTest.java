package core.old;

import constants.TileType;
import core.Coordinate;
import core.Gameboard;
import holdables.Tile;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import styles.CarStyle;
import styles.Style;

/**
 * Gameboard Test when run creates a grid of junction tile images - WIP
 *
 * @author Joshua Murray
 */
public class GameBoardTest extends Application {


    private Gameboard gameboardTest;
    private GridPane gridPane;


    @Override
    public void start(Stage primaryStage) throws Exception {
        gameboardTest = new Gameboard("src/resources/file/GameboardOne");
        gridPane = new GridPane();
        redraw();

        primaryStage.setTitle("Game Board Test");

        for (int y = 0; y < gameboardTest.getSize()[1]; y++) {
            addButtonY(gridPane, gameboardTest.getSize()[0] + 1, y);
            addButtonY(gridPane, 0, y);

        }
        for (int x = 0; x < gameboardTest.getSize()[0]; x++) {
            addButtonX(gridPane, x, gameboardTest.getSize()[1] + 1);

        }

        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Adds a button for the verticle
     *
     * @param grid     Grid pane
     * @param colIndex Column Index for button to be placed at
     * @param rowIndex Row Index for button to be placed at
     */
    private void addButtonY(GridPane grid, int colIndex, int rowIndex) {
        Button button = new Button();
        button.setText("Row: " + rowIndex);
        button.setOnMouseClicked(e -> {
            boolean fixedCol = false;
            for (int x = 0; x < gameboardTest.getSize()[0]; x++) {
                if (gameboardTest.getTile(x, rowIndex).isFixed()) {
                    fixedCol = true;
                }
            }
            if (!fixedCol) {
                tileMove(new Tile(TileType.CORNER, gameboardTest.getStyle(), 90, false), gameboardTest, "Row", rowIndex);
                redraw();
            }
        });
        grid.add(button, colIndex, rowIndex);
    }

    /**
     * Adds a button for the horizontal
     *
     * @param grid     Grid pane
     * @param colIndex Column Index for button to be placed at
     * @param rowIndex Row Index for button to be placed at
     */
    private void addButtonX(GridPane grid, int colIndex, int rowIndex) {
        Button button = new Button();
        button.setText("Column: " + colIndex);
        button.setOnMouseClicked(e -> {
            boolean fixedRow = false;
            for (int y = 0; y < gameboardTest.getSize()[1]; y++) {
                if (gameboardTest.getTile(colIndex, y).isFixed()) {
                    fixedRow = true;
                }
            }
            if (!fixedRow) {
                tileMove(new Tile(TileType.CORNER, gameboardTest.getStyle(), 90, false), gameboardTest, "Column", colIndex);
                redraw();
            }
        });
        grid.add(button, colIndex, rowIndex);
    }


    private void addPane(GridPane grid, int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            System.out.println("Clicked on [" + colIndex + "," + rowIndex + "]");

            Tile effectSet = gameboardTest.getTile(colIndex, rowIndex);
            effectSet.setOnFire();
            gameboardTest.setTile(effectSet, colIndex, rowIndex);
            redraw();

        });
        grid.add(pane, colIndex, rowIndex);

    }

    /**
     * Tile Move method that adds a new tile and shifts all tiles in the row across by one
     *
     * @param tile        Tile to be added to end
     * @param gameboard   Gameboard for tile to be added to
     * @param rowOrColumn String of whether inserting in row or column
     * @param num         Row/Column number
     * @return Gameboard in new state
     */
    public Gameboard tileMove(Tile tile, Gameboard gameboard, String rowOrColumn, int num) {

        if (rowOrColumn.equals("Column")) {
            Tile newTile = tile;

            for (int y = 0; y < gameboard.getSize()[0]; y++) {
                Tile tempTile = gameboard.getTile(num, y);
                newTile.setCoordinate(new Coordinate(num, y));
                gameboard.setTile(newTile, num, y);
                newTile = tempTile;
            }

        } else if (rowOrColumn.equals("Row")) {
            Tile newTile = tile;
            for (int x = 0; x < gameboard.getSize()[1]; x++) {
                Tile tempTile = gameboard.getTile(x, num);
                newTile.setCoordinate(new Coordinate(x, num));
                gameboard.setTile(newTile, x, num);
                newTile = tempTile;
            }
        }
        return gameboard;
    }

    /**
     * Redraws the gameboard when called
     */
    public void redraw() {
        CarStyle c = new CarStyle();
        Image startImage = Style.getCornerFire();
        for (int y = 0; y < gameboardTest.getSize()[1]; y++) {

            for (int x = 0; x < gameboardTest.getSize()[0]; x++) {

                ImageView iv1 = new ImageView();

                if (gameboardTest.isStart(gameboardTest.getTile(x, y).getCoordinate().getX(), gameboardTest.getTile(x, y).getCoordinate().getY())) {
                    iv1.setImage(startImage);

                } else {
                    iv1.setImage(gameboardTest.getTile(x, y).getImage());

                }
                iv1.setFitHeight(100);
                iv1.setFitWidth(100);
                iv1.setRotate(gameboardTest.getTile(x, y).getAngle());


                gridPane.add(iv1, x, y);
                addPane(gridPane, x, y);

            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
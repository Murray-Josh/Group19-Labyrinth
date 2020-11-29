package core;

import holdables.CornerTile;
import holdables.Tile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import styles.CarStyle;

/**
 * Gameboard Test when run creates a grid of junction tile images - WIP
 *
 * @author Joshua Murray
 */
public class GameBoardTest extends Application {


    private Gameboard2 gameboardTest;
    private GridPane gridPane;


    @Override
    public void start(Stage primaryStage) throws Exception {
        gameboardTest = new Gameboard2("src/resources/file/GameboardOne");
        gridPane = new GridPane();
        redraw();

        primaryStage.setTitle("Game Board Test");

        for (int y = 0; y < gameboardTest.getSize()[1]; y++) {
            addButtonY(gridPane, gameboardTest.getSize()[0] + 1, y);
        }
        for (int x = 0; x < gameboardTest.getSize()[0]; x++) {
            addButtonX(gridPane, x, gameboardTest.getSize()[1] + 1);

        }
        Scene scene = new Scene(gridPane, 1280, 720);
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
        button.setOnMouseClicked(e -> {
            System.out.printf("Clicked on [%d, %d]%n", colIndex, rowIndex);
            tileMove(new Tile(new CornerTile(), gameboardTest.getStyle(), 90, false), gameboardTest, "Row", rowIndex);
            redraw();
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
        button.setOnMouseClicked(e -> {
            System.out.printf("Clicked on [%d, %d]%n", colIndex, rowIndex);

            tileMove(new Tile(new CornerTile(), gameboardTest.getStyle(), 90, false), gameboardTest, "Column", colIndex);
            redraw();
        });
        grid.add(button, colIndex, rowIndex);
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
    public Gameboard2 tileMove(Tile tile, Gameboard2 gameboard, String rowOrColumn, int num) {

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
        Image startImage = c.getCornerFire();
        Image fixedImage = c.getJunctionIce();
        for (int y = 0; y < gameboardTest.getSize()[1]; y++) {

            for (int x = 0; x < gameboardTest.getSize()[0]; x++) {

                ImageView iv1 = new ImageView();

                if (gameboardTest.isStart(gameboardTest.getTile(x, y).getCoordinate().getX(), gameboardTest.getTile(x, y).getCoordinate().getY())) {
                    iv1.setImage(startImage);
                } else if (gameboardTest.getTile(x, y).isFixed()) {
                    iv1.setImage(fixedImage);
                } else {
                    iv1.setImage(gameboardTest.getTile(x, y).getImage());
                }
                iv1.setFitHeight(100);
                iv1.setFitWidth(100);
                iv1.setRotate(gameboardTest.getTile(x, y).getAngle());

                gridPane.add(iv1, x, y);
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

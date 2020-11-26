package core;

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
 * Joshua Murray
 */
public class GameBoardTest extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Game Board Test");
        Image image = new Image("Cars_Style/cars_junction.png");

        //Image image = CarStyle.getCornerTile();
        GridPane gridPane = new GridPane();

        //gridPane.add(iv1, 0, 1);

        for(int y = 0; y<10; y++){

            for(int x = 0; x<10; x++) {

                ImageView iv1 = new ImageView();
                iv1.setImage(image);
                iv1.setFitHeight(100);
                iv1.setFitWidth(100);
                gridPane.add(iv1, y, x);
                //gridPane.add(new Button("Button" + x + y), y, x, 1, 1);
            }
        }
        System.out.println(gridPane.getChildren().get(1));



        Scene scene = new Scene(gridPane, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

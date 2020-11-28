package core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import styles.CarStyle;
import styles.Style;

/**
 * Gameboard Test when run creates a grid of junction tile images - WIP
 * @author Joshua Murray
 */
public class GameBoardTest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Gameboard2 gameboardTest = new Gameboard2("resources/file/GameboardOne");
        CarStyle c = new CarStyle();
        primaryStage.setTitle("Game Board Test");
        Image startImage = c.getCornerFire();
        Image fixedImage = c.getJunctionIce();
        Image normalImage = c.getJunctionTile();
        GridPane gridPane = new GridPane();

        //gridPane.add(iv1, 0, 1);
        //System.out.println(gameboardTest.getTile(4,0).getType().toString());

        for (int y = 0; y < gameboardTest.getSize()[1]; y++) {

            for (int x = 0; x < gameboardTest.getSize()[0]; x++) {
                System.out.println(gameboardTest.getTile(x, y).getAngle());

                ImageView iv1 = new ImageView();
                
                if(gameboardTest.isStart(gameboardTest.getTile(x,y).getCoordinate().getX(), gameboardTest.getTile(x,y).getCoordinate().getY())) {
                    iv1.setImage(startImage);
                }
                else if(gameboardTest.getTile(x, y).isFixed()) {
                    iv1.setImage(fixedImage);
                }
                else {
                    iv1.setImage(normalImage);
                }
                
                iv1.setFitHeight(100);
                iv1.setFitWidth(100);
                iv1.setRotate(gameboardTest.getTile(x, y).getAngle());
                gridPane.add(iv1, x, y);
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

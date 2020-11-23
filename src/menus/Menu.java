package menus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Menu extends Application {
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        Button start = new Button("Start New Game");
        start.setOnAction(event -> {

        });

        Button loadGame = new Button("Load Game");
        loadGame.setOnAction(event -> {

        });
        Scene scene = new Scene(root);
        primaryStage.setTitle("The Best Labyrinth ... In the world");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}

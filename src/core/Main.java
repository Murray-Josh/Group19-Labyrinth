package core;

import constants.ErrorMsg;
import constants.Title;
import controllers.StageController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Main extends Application {



    public static void main(String[] args) {

        /*
        Level level = new Level("src/resources/file/GameboardOne");
        try {
            FileOutputStream fileOut = new FileOutputStream(new File("src/resources/file/level"));
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(level);
            fileOut.close();
            objOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            StageController.showError(ErrorMsg.PROFILE_WRITE_ERROR, Title.ERROR, false);
        }
        */
        launch(args);



    }



    /**
     * Starts the program
     * @param primaryStage Stage passed from javafx
     */
    @Override
    public void start(Stage primaryStage) {
        StageController.start(primaryStage);
    }


}

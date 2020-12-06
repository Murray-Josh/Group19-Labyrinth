package controllers;

import static controllers.StageController.changeScene;
import static controllers.StageController.home;
import static controllers.StageController.showError;

import constants.ErrorMessage;
import constants.LevelType;
import constants.Title;
import constants.Window;
import core.Gameboard;
import core.Level;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import players.PlayerProfile;
import players.Profiles;
import styles.CarStyle;
import styles.MouseStyle;
import styles.PirateStyle;
import styles.Style;

/**
 * Controls the window that allows the user to set up a new game
 *
 * @author Joseph Omar
 * @version 1.5
 */
@SuppressWarnings("unused")
public class SetUpGameController implements Initializable {

   /**
    * Strings referencing available styles
    */
   public static final String CAR = "Car";
   public static final String MOUSE_TRAP = "Mouse Trap";
   public static final String PIRATE = "Pirate";

   /**
    * Default selection
    */
   private static final PlayerProfile none = new PlayerProfile("--None--");

   private final ObservableList<PlayerProfile> profiles = FXCollections
        .observableArrayList(Profiles.getProfiles());
   private final ArrayList<PlayerProfile> players = new ArrayList<>();

   /**
    * Controls on the Set Up Game window
    */
   @FXML
   private ChoiceBox<PlayerProfile> comOne;
   @FXML
   private ChoiceBox<PlayerProfile> comTwo;
   @FXML
   private ChoiceBox<PlayerProfile> comThree;
   @FXML
   private ChoiceBox<PlayerProfile> comFour;
   private ArrayList<ChoiceBox<PlayerProfile>> boxes;
   @FXML
   private ChoiceBox<LevelType> comBoard;
   @FXML
   private ChoiceBox<String> comStyle;
   @FXML
   private Button cmdStart;
   @FXML
   private Button cmdBack;
   @FXML
   private Button cmdLockOne;
   @FXML
   private Button cmdLockTwo;
   @FXML
   private Button cmdLockThree;
   @FXML
   private Button cmdLockFour;


   private ArrayList<Button> buttons;
   private URL location;
   private ResourceBundle resourceBundle;

   /**
    * Returns the user to the main menu
    *
    * @param actionEvent Event
    */
   public void cmdBackClick(ActionEvent actionEvent) {
      home();
   }

   /**
    * uses the data inputted onto the form and creates a new gameboard and passes it to the
    * Gameboard window
    *
    * @param actionEvent MouseEvent
    */
   public void cmdStartClick(ActionEvent actionEvent) {
      Style style;
      Level level = null;
      String styleString = comStyle.getValue();
      if (styleString.equals(MOUSE_TRAP)) {

         style = new MouseStyle();
      } else if (styleString.equals(CAR)) {
         style = new CarStyle();
      } else {
         style = new PirateStyle();
      }
      try {
         level = new Level(comBoard.getSelectionModel().getSelectedItem().getPath());
      } catch (FileNotFoundException e) {
         showError(ErrorMessage.BOARD_CREATE_ERROR, Title.SETUP, false);
         initialize(location, resourceBundle);
      }
      try {
         Gameboard gameboard = new Gameboard(comBoard.getSelectionModel().getSelectedItem(),level, style, players);
         changeScene(Window.BOARD, new Object[]{false, gameboard});
      } catch (Exception e) {
         showError(ErrorMessage.BOARD_CREATE_ERROR, Title.SETUP, false);
         e.printStackTrace();
         initialize(location, resourceBundle);
      }
   }


   /**
    * Initialises the Set Up Game Window
    *
    * @param location  Location of the FXML file
    * @param resources Resources needed by the Controller
    */
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      this.buttons = new ArrayList<>(
           Arrays.asList(cmdLockOne, cmdLockTwo, cmdLockThree, cmdLockFour));
      this.boxes = new ArrayList<>(Arrays.asList(comOne, comTwo, comThree, comFour));
      this.location = location;
      this.resourceBundle = resources;
      profiles.add(none);
      boxes.forEach(box -> {
         box.setItems(profiles);
         box.getSelectionModel().select(none);
      });
      boxes.remove(comOne);
      buttons.remove(cmdLockOne);
      boxes.forEach(box -> box.setDisable(true));
      buttons.forEach(button -> button.setDisable(true));
      initialiseStyles();
      initialiseLevels();
   }

   /**
    * Lock the selection of Choice Box Four
    *
    * @param actionEvent Event
    */
   public void cmdLockFourClick(ActionEvent actionEvent) {
      lock(cmdLockFour, null, comFour, null, 4);
   }

   /**
    * Lock the selection of Choice Box Three
    *
    * @param actionEvent Event
    */
   public void cmdLockThreeClick(ActionEvent actionEvent) {
      lock(cmdLockThree, cmdLockFour, comThree, comFour, 3);
   }

   /**
    * Lock the selection of Choice Box Two
    *
    * @param actionEvent Event
    */
   public void cmdLockTwoClick(ActionEvent actionEvent) {
      lock(cmdLockTwo, cmdLockThree, comTwo, comThree, 2);
   }

   /**
    * Locks the selection of Choice Box One
    *
    * @param actionEvent Event
    */
   public void cmdLockOneClick(ActionEvent actionEvent) {
      lock(cmdLockOne, cmdLockTwo, comOne, comTwo, 1);
   }

   /**
    * Checks if the current selection is valid
    *
    * @param currentButton    Current Lock Button
    * @param nextButton       Next Lock Button
    * @param currentChoiceBox Current Choice Box
    * @param nextChoiceBox    Next Choice Box
    * @param slotNumber       Player number (1-4)
    */
   private void lock(Button currentButton, Button nextButton,
        ChoiceBox<PlayerProfile> currentChoiceBox, ChoiceBox<PlayerProfile> nextChoiceBox,
        int slotNumber) {
      PlayerProfile selection = currentChoiceBox.getSelectionModel().getSelectedItem();
      boolean isNone = selection.equals(none);
      if (isNone && (slotNumber == 1 || slotNumber == 2)) {
         showError(ErrorMessage.PROFILE_NOT_VALID, Title.SETUP, false);
         currentChoiceBox.getSelectionModel().select(none);
      } else if (isNone && slotNumber == 3) {
         currentButton.setDisable(true);
         currentChoiceBox.setDisable(true);
         nextButton.setDisable(true);
         nextChoiceBox.setDisable(true);
         currentChoiceBox.setValue(none);
         nextChoiceBox.setValue(none);
      } else {

         this.players.add(selection);
         updateChoiceBoxes(selection, currentButton, currentChoiceBox, nextButton, nextChoiceBox);
      }
   }

   /**
    * Updates the remaining choice box based on the selected item of the current choice box.
    * Disables the current box and lock button and enables the next box and lock button
    *
    * @param selection        Current Profile selection
    * @param currentButton    Current Lock Button
    * @param currentChoiceBox Current Choice Box
    * @param nextButton       Next Lock Button
    * @param nextChoiceBox    Next Choice Box
    */
   private void updateChoiceBoxes(PlayerProfile selection, Button currentButton,
        ChoiceBox<PlayerProfile> currentChoiceBox, Button nextButton,
        ChoiceBox<PlayerProfile> nextChoiceBox) {
      currentButton.setDisable(true);
      currentChoiceBox.setDisable(true);
      this.profiles.remove(selection);
      buttons.remove(currentButton);
      boxes.remove(currentChoiceBox);
      boxes.forEach(box -> box.setItems(profiles));
      if (nextButton != null && nextChoiceBox != null) {
         nextButton.setDisable(false);
         nextChoiceBox.setDisable(false);
      }
      /* Creates a new Instance of the selected profile with the same name so that it can be displayed after being removed from profiles */
      PlayerProfile forDisplay = new PlayerProfile(selection.getName());
      currentChoiceBox.setItems(FXCollections.observableArrayList(forDisplay));
      currentChoiceBox.setValue(forDisplay);
   }

   /**
    * Adds all the available styles to the choice box
    */
   private void initialiseStyles() {
      comStyle.getItems().addAll(CAR, MOUSE_TRAP, PIRATE);
      comStyle.getSelectionModel().selectFirst();
   }

   /**
    * Gets all the level files and adds them to the {@link ChoiceBox}
    */
   private void initialiseLevels() {
      comBoard.getItems().clear();
   for (LevelType levelType : LevelType.values()) {
      comBoard.getItems().add(levelType);
   }
   }
}


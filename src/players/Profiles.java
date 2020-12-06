package players;

import constants.ErrorMessage;
import constants.Title;
import controllers.StageController;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

/**
 * Handles the saving, loading and manipulating of all the Player Profiles saved from the game
 *
 * @author Martin Samm
 * @author Joseph Omar
 * @version 3.0
 */
public class Profiles {

    private static final String PATH = "profiles.ser";
    private static ArrayList<PlayerProfile> profiles = new ArrayList<>();

    /**
     * Adds a profile to the list of profiles and then refreshes
     *
     * @param profile Profile you want to add
     */
    public static void add(PlayerProfile profile) {
        profiles.add(profile);
        refresh();
    }

    /**
     * Shows and controls the Create a Profile dialog.
     */
    public static void showCreate() {
        refresh();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setGraphic(null);
        dialog.setHeaderText("Create Your Profile");
        dialog.setContentText(null);
        dialog.setTitle(Title.CREATE_PROFILE.toString());
        dialog.getEditor().setPromptText("Nickname");
        dialog.getDialogPane().getStylesheets().add("./resources/css/dialog.css");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            PlayerProfile newProfile = new PlayerProfile(result.get());
            if (!(exists(newProfile))) {
                add(newProfile);
                StageController.showConfirmation("The profile " + newProfile.getName() +
                                " has successfully been created.", "Profile Creation Complete",
                        Title.CREATE_PROFILE.toString());
            } else {
                StageController
                        .showError(ErrorMessage.PROFILE_ADD_ERROR, Title.CREATE_PROFILE, false);
                showCreate();
            }
        } else {
            dialog.close();
        }
    }

    /**
     * Shows and controls the Delete a Profile dialog
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void showDelete() {
        refresh();
        ChoiceDialog dialog = new ChoiceDialog();
        dialog.setGraphic(null);
        dialog.setHeaderText("Delete a Profile");
        dialog.setContentText(null);
        dialog.setTitle(Title.DELETE_PROFILE.toString());
        dialog.getDialogPane().getStylesheets().add("./resources/css/dialog.css");
        dialog.getItems().addAll(profiles);
        Optional<PlayerProfile> result = dialog.showAndWait();
        if (result.isPresent()) {
            remove(result.get());
            StageController.showConfirmation("The profile " + result.get().getName() +
                            " has successfully been deleted.", "Delete Complete",
                    Title.DELETE_PROFILE.toString());
        } else {
            dialog.close();
        }
    }

    /**
     * Calls save() and then load(), refreshing the available profiles
     */
    public static void refresh() {
        if (!profiles.isEmpty()) {
            save();
        }
        load();
    }

    /**
     * Removes a profile from the list of profiles then refreshes
     *
     * @param profile Profile you want to remove
     */
    public static void remove(PlayerProfile profile) {
        profiles.remove(profile);
        refresh();
    }

    /**
     * Loads all profiles from profiles.ser
     */
    @SuppressWarnings("unchecked")
    public static void load() {
        try {
            FileInputStream fileIn = new FileInputStream(PATH);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            profiles = (ArrayList<PlayerProfile>) objIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            StageController.showError(ErrorMessage.PROFILE_READ_ERROR, Title.ERROR, false);
        }
    }

    /**
     * Saves all the profiles to profiles.ser
     */
    public static void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(PATH);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(profiles);
            fileOut.close();
            objOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            StageController.showError(ErrorMessage.PROFILE_WRITE_ERROR, Title.ERROR, false);
        }
    }

    /**
     * Checks if a profile of the same name already exists in the list of profiles
     *
     * @param profile Profile you want to check
     * @return If it exists or not
     */
    public static boolean exists(PlayerProfile profile) {
        refresh();
        return profiles.stream().anyMatch(p -> p.equals(profile));
    }

    /**
     * Gets the list of profiles
     *
     * @return List of profiles
     */
    public static ArrayList<PlayerProfile> getProfiles() {
        refresh();
        return profiles;
    }


}

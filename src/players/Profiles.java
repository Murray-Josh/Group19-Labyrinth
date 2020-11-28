package players;

import constants.ErrorMsg;
import constants.Title;
import controllers.StageController;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Profiles {
    private static ArrayList<PlayerProfile> profiles = new ArrayList<PlayerProfile>();
    private static final String PATH = "profiles.ser";

    /**
     * Calls save() and then load(), refreshing the available profiles
     */
    public static void refresh() {
        if (profiles.isEmpty()) {
            load();
            profiles.sort(PlayerProfile::compareTo);
        } else {
            save();
            load();
            profiles.sort(PlayerProfile::compareTo);
        }
    }

    /**
     * Saves all the profiles to profiles.ser
     */
    public static void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(PATH));
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(profiles);
            fileOut.close();
            objOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            StageController.showError(ErrorMsg.PROFILE_WRITE_ERROR, Title.ERROR, false);
        }
    }

    /**
     * Loads all profiles from profiles.ser
     */
    public static void load() {
    try {
        FileInputStream fileIn = new FileInputStream(new File(PATH));
        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        profiles = (ArrayList<PlayerProfile>) objIn.readObject();
    } catch (Exception e) {
        e.printStackTrace();
        StageController.showError(ErrorMsg.PROFILE_READ_ERROR, Title.ERROR, false );
    }
    }

    /**
     * Adds a profile to the list of profiles and then refreshes
     * @param profile Profile you want to add
     */
    public static void add(PlayerProfile profile) {
    profiles.add(profile);
    refresh();
    }

    /**
     * Removes a profile from the list of profiles then refreshes
     * @param profile Profile you want to remove
     */
    public static void remove(PlayerProfile profile) {
        profiles.remove(profile);
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
                StageController.showConfirmation("The profile " + newProfile.getName() + " has successfully been created.", "Profile Creation Complete", Title.CREATE_PROFILE.toString());
            } else {
                StageController.showError(ErrorMsg.PROFILE_ADD_ERROR, Title.CREATE_PROFILE, false);
                showCreate();
            }
        } else {
            dialog.close();
        }
    }

    /**
     * Shows and controls the Delete a Profile dialog
     */
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
            StageController.showConfirmation("The profile " + result.get().getName() + " has successfully been deleted.", "Delete Complete", Title.DELETE_PROFILE.toString());
        } else {
            dialog.close();
        }
    }

    /**
     * Checks if a profile of the same name already exists in the list of profiles
     * @param profile Profile you want to check
     * @return If it exists or not
     */
    public static boolean exists(PlayerProfile profile) {
        refresh();
        return profiles.stream().anyMatch(p -> p.equals(profile));
    }

    /**
     * Gets the list of profiles
     * @return List of profiles
     */
    public static ArrayList<PlayerProfile> getProfiles() {
        refresh();
        return profiles;
    }
}

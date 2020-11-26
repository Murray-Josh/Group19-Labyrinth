package players;

import core.ErrorMsg;
import core.Main;
import core.Title;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static fxml.StageController.*;

/**
 * Players.Profiles static class: Creates, stores, loads, saves and manages all the PlayerProfiles of the people that are playing/have played the game
 *
 * @author Martin Samm & Joseph Omar
 * @version 2.0.0
 */
public class Profiles {
    private static ArrayList<PlayerProfile> profiles;

    /**
     * Refreshes the list of profiles
     *
     * @throws IOException            If Players.profile does not exist in the application root directory or cannot be written to
     * @throws ClassNotFoundException If an object class that is in Players.profile is not in the application or is not serializable
     */
    public static void refresh() {
        try {
            saveProfiles();
        } catch (IOException e) {
            showError(ErrorMsg.SAVE_WRITE_ERROR, Title.ERROR, false);
        }
        try {
            loadProfiles();
        } catch (IOException e) {
            showError(ErrorMsg.PROFILE_FILE_NOT_FOUND, Title.ERROR, false);
        } catch (ClassNotFoundException e) {
            showError(ErrorMsg.PROFILE_READ_ERROR, Title.ERROR, false);
        }
    }

    /**
     * Loads the profiles from Players.profile
     *
     * @throws IOException            If Players.profile does not exist in the application root directory
     * @throws ClassNotFoundException If an object class that is in Players.profile is not in the application or is not serializable
     */
    public static void loadProfiles() throws IOException, ClassNotFoundException {
        File filename = new File("Players.profile");
        if (!filename.exists()) {
            throw new IOException("The file located at <" + filename + "> does not exist.");
        } else {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            profiles = (ArrayList<PlayerProfile>) objectInputStream.readObject();
        }
    }

    /**
     * Saves the profiles to Players.profile and creates the file if one does not already exist.
     *
     * @throws IOException If the file cannot be written
     */
    private static void saveProfiles() throws IOException {
        File file = new File("labyrinth.profiles");
        if (file.exists()) {
            file.renameTo(new File(file.toString() + ".old"));
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(profiles);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Add new player profile to list
     *
     * @param profile Players.PlayerProfile
     * @throws IOException            If Players.profile does not exist in the application root directory or cannot be written to
     * @throws ClassNotFoundException If an object class that is in Players.profile is not in the application or is not serializable
     */
    public static void addProfile(PlayerProfile profile) throws IOException, ClassNotFoundException {
        profiles.add(profile);
        if (!(profiles.isEmpty())) {
            profiles.sort(Comparator.comparing(PlayerProfile::getNumOfWins).reversed());
        }
        refresh();
    }

    /**
     * Remove player profile to list
     *
     * @param profile Players.PlayerProfile
     * @throws NullPointerException   If the supplied Players.PlayerProfile does not exist in Players.Profiles
     */
    public static void removeProfile(PlayerProfile profile) throws NullPointerException {
        if (exists(profile)) {
            profiles.remove(profile);
            refresh();
        } else {
            throw new NullPointerException("The specified profile does not exist");
        }
    }

    /**
     * Checks if a supplied profile exists in profiles
     *
     * @param profile The profile to be checked
     * @return If the profile exists in profiles
     */
    public static boolean exists(PlayerProfile profile) {
        return profiles.contains(profile);
    }

    /**
     * Finds a specific player profile in a list using an index
     *
     * @param index Index of the Profile
     * @return Players.PlayerProfile, null if Players.PlayerProfile not found
     * @throws IndexOutOfBoundsException When the supplied index does not refer to a Players.PlayerProfile
     */
    public static PlayerProfile getProfile(int index) throws IndexOutOfBoundsException {
        return profiles.get(index);
    }

    /**
     * Gets size of Profile array
     */
    public static int getArraySize() {
        return profiles.size();
    }

    /**
     * Gets all the saved profiles
     *
     * @return All saved profiles
     * @throws IOException            If the file cannot be written to or cannot be found
     * @throws ClassNotFoundException If the classes in the file are not in this application
     */
    public static ArrayList<PlayerProfile> get() throws IOException, ClassNotFoundException {
        refresh();
        return profiles;
    }

    /**
     * Creates and displays a Dialog box that allows the user to add a profile
     */
    public static void addProfileDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add a Profile");
        dialog.setContentText("Nickname: ");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.getEditor();
        dialog.getDialogPane().getStylesheets().add("./Misc/dialog.css");
        Optional<String> result = dialog.showAndWait();
        try {
            if (result.isPresent()) {
                PlayerProfile newProfile = new PlayerProfile(result.get());
                if (profileExists(newProfile)) {
                    showError(ErrorMsg.PROFILE_ADD_ERROR, Title.CREATE_PROFILE, false);
                    addProfileDialog();
                } else {
                    addProfile(newProfile);
                    showConfirmation("The profile '" + newProfile.getName() + "' has been created.", "Profile Creation Successful", "Create a Profile");
                }
            } else {
                home();
            }
        } catch (Exception e) {
            showError(ErrorMsg.PROFILE_WRITE_ERROR, Title.CREATE_PROFILE, false);
            home();
        }

    }

    /**
     * Creates and displays a dialog box that allows the user to delete a profile
     */
    public static void deleteProfileDialog() {
        refresh();
        ChoiceDialog<PlayerProfile> dialog = new ChoiceDialog<PlayerProfile>();
        dialog.setGraphic(null);
        dialog.setHeaderText(null);
        dialog.setContentText("Delete Profile: ");
        dialog.getItems().addAll(profiles);
        dialog.getDialogPane().getStylesheets().add("./Misc/dialog.css");
        Optional<PlayerProfile> result = dialog.showAndWait();
        if (result.isPresent()) {
            delete(result.get());
            showConfirmation("The profile '" + result.get().getName() + "' has been deleted.", "Delete Successful", "Delete Profile");
        } else {
            deleteProfileDialog();
        }
    }

    /**
     * Deletes the specified profile
     * @param profile
     */
    private static void delete(PlayerProfile profile) {
        profiles.remove(profile);
        refresh();
    }

    /**
     * Checks a profile against all profiles that exist.
     * @param profile Profile to check
     * @return If the profile already exists
     */
    public static boolean profileExists(PlayerProfile profile) throws IOException, ClassNotFoundException {
        refresh();
        boolean toReturn = false;
        for (PlayerProfile p : profiles) {
            if (p.equals(profile)) { toReturn = true; }
        }
        return toReturn;
    }

}

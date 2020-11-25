package fxml;

import core.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import players.PlayerProfile;
import players.Profiles;

public class AddProfileDialogController {
    @FXML
    private Button cmdAdd;
    @FXML
    private Button cmdCancel;
    @FXML
    private TextField txtName;

    public void cmdAddClick(ActionEvent actionEvent) {
        PlayerProfile profile = new PlayerProfile(txtName.getText());
        if (Profiles.exists(profile)) {
            Main.changeScene("ProfileError.fxml", "Create a Profile");
        } else {
            try {
                Profiles.addProfile(profile);
            } catch (Exception e ) {
                Main.showError("The profile could not be added. Please verify integrity of game files and path of player.profiles", "Could Not Save Profile", "Create a Profile", false);
            }
        }
    }

    public void cmdCancelClick(ActionEvent actionEvent) {
        Main.changeScene("MainMenu.fxml", "Labyrinth");
    }
}

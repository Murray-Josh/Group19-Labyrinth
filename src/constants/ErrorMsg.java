package constants;

/**
 * Enum defining all the error messages and headers that will be displayed through GUI
 *
 * @author Joseph Omar
 * @version 1.9
 */
public enum ErrorMsg {
    FXML_NOT_FOUND("The FXML file for this window cannot be found.\nPlease verify game files and directories.\n",
                   "FXML Not Found"),
    MESSGAGE_FAIL("The Message of the Day could not be fetched", "Message of the Day Error"),
    PROFILE_ADD_ERROR("That nickname is already taken, please choose a new one\n", "Cannot Create Profile"),
    PROFILE_FILE_NOT_FOUND("The profiles save file <profiles.ser> could not be found.\nPlease verify that the file is" +
                                   " in the saves folder.\n", "Profiles Not Found"),
    PROFILE_LOAD_ERROR("The profiles save file <profiles.ser> could not be parsed\n", "Profile Parse Error"),
    PROFILE_NOT_SELECTED("You need to select at least two profiles", "Not Enough Profiles Selected"),
    PROFILE_NOT_VALID("You must select a profile", "Selected Profile Not Valid"),
    PROFILE_READ_ERROR("The profiles save file <profiles.ser> could not be read.\nPlease verify all game files.\n",
                       "Profiles Read Error"),
    PROFILE_WRITE_ERROR("The profiles save file <profiles.ser> could not be written.\nPlease verify all game files " +
                                "and directories.\n", "Profiles Write Error"),
    SAVE_FILE_NOT_FOUND("The savegame selected could not be found\n", "Savegame File Path Error"),
    SAVE_LOAD_ERROR("The savegame selected could not be loaded\n", "Savegame Load Error"),
    SAVE_NOT_SELECTED("You have not selected a save file to load\n", "No Savegame Selected"),
    SAVE_READ_ERROR("The savegame selected could not be parsed\n", "Savegame Parse Error"),
    SAVE_WRITE_ERROR("The game could not be written to a file\n", "Savegame Write Error"),
    STYLE_NOT_VALID("The selected Style is unavailable", "Style Unavailable");

    private final String MESSAGE;
    private final String HEADER;

    /**
     * Errormsg Constructor
     *
     * @param message Message to be displayed
     * @param header  Header to be displayed
     */
    ErrorMsg(String message, String header) {
        this.MESSAGE = message;
        this.HEADER = header;
    }

    /**
     * Gets message
     *
     * @return message
     */
    public String getMessage() {
        return this.MESSAGE;
    }

    /**
     * Gets Header Text
     *
     * @return header
     */
    public String getHeader() {
        return this.HEADER;
    }
}

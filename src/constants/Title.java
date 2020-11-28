package constants;

/**
 * Enum defining window titles that get displayed via GUI
 *
 * @author Joseph Omar
 * @version 1.0
 */
public enum Title {
    /**
     * Main Menu
     */
    MAIN("Labyrinth"),
    /**
     * Load Game
     */
    LOAD("Load a Saved Game"),
    /**
     * Save Game
     */
    SAVE("Save Game"),
    /**
     * Setup Game
     */
    SETUP("Set Up a New Game"),
    /**
     * Scoreboard
     */
    SCOREBOARD("Scoreboard"),
    /**
     * Create Profile
     */
    CREATE_PROFILE("Create a Profile"),
    /**
     * Delete profile
     */
    DELETE_PROFILE("Delete a Profile"),
    /**
     * Error
     */
    ERROR("Labyrinth encountered an error"),
    /**
     * Critical Error (Only when the program exits after
     */
    CRIT_ERROR("Labyrinth encountered a critical error");

    private final String TITLE;

    /**
     * Constructor
     *
     * @param title Title of the Window
     */
    Title(String title) {
        this.TITLE = title;
    }

    /**
     * Converts TITLE type to string
     *
     * @return
     */
    @Override
    public String toString() {
        return this.TITLE;
    }
}

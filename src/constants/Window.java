package constants;

/**
 * Enum defining the fxml paths and titles for GUI scenes
 *
 * @author Joseph Omar
 * @version 1.0
 */
public enum Window {
    HOME("/fxml/MainMenu.fxml", Title.MAIN),
    LOAD("/fxml/LoadGame.fxml", Title.LOAD),
    SCOREBOARD("/fxml/Scoreboard.fxml", Title.SCOREBOARD),
    SETUP("SetUpGame.fxml", Title.SETUP);


    private final String PATH;
    private final Title TITLE;

    /**
     * Constructor for a window
     *
     * @param path  Path of the window's fxml file
     * @param title Title of the window
     */
    Window(String path, Title title) {
        this.TITLE = title;
        this.PATH = path;
    }

    /**
     * Gets the relative path of the window's fxml file
     *
     * @return fxml file path
     */
    public String getPath() {
        return this.PATH;
    }

    /**
     * Gets the Title of the window
     *
     * @return Title of the window
     */
    public String getTitle() {
        return this.TITLE.toString();
    }

}

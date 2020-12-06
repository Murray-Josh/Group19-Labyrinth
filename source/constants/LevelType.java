package constants;

import java.io.Serializable;

/**
 * Defines a Level
 *
 * @author Martin Samm
 * @author Joseph Omar
 * @version 1.0
 */
public enum LevelType implements Serializable {
    BIG("source/resources/file/Big", "Big"),
    MEDIUM("source/resources/file/Medium", "Medium"),
    SMALL("source/resources/file/Small", "Small"),
    WIN("source/resources/file/--{WIN}--", "win_demo");
    final String PATH;
    final String NAME;

    LevelType(String path, String name) {
        this.PATH = path;
        this.NAME = name;
    }

    /**
     * Gets the path of the level file
     *
     * @return File Path
     */
    public String getPath() {
        return PATH;
    }

    /**
     * Gets the name of the level file
     *
     * @return Name of level
     */
    public String getName() {
        return NAME;
    }


    /**
     * Converts level to String
     *
     * @return level as String
     */
    @Override
    public String toString() {
        return NAME;
    }
}

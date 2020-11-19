/**
 * PlayerProfile.java
 *
 * @version 1.0.0
 * @author Martin Samm
 */


import java.io.Serializable;

/**
 * stores wins, losses, number of games played
 * and name of each individual player
 */
public class PlayerProfile implements Serializable {
    private int numWins;
    private int numLosses;
    private int numGames;
    private String name;

    /**
     * Creates a new player profile
     *
     * @param name Name of player
     */
    public PlayerProfile(String name) {
        this.name = name;
    }

    /**
     * @return current amount of wins
     */
    public int getNumOfWins() {
        return numWins;
    }

    /**
     * Assign new number of wins
     *
     * @param num int
     */
    public void setNumOfWins(int num) {
        numWins = num;
    }

    /**
     * @return current amount of losses
     */
    public int getNumOfLosses() {
        return numLosses;
    }

    /**
     * Assign new number of losses
     *
     * @param num int
     */
    public void setNumOfLosses(int num) {
        numLosses = num;
    }

    /**
     * @return current number of games
     */
    public int getNumOfGames() {
        return numGames;
    }

    /**
     * Assign new number of games
     *
     * @param num int
     */
    public void setNumOfGames(int num) {
        numGames = num;
    }

    /**
     * @return name of player profile
     */
    public String getName() {
        return name;
    }

    /**
     * Assign new name to player profile
     *
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Convert player profile to readable String
     */
    public String toString() {
        String s = "Name: " + getName();
        s += " | Wins: " + getNumOfWins();
        s += " | Losses: " + getNumOfLosses();
        s += " | Games: " + getNumOfGames();
        return s;
    }
}

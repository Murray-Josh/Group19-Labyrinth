package players;
/*
 * Players.PlayerProfile.java
 */


import java.io.Serializable;

/**
 * stores wins, losses, number of games played
 * and name of each individual player
 * * @version 2.0.0
 *
 * @author Martin Samm
 */
public class PlayerProfile implements Serializable, Comparable<PlayerProfile> {
    private static final int PERCENTAGE_MULTIPLIER = 100;
    private int numWins;
    private int numLosses;
    private int numGames;
    private String name;
    private double winPercentage;

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
     * @return name of the player profile
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
     * Return's the Players.Player Profile's win percentage
     *
     * @return Win Percentage
     */
    public double getWinPercentage() {
        calculateWinPercentage();
        return this.winPercentage;
    }

    /**
     * Convert player profile to readable String
     */
    public String toString() {
        String s = "Name: " + getName();
        s += " | Wins: " + getNumOfWins();
        s += " | Losses: " + getNumOfLosses();
        s += " | Games: " + getNumOfGames();
        s += " | Win Percentage: " + getWinPercentage() + "%";
        return s;
    }

    /**
     * Calculates the win percentage based on the number of wins and the number of games
     */
    public void calculateWinPercentage() {
        if (getNumOfWins() > 0 || getNumOfGames() > 0) {
            this.winPercentage = (this.getNumOfWins() / this.getNumOfGames()) * PERCENTAGE_MULTIPLIER;
        } else {
            this.winPercentage = 0;
        }
    }

    /**
     * Compares this profile to another profile
     *
     * @param comparisonProfile Profile being compared to
     * @return 0 if equal, >0 if better, <0 if worse.
     */
    @Override
    public int compareTo(PlayerProfile comparisonProfile) {
        return this.getNumOfWins() - comparisonProfile.getNumOfWins();
    }

    /**
     * If this profile matches another profile
     * @param p Profile to be checked against
     * @return If the names match or not
     */
    public boolean equals(PlayerProfile p) {
       return this.getName().equals(p.getName());
    }
}

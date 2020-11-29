package players;
/*
 * Players.PlayerProfile.java
 */


import java.io.Serializable;

/**
 * stores wins, losses, number of games played and name of each individual player * @version 2.0.0
 *
 * @author Martin Samm
 */
public class PlayerProfile implements Serializable, Comparable<PlayerProfile> {
    private static final int    PERCENTAGE_MULTIPLIER = 100;
    private              int    wins;
    private              int    losses;
    private              int    games;
    private              String name;

    /**
     * Creates a new player profile
     *
     * @param name Name of player
     */
    public PlayerProfile(String name) {
        this.name = name;
        this.games = 0;
        this.losses = 0;
        this.wins = 0;
    }

    /**
     * Creates a new player profile with the specified statistics
     *
     * @param name   Name of the profile
     * @param wins   Number of wins
     * @param losses Number of Losses
     * @param games  Number of Games
     */
    public PlayerProfile(String name, int wins, int losses, int games) {
        this.name = name;
        this.games = games;
        this.losses = losses;
        this.wins = wins;
    }

    /**
     * @return current amount of losses
     */
    public int getNumOfLosses() {
        return losses;
    }

    /**
     * Assign new number of losses
     *
     * @param num int
     */
    public void setNumOfLosses(int num) {
        losses = num;
    }

    /**
     * @return current number of games
     */
    public int getNumOfGames() {
        return games;
    }

    /**
     * Assign new number of games
     *
     * @param num int
     */
    public void setNumOfGames(int num) {
        games = num;
    }

    /**
     * Return's the Players.Player Profile's win percentage
     *
     * @return Win Percentage
     */
    public float getWinPercentage() {
        if (wins != 0 && games != 0) {
            return (wins * PERCENTAGE_MULTIPLIER / games);
        } else {
            return 0;
        }
    }

    /**
     * Convert player profile to readable String
     */
    public String toString() {
        return this.name;
    }

    /**
     * Compares this profile to another profile
     *
     * @param comparisonProfile Profile being compared to
     *
     * @return 0 if equal, >0 if better, <0 if worse.
     */
    @Override
    public int compareTo(PlayerProfile comparisonProfile) {
        return this.getNumOfWins() - comparisonProfile.getNumOfWins();
    }

    /**
     * @return current amount of wins
     */
    public int getNumOfWins() {
        return wins;
    }

    /**
     * Assign new number of wins
     *
     * @param num int
     */
    public void setNumOfWins(int num) {
        wins = num;
    }

    /**
     * If this profile matches another profile
     *
     * @param p Profile to be checked against
     *
     * @return If the names match or not
     */
    public boolean equals(PlayerProfile p) {
        return this.getName().equals(p.getName());
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
}

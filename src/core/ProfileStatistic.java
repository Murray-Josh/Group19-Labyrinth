package core;

import java.io.Serializable;

/**
 * Stores the player's wins, losses and number of games
 *
 * @author Martin Samm
 * @author Joseph Omar
 * @version 1.1
 */
public final class ProfileStatistic implements Serializable {

    private int wins;
    private int losses;
    private int games;

    /**
     * Constructs a new ProfileStatistic.
     *
     * @param wins   Number of wins
     * @param losses Number of losses
     * @param games  Number of games
     */
    public ProfileStatistic(int wins, int losses, int games) {
        this.wins = wins;
        this.losses = losses;
        this.games = games;
    }

    /**
     * Gets the Number of wins
     *
     * @return Number of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Sets the Number of wins.
     *
     * @param wins Number of wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * Gets the Number of losses
     *
     * @return Number of losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Sets the Number of losses
     *
     * @param losses Number of losses
     */
    public void setLosses(int losses) {
        this.losses = losses;
    }

    /**
     * Gets the Number of games
     *
     * @return Number of games
     */
    public int getGames() {
        return games;
    }

    /**
     * Sets the Number of Games
     *
     * @param games Number of Games
     */
    public void setGames(int games) {
        this.games = games;
    }

}
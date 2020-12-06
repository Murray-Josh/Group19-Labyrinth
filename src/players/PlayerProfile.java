package players;

import constants.LevelType;
import core.ProfileStatistic;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores wins, losses, number of games played and name of each individual player * @version 2.0.0
 *
 * @author Martin Samm
 * @author Joseph Omar
 * @version 2.0
 */
@SuppressWarnings("unused")
public class PlayerProfile implements Serializable {

    private static final int PERCENTAGE_MULTIPLIER = 100;
    private String name;
    private HashMap<LevelType, ProfileStatistic> statistics;

    /**
     * Creates a new player profile
     *
     * @param name Name of player
     */
    public PlayerProfile(String name) {
        this.name = name;
        this.statistics = new HashMap<>();
        for (LevelType levelType : LevelType.values()) {
            statistics.put(levelType, new ProfileStatistic(0, 0, 0));
        }
    }

    /**
     * Convert player profile to readable String
     */
    public String toString() {
        return this.name;
    }


    /**
     * If this profile matches another profile
     *
     * @param p Profile to be checked against
     * @return If the names match or not
     */
    public boolean equals(PlayerProfile p) {
        return this.getName().equals(p.getName());
    }

    /**
     * Gets name of profile
     *
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

    public void editWins(LevelType levelType, int wins) {
        statistics.get(levelType).setWins(wins);
    }

    public void editGames(LevelType levelType, int games) {
        statistics.get(levelType).setGames(games);
    }

    public void editLosses(LevelType levelType, int losses) {
        statistics.get(levelType).setLosses(losses);
    }

    public int getWins(LevelType levelType) {
        return statistics.get(levelType).getWins();
    }

    public int getLosses(LevelType levelType) {
        return statistics.get(levelType).getLosses();
    }

    public int getGames(LevelType levelType) {
        return statistics.get(levelType).getGames();
    }

    public HashMap<LevelType, ProfileStatistic> getStatistics() {
        return statistics;
    }

    public ProfileStatistic getStatistic(LevelType levelType) {
        return statistics.get(levelType);
    }


    public void addLevelStatistic(LevelType level, int wins, int losses, int games) {
        statistics.put(level, new ProfileStatistic(wins, losses, games));
    }

    public float getWinPercentage(LevelType levelType) {
        int games = statistics.get(levelType).getGames();
        int wins = statistics.get(levelType).getWins();
        if (wins != 0 && games != 0) {
            return (float) (wins * PERCENTAGE_MULTIPLIER / games);
        } else {
            return 0;
        }
    }
}

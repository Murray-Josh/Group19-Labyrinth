package core;

import java.io.Serializable;

public final class ProfileStatistic implements Serializable {
    private int wins;
    private int losses;
    private int games;

    public ProfileStatistic(int wins, int losses, int games) {
        this.wins=wins;
        this.losses=losses;
        this.games=games;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

}
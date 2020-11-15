import java.util.ArrayList;

public class Player {
    private PlayerProfile profile;
    private Coordinate coord;
    private ArrayList<TileEffect> hand;
    private Style style;
    private int playerNum;
    private int currentDirection;
    private PlayerEffect activeEffect;

    public Player(PlayerProfile profile, Coordinate coord, Style style, int playerNum) {
        setProfile(profile);
        setCoord(coord);
        setStyle(style);
        setPlayerNum(playerNum);
        setCurrentDirection(0);

    }

    private void setProfile(PlayerProfile profile) {
        this.profile = profile;
    }

    public PlayerProfile getProfile() {
        return profile;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public Coordinate getCoord() {
        return coord;
    }

    private void setHand(ArrayList<TileEffect> hand) {
        this.hand = hand;
    }

    public void addToHand(TileEffect effect) {
        hand.add(effect);
    }

    public ArrayList<TileEffect> getHand() {
        return hand;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    private void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public void setActiveEffect(PlayerEffect activeEffect) {
        this.activeEffect = activeEffect;
    }

    public PlayerEffect getActiveEffect() {
        return activeEffect;
    }
}

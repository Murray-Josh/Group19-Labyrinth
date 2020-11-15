import java.util.ArrayList;

public class Player {
    private PlayerProfile profile;
    private Coordinate coordinate;
    private ArrayList<Effect> hand;
    private Style style;
    private int playerNum;
    private int currentDirection;
    private PlayerEffect activeEffect;

    public Player(PlayerProfile profile, Coordinate coord, Style style, int playerNum) {
        setProfile(profile);
        setCoordinate(coord);
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

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    private void setHand(ArrayList<Effect> hand) {
        this.hand = hand;
    }

    public void addToHand(Effect effect) {
        hand.add(effect);
    }

    public ArrayList<Effect> getHand() {
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

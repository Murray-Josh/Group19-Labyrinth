import java.util.ArrayList;

public abstract class TileType {

    private Boolean NORTH;
    private Boolean SOUTH;
    private Boolean EAST;
    private Boolean WEST;
    protected Boolean DirectionIsFalse;
    protected Boolean DirectionIsTrue;

    public TileType(){
        this.NORTH = NORTH;
        this.EAST = EAST;
        this.SOUTH = SOUTH;
        this.WEST = WEST;
        this.DirectionIsFalse = Boolean.FALSE;
        this.DirectionIsTrue = Boolean.TRUE;
    }

    public ArrayList<Boolean> getAvailableDirections(){
        ArrayList<Boolean> availableDirections = new ArrayList<>();

        if(getNORTH()){
            availableDirections.add(getNORTH());
        }
        if(getEAST()){
            availableDirections.add(getEAST());
        }
        if(getSOUTH()){
            availableDirections.add(getSOUTH());
        }
        if(getWEST()){
            availableDirections.add(getWEST());
        }

        return availableDirections;

    }

    public Image getImage(){

    }

    public Boolean getDirectionIsFalse() {
        return this.DirectionIsFalse;
    }

    public Boolean getDirectionIsTrue() {
        return this.DirectionIsTrue;
    }

    public Boolean getNORTH() {
        return this.NORTH;
    }

    public void setNORTH(Boolean NORTH) {
        this.NORTH = NORTH;
    }

    public Boolean getSOUTH() {
        return this.SOUTH;
    }

    public void setSOUTH(Boolean SOUTH) {
        this.SOUTH = SOUTH;
    }

    public Boolean getEAST() {
        return this.EAST;
    }

    public void setEAST(Boolean EAST) {
        this.EAST = EAST;
    }

    public Boolean getWEST() {
        return this.WEST;
    }

    public void setWEST(Boolean WEST) {
        this.WEST = WEST;
    }

}

package com.Tiles;

public class GoalTile extends TileType{

    public GoalTile(){
        setNORTH(getDirectionIsTrue());
        setEAST(getDirectionIsTrue());
        setSOUTH(getDirectionIsTrue());
        setWEST(getDirectionIsTrue());

    }

}

package com.Tiles;

public class StraightTile extends TileType{

    public StraightTile(){
        setNORTH(getDirectionIsFalse());
        setEAST(getDirectionIsFalse());
        setSOUTH(getDirectionIsTrue());
        setWEST(getDirectionIsTrue());

    }


}

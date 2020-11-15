package com.Tiles;

public class CornerTile extends TileType{

    public CornerTile(){
        setNORTH(getDirectionIsTrue());
        setEAST(getDirectionIsFalse());
        setSOUTH(getDirectionIsTrue());
        setWEST(getDirectionIsFalse());
    }
}

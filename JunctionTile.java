package com.Tiles;

public class JunctionTile extends TileType{

    public JunctionTile(){
        setNORTH(getDirectionIsTrue());
        setEAST(getDirectionIsTrue());
        setSOUTH(getDirectionIsFalse());
        setWEST(getDirectionIsTrue());

    }


}

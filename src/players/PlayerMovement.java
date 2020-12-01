package players;

import core.Coordinate;
import core.Gameboard;
import holdables.TileEffect;
import javafx.scene.input.KeyCode;
import players.Player;
import javafx.scene.control.Button;
import styles.Style;
import holdables.Tile;
import javafx.scene.input.KeyEvent;
import java.util.Collections;

/**
 * Moves the players
 *
 * @author FungWah Westley & Isabelle Ludwig
 * @version 1.0
 */

public class PlayerMovement {

    private Gameboard gameboard;

//TODO key listener method

    private void keyPressed(KeyEvent e){
        KeyCode key = e.getCode();

// if statement to find corresponding keys - up and w etc and an else at the end which ignores all other input?
        /*  more key codes at end of page if we need them :)
            224 -- Up
            225 -- Down
            226 -- Left
            227 -- Right

            87 -- W
            65 -- A
            83 -- S
            68 -- D
         */

    }


// TODO private method double move and backspace - pop coordinate stack


    private static void backMovement(){

    }

    private static void doubleMove(){

    }

//TODO boolean method check if you can move there ie tiles are aligned/not on fire/different player not on square etc

    private static Boolean canPlayerEnterTile(Tile nextTile){
     if (TileEffect.FIRE || tilesAligned() == false || isPlayerOnTile()){
            return false;
        } else {
         return true;
        }
    }

    private static Boolean isPlayerOnTile(Player currentPlayer){
        //we need to know how many players are in the game
        if(currentPlayer.getCoordinate().equals() || currentPlayer.getCoordinate().equals()){}

    }

    private static Boolean tilesAligned(){
        //checks if the tiles align - is there an easier way of doing this than doing shit tonnes of if statements
    }

//TODO boolean method that calls above - checks in all four directions - ends turn if not possible to move

    private static Boolean isAccessable(){
        if(canPlayerEnterTile()){
            //how would you check the four directions??
        }
    }

//TODO move counter method 1-4 moves - old coord != nodprivate static static

    private static void moveCount(){
        int count = 0;
        while(isAccessable() && count <= 4 && canPlayerEnterTile()){
            //move
            count++;
        }
    }


//TODO end turn (dw about this just yet tho)



    private Coordinate oldCoord(Player currentPlayer, int player){
        //can peek stack Player.coordinateHistory
        return currentPlayer.getCoordinate();
    }

    private static void newCoord(){
        //can push new coords onto stack Player.coordinateHistory
    }


/*
 If you make a method that takes in a keyEvent type from javafx.scene.input.KeyEvent;
 and decides what to do with it that'd be great. Or make an algorithm that gets
 the tiles Matrix from Gameboard and the direction of the keypress
 and returns how many places they can move in that direction?
 you should have a Gameboard attribute in PlayerMovement
 have a look at the Matrix class to figure out how to use it
 its in core


 player, the arraylist that contains the coordinates of the player will become a Stack (java.util.Collections)
 so when you want to add the current coord use push, when you want
 the current coord use peek and when you want the position n turns ago use elementAt(n-1) i think
 have a look at the api for it
*/


}






/*
    private int dx;
    private int dy;
    private int x;
    private int y;

//TODO all methods should be static
    //non static attribute - "private gameboard" and the initialise
    //do we just want the key codes or do we want the gameboard controller to just call a method here which makes it easier for us...


//TODO load in image??

    private void loadPlayer(int num) {
        Style.getPlayerImage(num);
    }






//could just if player presses up, check if that tile is accessible or not instead of checking all possible options - might be a lot faster

// we can only use this if using awt things is allowed which i dont know if it is...

// this is happening in gameboard controller (key listener) - need to accsess that instead of creating it ourselves :)

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT && tileAccessable() == true){
            dx += -1;
        }

        if (key == KeyEvent.VK_RIGHT && tileAccessable() == true) {
            dx += 1;
        }

        if (key == KeyEvent.VK_UP && tileAccessable() == true) {
            dy += -1;
        }

        if (key == KeyEvent.VK_DOWN && tileAccessable() == true) {
            dy += 1;
        }
    }


    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private boolean tileAccessable(){
        boolean tilesAlign = true;
        if (tilesAlign && Tile.isOnFire() == false){
            return true;
        } else {
            return false;
        }
    }

    //   Tile nextMoveTile = null;


    private Gameboard gameboard;

    //TODO gets current player position

    Tile currentTile = gameboard.getTile(Player.getCoordinate().getX(),Player.getCoordinate().getY());
    //        (1,2)
    //  (0,1) (1,1) (2,1)
    //        (1,0)


    //if(currentTile.)



//TODO check if tile is on fire

    if(nextMoveTile.isOnFire()){
        //block user from e}else if (nextMoveTile.rotaion == 1 || ...){
    }


    //currentPlayerPosition


    //not really sure where this came from but shes here now
    public Coordinate getCoordinate(int num) {
        return Player.coordinateArray.get(num);
    }



//TODO loop that checks who many tiles the player can still move out of 4

    Int count = 0;
    Boolean hasUserClickedTile = false
    while (hasUserClickedTile == false || count < 4) {}



//TODO check if surrounding tiles are accessible from current tile - there is a method that does this in tileType

    if(
    void nextMoveTile(){
        //block user from entering
    }else{

    } if(nextMoveTile.rotation()) {

    }

            //    public Coordinate getCoordinate(int i) { return coordinateArray.get(i);  }
            //    Boolean hasUserClickedTile = false;




//TODO take input from player - arrow keys or click on tile
//TODO check for double move? - does that happen in this class or another??????????
//TODO check for backwards movements for selected player - again does that happen in this class or another??????????
//TODO check if tile player wants to move to isn´t being occupied by another player








    public void Move(Player currentPlayer, int i){
        Tile currentTile;
        currentTile.setCoordinate(currentPlayer.getCoordinate(i));

        Tile tempMoveTile = null;
        //gamerBoardController will have buttons I guess? using these for now
        Button up = null;
        Button down = null;
        Button left = null;
        Button right = null;

        if(currentTile.getAngle() == ){
            //if the current tile has multiple directions, eg. player can go north and west
            //disable up and left arrows until accessible


        }

        int count = 0;
        Boolean hasUserFinishedMoving = false;

        while (hasUserFinishedMoving || count <= 4) {
            // if any of the arrow has been pressed then
            //update tempMoveTile to the location of the next tile
            if(!(updateTempTile(currentTile,up,down,left,right).isOnFire()){
                currentPlayer.= updateTempTile(currentTile,up,down,left,right);
                count++;
            } else {
                //block user from pressing an arrow to that tile.
            }
            //not sure how the user will tell the game the moves chosen has finished but
            //maybe button click done? then hasUserFinsihedMoving = true;

        }

        //update the final location of the player
        currentTile = tempMoveTile;

    }


    private Tile updateTempTile(Tile currentTile, Button up, Button down, Button left, Button right){
        Coordinate upCood, downCood, leftCood, righCood;
        //if up is pressed then
        upCood = new Coordinate(currentTile.getCoordinate().getX(),currentTile.getCoordinate().getY()+1);
        currentTile.setCoordinate(upCood);
        //if down is pressed then
        downCood = new Coordinate(currentTile.getCoordinate().getX(),currentTile.getCoordinate().getY()-1);
        currentTile.setCoordinate(downCood);
        //if left is pressed then
        leftCood = new Coordinate(currentTile.getCoordinate().getX()-1,currentTile.getCoordinate().getY());
        currentTile.setCoordinate(leftCood);
        //if right is pressed then
        righCood = new Coordinate(currentTile.getCoordinate().getX()-1,currentTile.getCoordinate().getY());
        currentTile.setCoordinate(leftCood);

        return currentTile;

    }
}
*/





/* these are all the key codes if we need them :)
3 -- Cancel
8 -- Backspace
9 -- Tab
10 -- Enter
12 -- Clear
16 -- Shift
17 -- Ctrl
18 -- Alt
19 -- Pause
20 -- Caps Lock
21 -- Kana
24 -- Final
25 -- Kanji
27 -- Escape
28 -- Convert
29 -- No Convert
30 -- Accept
31 -- Mode Change
32 -- Space
33 -- Page Up
34 -- Page Down
35 -- End
36 -- Home
37 -- Left
38 -- Up
39 -- Right
40 -- Down
44 -- Comma
45 -- Minus
46 -- Period
47 -- Slash
48 -- 0
49 -- 1
50 -- 2
51 -- 3
52 -- 4
53 -- 5
54 -- 6
55 -- 7
56 -- 8
57 -- 9
59 -- Semicolon
61 -- Equals
65 -- A
66 -- B
67 -- C
68 -- D
69 -- E
70 -- F
71 -- G
72 -- H
73 -- I
74 -- J
75 -- K
76 -- L
77 -- M
78 -- N
79 -- O
80 -- P
81 -- Q
82 -- R
83 -- S
84 -- T
85 -- U
86 -- V
87 -- W
88 -- X
89 -- Y
90 -- Z
91 -- Open Bracket
92 -- Back Slash
93 -- Close Bracket
96 -- NumPad-0
97 -- NumPad-1
98 -- NumPad-2
99 -- NumPad-3
100 -- NumPad-4
101 -- NumPad-5
102 -- NumPad-6
103 -- NumPad-7
104 -- NumPad-8
105 -- NumPad-9
106 -- NumPad *
107 -- NumPad +
108 -- NumPad ,
109 -- NumPad -
110 -- NumPad .
111 -- NumPad /
112 -- F1
113 -- F2
114 -- F3
115 -- F4
116 -- F5
117 -- F6
118 -- F7
119 -- F8
120 -- F9
121 -- F10
122 -- F11
123 -- F12
127 -- Delete
128 -- Dead Grave
129 -- Dead Acute
130 -- Dead Circumflex
131 -- Dead Tilde
132 -- Dead Macron
133 -- Dead Breve
134 -- Dead Above Dot
135 -- Dead Diaeresis
136 -- Dead Above Ring
137 -- Dead Double Acute
138 -- Dead Caron
139 -- Dead Cedilla
140 -- Dead Ogonek
141 -- Dead Iota
142 -- Dead Voiced Sound
143 -- Dead Semivoiced Sound
144 -- Num Lock
145 -- Scroll Lock
150 -- Ampersand
151 -- Asterisk
152 -- Double Quote
153 -- Less
154 -- Print Screen
155 -- Insert
156 -- Help
157 -- Meta
160 -- Greater
161 -- Left Brace
162 -- Right Brace
192 -- Back Quote
222 -- Quote
224 -- Up
225 -- Down
226 -- Left
227 -- Right
240 -- Alphanumeric
241 -- Katakana
242 -- Hiragana
243 -- Full-Width
244 -- Half-Width
245 -- Roman Characters
256 -- All Candidates
257 -- Previous Candidate
258 -- Code Input
259 -- Japanese Katakana
260 -- Japanese Hiragana
261 -- Japanese Roman
262 -- Kana Lock
263 -- Input Method On/Off
512 -- At
513 -- Colon
514 -- Circumflex
515 -- Dollar
516 -- Euro
517 -- Exclamation Mark
518 -- Inverted Exclamation Mark
519 -- Left Parenthesis
520 -- Number Sign
521 -- Plus
522 -- Right Parenthesis
523 -- Underscore
524 -- Windows
525 -- Context Menu
61440 -- F13
61441 -- F14
61442 -- F15
61443 -- F16
61444 -- F17
61445 -- F18
61446 -- F19
61447 -- F20
61448 -- F21
61449 -- F22
61450 -- F23
61451 -- F24
65312 -- Compose
65368 -- Begin
65406 -- Alt Graph
65480 -- Stop
65481 -- Again
65482 -- Props
65483 -- Undo
65485 -- Copy
65487 -- Paste
65488 -- Find
65489 -- Cut
 */
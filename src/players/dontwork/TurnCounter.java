/**
 * TurnCounter.java
 *
 * @author Walid Mohamed
 * @version 1.0.0
 */
public class TurnCounter {

    private boolean playerOnePlaysNext;
    private boolean playerTwoPlaysNext;
    private boolean playerThreePlaysNext;
    private boolean playerFourPlaysNext;

    protected P[] players;
    //playerOnePlaysNext = true;

    public void switchPlayer() {

        boolean playerRotation;
        if (!playerOnePlaysNext) playerOnePlaysNext = true;
        else playerOnePlaysNext = false;
        if (playerTwoPlaysNext) {
            playerTwoPlaysNext = false;
            playerThreePlaysNext = !playerThreePlaysNext;
            playerFourPlaysNext = !playerFourPlaysNext;
        } else {
            playerTwoPlaysNext = true;
            playerThreePlaysNext = !playerThreePlaysNext;
            playerFourPlaysNext = !playerFourPlaysNext;
        }
    }

    protected TurnCounter(P... players) {
        this.players = players;
        reset();

//Player 1:
        if (playerOnePlaysNext) {

            //player 1 acts.
            switchPlayer();
            System.out.println("It is Player One's turn");
        }
        //Player 2:
        if (!playerOnePlaysNext) {

            //player 2 acts.
            switchPlayer();
            System.out.println("It is Player Two's turn");
        }
        //Player 3:
        if (!playerTwoPlaysNext) {

            //player 3 acts.
            switchPlayer();
            System.out.println("It is Player Three's turn");
        }
        //Player 4:
        if (!playerThreePlaysNext) {

            //player 4 acts.
            switchPlayer();
            System.out.println("It is Player Four's turn");
        }
    }

    private void reset() {

    }
}

//should include skipping turns if player is unable to move due to an effect but my brain stopped working tonight lol

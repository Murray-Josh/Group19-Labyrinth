public class TurnCounter {

    private boolean playerOnePlaysNext;
    private P[] players;
    //playerOnePlaysNext = true;

    public void switchPlayer() {

        boolean onePlaysNext;
        if (!playerOnePlaysNext) playerOnePlaysNext = true;
        else playerOnePlaysNext = false;
    }

    public TurnCounter(P... players) {
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
    }

    private void reset() {
    }
}

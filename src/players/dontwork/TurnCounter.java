public class TurnCounter {

    private boolean playerOnePlaysNext;
    protected P[] players;
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
   /*     if (currentPlayer == playerOne)
        {
            currentPlayer = playerTwo;
        }
        else if (currentPlayer == playerTwo)
        {
            currentPlayer = playerOne;
        }
    }

    private void reset() {
    }
}
*/
        
    /* initialize
    List<Player> players = Arrays.asList(new Player(...),new Player(...));
        Player currentPlayer = players.get(0);


        // switch Player:
        currentPlayer = players.get(1-players.indexOf(currentPlayer));

        // version for more than two players:
        // select the next player from list and restart with the first after the last:
        currentPlayer = players.get((players.indexOf(currentPlayer)+1)%players.size());

        // similar by cycling the list
        currentPlayer = players.remove(0); // get the next player from list
        players.add(currentPlayer); // put it at the end of the list */

// how to implement a player losing their turn due to freeze or inability to move due to tile shifts.

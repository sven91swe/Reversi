package Game;

import Board.Move;
import Board.ReversiBoard;
import GameBot.GameBot;

/**
 * Created by Joel Magnusson on 2016-04-03.
 */
public class GameOld {
    private ReversiBoard board = new ReversiBoard();
    private GameBot[] bots = new GameBot[2];
    private Move latestMove = new Move(false);
    private int turn = 0;
    private boolean isFinished = false;
    private int winner;

    public GameOld(GameBot bot){ this(bot,bot); }

    public GameOld(GameBot bot1, GameBot bot2){
        this.bots[0] = bot1;
        this.bots[1] = bot2;
    }

    public GameBot[] getBots() {return this.bots;}

    public Move getLatestMove() {return this.latestMove;}
    private void setLatestMove(Move latestMove){this.latestMove = latestMove;}

    private int getTurn() {return this.turn;}
    private void nextTurn() {this.turn += 1;}

    /**
     * TODO: Make calculateWinner actually calculate the winner.
     */
    private void calculateWinner(){}

    public int getWinner() {
        if(this.isFinished){
            return this.winner;
        }
        /**
         * TODO: Make it return an error.
         */
        return -1;
    }

    public boolean isFinished() {return this.isFinished;}
    private void finish() {
        this.calculateWinner();
        this.isFinished = true;
    }

    public void run() {
        boolean bothArePassing = false;
        while (!bothArePassing & this.getTurn() < 120){
            try {
                //thread to sleep for the specified number of milliseconds
                Thread.sleep(500);
            } catch (java.lang.InterruptedException ie) {
                System.out.println(ie);
            }

            System.out.print("Iteration: " + this.getTurn() + " \n");
            this.board.printBoard();

            int color = this.getTurn()%2+1;
            this.bots[color-1].calculateNextMove(this.board.copy(), color);
            System.out.print(this.bots[color-1].getMove() + " \n");
            this.board.doMove(this.bots[color-1].getMove(), color);

            bothArePassing = this.bots[color-1].getMove().isPassing() & this.getLatestMove().isPassing();
            this.setLatestMove(this.bots[color-1].getMove());
            this.nextTurn();

        }
        System.out.print("Iteration: " + this.getTurn() + " \n");
        this.board.printBoard();
        this.finish();
    }
}

package Game;

import Board.NextMove;
import Board.ReversiBoard;
import GameBot.GameBot;

/**
 * Created by Joel Magnusson on 2016-04-03.
 */
public class Game {
    private int winner;
    private boolean isFinished = false;
    private int iteration = 0;
    private ReversiBoard board = new ReversiBoard();
    private GameBot[] bots = new GameBot[2];

    public Game(GameBot bot){ this(bot,bot); }

    public Game(GameBot bot1, GameBot bot2){
        this.bots[0] = bot1;
        this.bots[1] = bot2;
    }

    public GameBot[] getBots() {return this.bots;}

    private int getIteration() {return this.iteration;}
    private void increaseIteration() {this.iteration += 1;}

    private void calculateWinner(){}

    public int getWinner() {
        if(this.isFinished){
            return this.winner;
        }
        return -1;
    }

    public boolean isFinished() {return this.isFinished;}
    private void setIsFinished() {this.isFinished = true;}

    private void oneIteration(int color) {
        System.out.print("Iteration: " + this.getIteration() + " \n");
        this.board.printBoard();
        this.bots[color-1].calculateNextMove(this.board.copy(), color);
        System.out.print(this.bots[color-1].getNextMove() + " \n");
        this.board.doMove(this.bots[color-1].getNextMove(), color);
        this.increaseIteration();
    }

    public void run() {
        while(this.getIteration() < 60){
            try {
                //thread to sleep for the specified number of milliseconds
                Thread.sleep(500);
            } catch (java.lang.InterruptedException ie) {
                System.out.println(ie);
            }
            this.oneIteration(this.getIteration()%2+1);

            if (this.getIteration() == 59) {
                System.out.print("Iteration: " + 60 + " \n");
                this.board.printBoard();
            }
        }
        this.setIsFinished();
    }
}

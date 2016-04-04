package GameBot;

import Board.Move;
import Board.ReversiBoard;

/**
 * Created by Sven Eriksson on 2016-03-25.
 */
abstract public class GameBot {
    protected boolean isRunning = false;
    private Move move = new Move();
    private int version = 0;
    private String name = "Unknown";
    private String creator = "Unknown";

    /**
     * This method is responsible for doing the calculation for the next move.
     * The next move should be saved in the class variable nextMove.
     * You will have 10 seconds to calculate a move.
     *
     * It is highly recommended that isRunning is set to true and nextMove to null when this method is initialized.
     * Once isRunning is changed to false, this method must be exited within 2 seconds.
     *
     * @param board 10x10 2d array. Outer edges are -1, empty areas are 0, 1 and 2 represent game pieces
     */
    public abstract void calculateNextMove(ReversiBoard board, int color);

    /**
     *
     * @param move
     * null means pass
     * Otherwise an array with two integers in the range [1,8] is expected. This will indicate the move that you will do.
     */


    protected void setMove(Move move){
        this.move = move;
    }

    public Move getMove(){
        return this.move;
    }

    public void stopCalculating(){
        this.isRunning = false;
    }

    public int getVersion(){
        return this.version;
    }

    protected void setVersion(int v){
        this.version = v;
    }

    public String getName(){
        return this.name;
    }

    protected void setName(String n){
        this.name = n;
    }

    public String getCreator(){
        return this.creator;
    }

    protected void setCreator(String c){
        this.creator = c;
    }
}

package gameBot;

/**
 * Created by Sven Eriksson on 2016-03-25.
 */
abstract public class GameBot {
    boolean isRunning = false;
    int[] nextMove = null;


    /**
     * This method is responsible for doing the calculation for the next move.
     * The next move should be saved in the class variable nextMove.
     * You will have 10 seconds to calculate a move.
     *
     * It is highly recommended that isRunning is set to true and nextMove to null when this method is initialized.
     * Once isRunning is changed to false, this method must be exited within 2 seconds.
     *
     * @param board
     */
    public abstract void calculateNextMove(int[][] board, int colour);

    /**
     *
     * @param nextMove
     * null means pass
     * Otherwise an array with two integers in the range [1,8] is expected. This will indicate the move that you will do.
     */
    private void setNextMove(int[] nextMove){
        this.nextMove = nextMove.clone();
    }

    public int[] getNextMove(){
        return this.nextMove;
    }

    public void stopCalculating(){
        this.isRunning = false;
    }
}

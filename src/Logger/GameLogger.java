package Logger;

import Board.Move;
import GameBot.GameBot;

/**
 * Created by Sven Eriksson on 2016-04-04.
 */
public class GameLogger {
    private GameBot A;
    private GameBot B;
    private int gameNumber;

    public GameLogger(GameBot A, GameBot B, int gameNumber){
        this.A = A;
        this.B = B;
        this.gameNumber = gameNumber;
    }

    public void newMove(GameBot X, Move move, int color){

    }

    public void setWinner(GameBot X, int winnerScore, int loserScore, String reason){

    }

}

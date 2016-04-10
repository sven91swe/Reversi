package Game;

import GameBot.GameBot;
import Board.Move;

/**
 * Created by Sven Eriksson on 2016-04-07.
 */
public class MoveTimer implements Runnable{
    private GameBot gameBot;
    private Move nextMove;
    public MoveTimer(GameBot gameBot){
        this.gameBot = gameBot;
    }

    @Override
    public void run() {
        this.nextMove = this.gameBot.getMove();
        this.gameBot.stopCalculating();

    }

    public Move getNextMove(){
        return this.nextMove;
    }
}

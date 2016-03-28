package gameBot.MGPlayer;

import Board.Board;
import gameBot.GameBot;
import Board.NextMove;

/**
 * Created by Sven Eriksson on 2016-03-25.
 */
public class MGBot extends GameBot {
    public MGBot(){
        super();
        this.setCreator("MGUser");
        this.setName("MGBot");
        this.setVersion(1);

    }
    @Override
    public void calculateNextMove(Board board, int colour) {
        this.isRunning = true;
        NextMove temp = null;
        this.setNextMove(temp);

        this.isRunning = false;
    }
}

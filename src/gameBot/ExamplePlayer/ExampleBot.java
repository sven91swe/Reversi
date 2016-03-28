package gameBot.ExamplePlayer;

import Board;
import gameBot.GameBot;
import NextMove;

/**
 * Created by Sven Eriksson on 2016-03-25.
 */
public class ExampleBot extends GameBot {
    public ExampleBot(){
        super();
        this.setCreator("ExampleUser");
        this.setName("ExampleBot");
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

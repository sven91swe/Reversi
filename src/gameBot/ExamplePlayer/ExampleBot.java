package gameBot.ExamplePlayer;

import Board.ReversiBoard;
import gameBot.GameBot;
import Board.NextMove;

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
    public void calculateNextMove(ReversiBoard reversiBoard, int colour) {
        this.isRunning = true;
        NextMove temp = new NextMove();
        this.setNextMove(temp);

        this.isRunning = false;
    }
}

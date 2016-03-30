package GameBot.MGPlayer;

import Board.ReversiBoard;
import GameBot.GameBot;
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
    public void calculateNextMove(ReversiBoard reversiBoard, int colour) {
        this.isRunning = true;
        NextMove temp = new NextMove();
        this.setNextMove(temp);

        this.isRunning = false;
    }
}

package GameBot.ExamplePlayer;

import Board.ReversiBoard;
import GameBot.GameBot;
import Board.NextMove;

import java.util.ArrayList;

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
    public void calculateNextMove(ReversiBoard reversiBoard, int color) {
        this.isRunning = true;
        ArrayList<NextMove> list = reversiBoard.allPotentialMoves(color);
        NextMove nextMove = null;
        if(list.size() != 0) {
            nextMove = list.get(0);
        }else{
            nextMove = new NextMove(true);
        }
        this.setNextMove(nextMove);

        this.isRunning = false;
    }
}

package GameBot.ExamplePlayer;

import Board.ReversiBoard;
import GameBot.GameBot;
import Board.Move;

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
        ArrayList<Move> list = reversiBoard.allPotentialMoves(color);
        Move move = null;
        if(list.size() != 0) {
            move = list.get(0);
        }else{
            move = new Move(true);
        }
        this.setMove(move);

        this.isRunning = false;
    }
}

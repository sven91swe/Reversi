package GameBot.NotAPlayer;

import Board.ReversiBoard;
import GameBot.GameBot;
import Board.Move;

import java.util.ArrayList;

/**
 * Created by Joel Magnusson on 2016-04-03.
 */
public class NotABot extends GameBot {
    public NotABot(){
        super();
        this.setCreator("NotAUser");
        this.setName("NotABot");
        this.setVersion(-1);

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

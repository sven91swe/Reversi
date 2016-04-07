package GameBot.MKBot;

import Board.ReversiBoard;
import GameBot.GameBot;
import Board.Move;

import java.util.ArrayList;

/**
 * Created by Mattias Kjelltoft on 2016-04-07.
 */
public class MKBot extends GameBot {
    public MKBot(){
        super();
        this.setCreator("Mattias Kjelltoft");
        this.setName("MKBot");
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

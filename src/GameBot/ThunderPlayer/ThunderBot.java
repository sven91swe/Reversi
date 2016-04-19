package GameBot.ExamplePlayer;

import Board.ReversiBoard;
import GameBot.GameBot;
import Board.Move;

import java.util.ArrayList;
import java.util.Random;

public class ThunderBot extends GameBot {
    public ThunderBot(){
        super();
        this.setCreator("ExampleUser");
        this.setName("ExampleBot");
        this.setVersion(1);

    }
    @Override
    public void calculateNextMove(ReversiBoard reversiBoard, int color, ArrayList<Move> allPreviousMoves) {
        this.isRunning = true;

        ArrayList<Move> list = reversiBoard.allPotentialMoves(color);
	Random rand = new Random();
        Move move = null;
        if(list.size() != 0) {
            move = list.get(rand.nextInt(list.size()));
        }else{
            move = new Move(true);
        }
        this.setMove(move);

        this.isRunning = false;
    }
    
}

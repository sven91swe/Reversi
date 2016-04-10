package GameBot.MKBot;

import Board.ReversiBoard;
import Board.Move;
import GameBot.GameBot;

import java.util.ArrayList;

/**
 * Created by Mattias Kjelltoft on 2016-04-07.
 */
public class MKBot extends GameBot {
    private Node root;
    
    public MKBot(){
        super();
        this.setCreator("Mattias Kjelltoft");
        this.setName("MKBot");
        this.setVersion(1);
        root = null;
    }
    
    @Override
    public void calculateNextMove(ReversiBoard reversiBoard, int color, ArrayList<Move> allPreviousMoves) {
        this.isRunning = true;
        
        // Safety measure, set the move to something valid
        ArrayList<Move> list = reversiBoard.allPotentialMoves(color);
        Move move = null;
        if(list.size() != 0) {
            move = list.get(0);
        }else{
            move = new Move(true);
        }
        this.setMove(move);
        
        // Create or prune the tree
        if(root == null){ // Create the tree if it is not existing
            root = new Node( null, null, 1, reversiBoard);
        }else{ // Prune the tree and keep only the branch of the move just executed
            
        }
        
        
        
        this.isRunning = false;
    }
}

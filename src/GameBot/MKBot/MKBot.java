package GameBot.MKBot;

import Board.ReversiBoard;
import Board.Move;
import GameBot.GameBot;

import java.util.ArrayList;
import java.util.List;

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
    public void calculateNextMove(ReversiBoard reversiBoard, int color, List<Move> allPreviousMoves) {
        this.isRunning = true;
        
        // Safety measure, set the move to something valid
        ArrayList<Move> list = reversiBoard.allPotentialMoves(color);
        Move move = null;
        if(list.size() > 0) {
            move = list.get(0);
        }else{
            move = new Move(true);
        }
        this.setMove(move);
        
        // Create or prune the tree
        if(root == null){ // Create the tree if it is not existing
            root = new Node( null, null, 1, reversiBoard);
        }else{ // Prune the tree and keep only the branch of the move just executed
            root = root.findChildByMove(allPreviousMoves.get(allPreviousMoves.size()-2)); // Protagonists last move
            root = root.findChildByMove(allPreviousMoves.get(allPreviousMoves.size()-1)); // Opponents last move
        }
        
        // Start or continue the breadth first search
        //      Check if latest tier are done
        //      Spawn next tier or continue on last tier
        //      Update node values of next tier
        //      Update branch values 
        //      Update current move
        //      Check the time and maybee exit
        int i=0; // TODO Fix a timer
        if(root.getNumberOfChildren() > 0){
            do{
                    Node.resetTraversedCounter();
                    if(!(i<3)){ // TODO Fix a timer
                        break; // TODO return gracefully
                    }
            }while(!root.updateLeafNodeValues());
            root.updateBranchValue();
            move = root.getBestMove();
        }
        while(i<3){ // TODO Fix a timer
            root.spawnNewChildTier();
            do{
                Node.resetTraversedCounter();
                if(!(i<3)){ // TODO Fix a timer
                    break; // TODO return gracefully
                }
            }while(!root.updateLeafNodeValues());
            root.updateBranchValue();
            move = root.getBestMove();
            i++; // TODO Fix a timer
        }
        
        this.isRunning = false;
    }
}

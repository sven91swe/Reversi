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
    public void calculateNextMove(ReversiBoard reversiBoard, int color, ArrayList<Move> allPreviousMoves) {
        this.isRunning = true;

        ArrayList<Move> list = reversiBoard.allPotentialMoves(color);

        Move testMove = null;
        int testResult;
        int bestResult = -1;
        if(list.size() != 0) {
            int choice = 0;
            for(int i = 0;i < list.size();i++) {
                testMove = list.get(i);
                testResult = reversiBoard.evaluateMove(testMove, color).get("total");
                if(testResult>bestResult) {
                    bestResult = testResult;
                    choice = i;
                }
            }
            testMove = list.get(choice);
        }else{
            testMove = new Move(true);
        }
        this.setMove(testMove);

        this.isRunning = false;
    }
}

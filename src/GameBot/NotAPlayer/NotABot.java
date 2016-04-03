package GameBot.NotAPlayer;

import Board.ReversiBoard;
import GameBot.GameBot;
<<<<<<< eda90ec9b8273b148d425361682fdefa10957093
import Board.Move;
=======
import Board.NextMove;
>>>>>>> New GameBot package added. Note that it does not include a bot.

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

        ArrayList<NextMove> list = reversiBoard.allPotentialMoves(color);

        NextMove testMove = null;
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
            testMove = new NextMove(true);
        }
        this.setNextMove(testMove);

        this.isRunning = false;
    }
}

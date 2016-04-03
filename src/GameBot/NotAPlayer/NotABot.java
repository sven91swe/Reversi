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

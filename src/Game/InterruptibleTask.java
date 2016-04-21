package Game;

import Board.ReversiBoard;
import Board.Move;
import GameBot.GameBot;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Joel Magnusson on 2016-04-06.
 */
public class InterruptibleTask implements Callable<Move> {
    private GameBot bot;
    private ReversiBoard board;
    private int color;
    private List<Move> allMoves;

    public InterruptibleTask(GameBot bot, ReversiBoard board, int color, List<Move> allPreviousMoves){
        this.bot = bot;
        this.board = board;
        this.color = color;
        this.allMoves = allPreviousMoves;

    }

    @Override
    public Move call() throws Exception {
        this.bot.calculateNextMove(this.board, this.color, this.allMoves);
        return this.bot.getMove();
    }
}

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
    private int color;
    private GameState gameState;

    public InterruptibleTask(GameBot bot, GameState gameState, int color){
        this.bot = bot;
        this.gameState = gameState;
        this.color = color;
    }

    @Override
    public Move call() throws Exception {
        this.bot.calculateNextMove(this.gameState, this.color);
        return this.bot.getMove();
    }
}

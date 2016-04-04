package Game;

import Board.Move;
import Board.ReversiBoard;
import GameBot.GameBot;
import Logger.GameLogger;

/**
 * Created by Sven Eriksson on 2016-04-04.
 * Based on code made by Joel Magnusson on 2016-04-03.
 */
public final class Game {
    private Game(){}

    /**
     * TODO
     * @param B0
     * @param B1
     * @param gameLogger
     * @return
     */
    public static GameBot playGame(GameBot B0, GameBot B1, GameLogger gameLogger){
        return Game.playGame(B0, B1, gameLogger, true);
    }

    /**
     * TODO
     * @param B0
     * @param B1
     * @param gameLogger
     * @param showText
     * @return
     */
    public static GameBot playGame(GameBot B0, GameBot B1, GameLogger gameLogger, boolean showText){
        int currentTurn = 0;
        Move currentMove = new Move(false);
        Move lastMove = new Move(false);
        ReversiBoard board = new ReversiBoard();
        GameBot[] bots = {B0, B1};

        int color = 1;
        int botNumber;

        boolean bothArePassing = currentMove.isPassing() && lastMove.isPassing();

        if(showText){
            System.out.print("Iteration: " + currentTurn + " \n");
            board.printBoard();
        }


        while (!bothArePassing) {
            color = color % 2 + 1;
            botNumber = color - 1;
            lastMove = currentMove;

            //Get the next move from the bot. Check time. currentMove = null if it took too long.
            currentMove = Game.getNextMoveWithinTime(bots[botNumber], board, color);

            //Check time constraint..
            if (currentMove == null) {
                int scoreBot0 = board.getScore(1);
                int scoreBot1 = board.getScore(2);
                if (bots[botNumber] == B0) {
                    gameLogger.setWinner(B1, scoreBot1, scoreBot0, "Opponent ran out of time.");
                    return B1;
                } else {
                    gameLogger.setWinner(B0, scoreBot0, scoreBot1, "Opponent ran out of time.");
                    return B0;
                }
            }

            //Log move.
            gameLogger.newMove(bots[botNumber], currentMove, color);

            //Check if move is valid.
            if (!board.isValidMove(currentMove, color)) {
                int scoreBot0 = board.getScore(1);
                int scoreBot1 = board.getScore(2);
                if (bots[botNumber] == B0) {
                    gameLogger.setWinner(B1, scoreBot1, scoreBot0, "Invalid move by opponent.");
                    return B1;
                } else {
                    gameLogger.setWinner(B0, scoreBot0, scoreBot1, "Invalid move by opponent.");
                    return B0;
                }
            }

            //Do the move on the board. (=updates board)
            board.doMove(currentMove, color);

            //Check if game has ended.
            bothArePassing = currentMove.isPassing() && lastMove.isPassing();

            currentTurn++;

            if (showText) {
                try {
                    //thread to sleep for the specified number of milliseconds
                    Thread.sleep(500);
                } catch (java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }

                System.out.print("Iteration: " + currentTurn + " \n");
                System.out.print(currentMove + " \n");
                board.printBoard();

            }
        }


        //Game has ended, print board and calculate score and winner.
        int scoreBot0 = board.getScore(1);
        int scoreBot1 = board.getScore(2);

        if(scoreBot0 > scoreBot1){
            gameLogger.setWinner(B0, scoreBot0, scoreBot1, "Higher score, end of game.");
            return B0;
        }else if(scoreBot0 < scoreBot1){

            gameLogger.setWinner(B1, scoreBot1, scoreBot0, "Higher score, end of game.");
            return B1;
        }else{
            gameLogger.setWinner(null, scoreBot0, scoreBot1, "Same score, end of game.");
            return null;
        }
    }

    /**
     * TODO
     * @param bot
     * @param board
     * @param color
     * @return Move from the bot. If it ran out of time, returns null.
     */
    private static Move getNextMoveWithinTime(GameBot bot, ReversiBoard board, int color){

        //TODO implement time control.
        //Return null if out of time.

        bot.calculateNextMove(board.copy(), color);

        return bot.getMove();
    }
}

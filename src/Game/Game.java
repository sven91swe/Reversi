package Game;

import Board.Move;
import Board.ReversiBoard;
import GameBot.GameBot;
import Logger.GameLogger;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Sven Eriksson on 2016-04-04.
 * Based on code made by Joel Magnusson on 2016-04-03.
 */
public final class Game {
    //TODO: Consider making this into an object class due to these two.
    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

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

    public static GameBot playGame(GameBot B0, GameBot B1, GameLogger gameLogger, boolean showText){
	return playGame(B0,B1,gameLogger,showText,new ReversiBoard());
    }
    
    /**
     * TODO
     * @param B0
     * @param B1
     * @param gameLogger
     * @param showText
     * @param board
     * @return
     */   
    public static GameBot playGame(GameBot B0, GameBot B1, GameLogger gameLogger, boolean showText,ReversiBoard board){
        int currentTurn = 0;
        Move currentMove = new Move(false);
        Move lastMove = new Move(false);
        //ReversiBoard board = new ReversiBoard();
        GameBot[] bots = {B0, B1};
        ArrayList<Move> allPreviousMoves = new ArrayList<Move>();

        int color = 0; //First update of color will set this to 1.
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
            //Make sure that the bots only recieves copies of the board and arrayList.
            currentMove = Game.getNextMoveWithinTime(bots[botNumber], board.copy(), color, new ArrayList<Move>(allPreviousMoves));

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
            allPreviousMoves.add(currentMove);

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
    private static Move getNextMoveWithinTime(GameBot bot, ReversiBoard board, int color, ArrayList<Move> allPreviousMoves){
        MoveTimer moveTimer = new MoveTimer(bot);
        Future<Move> future =
                Game.executorService.submit(new InterruptibleTask(bot, board, color, allPreviousMoves));

        ScheduledFuture scheduledFuture =
                Game.scheduledExecutorService.schedule(moveTimer, 10, TimeUnit.SECONDS);

        Move nextMove = null;

        try {
            //Return future move, if within time limit (10 seconds, hardcoded).
            nextMove = future.get(12, TimeUnit.SECONDS);
            //If this returns true, moveTimers had not yet completed.
            if(scheduledFuture.cancel(true)) {

            } else {
                //If moveTimer completed.
                nextMove = moveTimer.getNextMove();
            }
        } catch (TimeoutException e) {
            future.cancel(true);
            nextMove = null;
            System.out.println("TimeoutException!");
        } catch (InterruptedException e) {
            future.cancel(true);
            nextMove = null;
            System.out.println("InterruptedException!");
        } catch (ExecutionException e) {
            future.cancel(true);
            nextMove = null;
            System.out.println("ExecutionException!");
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	    System.exit(1);
        }

        //Return null if out of time or error..
        return nextMove;
    }
}

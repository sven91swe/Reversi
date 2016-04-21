import Board.Move;
import Board.ReversiBoard;
import Game.Game;
import Logger.GameLogger;
import GraphicalInterface.ReversiMainView;
import GameBot.GameBot;

import GameBot.ExamplePlayer.ExampleBotCreator;
import GameBot.NotAPlayer.NotABotCreator;
import GameBot.ThunderPlayer.ThunderBotCreator;
//import GameBot.MKBot.MKBotCreator;
// Remember to add the new bots!

public class Main {

    public static void main(String[] args) {
        test5();

        System.out.print("\n");
        System.exit(0);
    }

    public static void test2(){
        ReversiBoard board = new ReversiBoard();
        board.printBoard();

        board.doMove(new Move(3,4),2);
        board.printBoard();

        board.doMove(new Move(3,3),1);
        board.printBoard();

        System.out.print(board.allPotentialMoves(2));

        System.out.print("\n" + board.doMove(new Move(3,2),1));
    }

    public static void test3(){
        GameBot[] bots = new GameBot[2];
        bots[0] = new ExampleBotCreator().getNewGameBot();
        bots[1] = new ExampleBotCreator().getNewGameBot();

        ReversiBoard board = new ReversiBoard();

        Move move;
        /* By only doing 60 iterations the game could be stopped when there are still valid moves to be made. 
        *  This is due to the fact that passing might be a valid move during the game. 
        *  But that player might be able to do a valid move later on during the game.
        *  TODO: Implemt dual pass check and break loop. Loop over a larger number (60)
        */ 
        
        for(int i = 0; i<60; i++){
            for(int j = 0; j<2; j++){
                try {
                    //thread to sleep for the specified number of milliseconds
                    Thread.sleep(500);
                } catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
                System.out.print("Iteration: " + (2*i+j) + " \n");
                board.printBoard();
                bots[j].calculateNextMove(board.copy(), j+1, null);
                move = bots[j].getMove();
                System.out.print(move + " \n");
                board.doMove(move, j+1);

                if((2*i+j) == 59){
                    System.out.print("Iteration: " + 60 + " \n");
                    board.printBoard();
                }
            }
        }
    }


    public static void test5(){
        GameBot B1 = new ThunderBotCreator().getNewGameBot();
        GameBot B2 = new NotABotCreator().getNewGameBot();

        Game game = new Game();

        ReversiMainView view = new ReversiMainView();
        view.setVisible(true);

        game.addObserver(view);

        GameLogger logger = new GameLogger(B1, B2, 1);
	    GameBot winner = game.playGame(B1, B2, logger, true);
        System.out.print(winner);
    }
}

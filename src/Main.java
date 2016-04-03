import Board.NextMove;
import Board.ReversiBoard;
import GameBot.NotAPlayer.NotABot;
import GameBot.NotAPlayer.NotABotCreator;
import GameBot.ExamplePlayer.ExampleBot;
import GameBot.ExamplePlayer.ExampleBotCreator;
import GameBot.GameBot;
import GameBot.GameBotCreator;

public class Main {

    public static void main(String[] args) {
        test3();

    }

    public static void test1(){
        System.out.println("Board with markers in the middle (board2)");
        Support.printBoard(Support.newBoard());


        GameBotCreator c = new ExampleBotCreator();
        GameBot g = c.getNewGameBot();

        g.calculateNextMove(null, 1);
        System.out.print(g.getNextMove().getX() + "  " + g.getNextMove().getY() + "\n");
        System.out.print(c.getCreator() + " - " + c.getName() + " - " + c.getVersion());
    }

    public static void test2(){
        ReversiBoard board = new ReversiBoard();
        board.printBoard();

        board.doMove(new NextMove(3,4),2);
        board.printBoard();

        board.doMove(new NextMove(3,3),1);
        board.printBoard();

        System.out.print(board.allPotentialMoves(2));

        System.out.print("\n" + board.doMove(new NextMove(3,2),1));
    }

    public static void test3(){
        GameBot[] bots = new GameBot[2];
        bots[0] = new ExampleBotCreator().getNewGameBot();
        bots[1] = new ExampleBotCreator().getNewGameBot();

        ReversiBoard board = new ReversiBoard();

        NextMove nextMove;
        for(int i = 0; i<30; i++){
            for(int j = 0; j<2; j++){
                try {
                    //thread to sleep for the specified number of milliseconds
                    Thread.sleep(500);
                } catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
                System.out.print("Iteration: " + (2*i+j) + " \n");
                board.printBoard();
                bots[j].calculateNextMove(board.copy(), j+1);
                nextMove = bots[j].getNextMove();
                System.out.print(nextMove + " \n");
                board.doMove(nextMove, j+1);

                if((2*i+j) == 59){
                    System.out.print("Iteration: " + 60 + " \n");
                    board.printBoard();
                }
            }
        }


    }


}

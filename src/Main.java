import Board.NextMove;
import Board.ReversiBoard;
import gameBot.ExamplePlayer.ExampleBotCreator;
import gameBot.GameBot;
import gameBot.GameBotCreator;

public class Main {

    public static void main(String[] args) {
        test2();

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

    }
}

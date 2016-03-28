import gameBot.ExamplePlayer.ExampleBotCreator;
import gameBot.GameBot;
import gameBot.GameBotCreator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Board with markers in the middle (board2)");
        Support.printBoard(Support.newBoard());


        GameBotCreator c = new ExampleBotCreator();
        GameBot g = c.getNewGameBot();

        g.calculateNextMove(null, 1);
        System.out.print(g.getNextMove().getX() + "  " + g.getNextMove().getY() + "\n");
        System.out.print(c.getCreator() + " - " + c.getName() + " - " + c.getVersion());

    }


}

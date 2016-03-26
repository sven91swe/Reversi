package gameBot.ExamplePlayer;

import gameBot.GameBot;

/**
 * Created by Sven Eriksson on 2016-03-25.
 */
public class ExampleBot extends GameBot {
    public ExampleBot(){
        super();
        this.setCreator("ExampleUser");
        this.setName("ExampleBot");
        this.setVersion(1);

    }
    @Override
    public void calculateNextMove(int[][] board, int colour) {
        this.isRunning = true;
        int[] temp = {20,20};
        this.setNextMove(temp);

        this.isRunning = false;
    }
}

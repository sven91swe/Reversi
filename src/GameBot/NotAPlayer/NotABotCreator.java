package GameBot.NotAPlayer;

import GameBot.GameBot;
import GameBot.GameBotCreator;

/**
 * Created by Joel Magnusson on 2016-04-03.
 */
public class NotABotCreator extends GameBotCreator {
    @Override
    protected GameBot createNewGameBot() {
        return new NotABot();
    }
}

package GameBot.MKBot;

import GameBot.GameBot;
import GameBot.GameBotCreator;
import GameBot.MKBot.MKBot;

/**
 * Created by Sven Eriksson on 2016-03-26.
 */
public class MKBotCreator extends GameBotCreator {
    @Override
    protected GameBot createNewGameBot() {
        return new MKBot();
    }
}

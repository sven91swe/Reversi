package GameBot.MKBot;

import GameBot.GameBot;
import GameBot.GameBotCreator;

/**
 * Created by Mattias Kjelltoft on 2016-04-07.
 */
public class MKBotCreator extends GameBotCreator {
    @Override
    protected GameBot createNewGameBot() {
        return new MKBot();
    }
}

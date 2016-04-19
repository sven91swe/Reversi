package GameBot.ExamplePlayer;

import GameBot.GameBot;
import GameBot.GameBotCreator;

/**
 * Created by Sven Eriksson on 2016-03-26.
 */
public class ThunderBotCreator extends GameBotCreator {
    @Override
    protected GameBot ThunderNewGameBot() {
        return new ThunderBot();
    }
}

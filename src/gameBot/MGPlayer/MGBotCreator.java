package gameBot.MGPlayer;

import gameBot.GameBot;
import gameBot.GameBotCreator;

/**
 * Created by Sven Eriksson on 2016-03-26.
 */
public class MGBotCreator extends GameBotCreator {
    @Override
    protected GameBot createNewGameBot() {
        return new MGBot();
    }
}

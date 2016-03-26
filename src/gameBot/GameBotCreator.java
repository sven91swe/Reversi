package gameBot;

/**
 * Created by Sven Eriksson on 2016-03-25.
 */
public abstract class GameBotCreator {
    protected GameBot lastGameBot = null;

    private GameBot getLastGameBot(){
        if(this.lastGameBot == null){
            lastGameBot = this.getNewGameBot();
        }
        return lastGameBot;
    }

    public GameBot getNewGameBot(){
        this.lastGameBot = this.createNewGameBot();
        return this.lastGameBot;
    }

    protected abstract GameBot createNewGameBot();


    public String getName(){
        return this.getLastGameBot().getName();
    }

    public String getCreator(){
        return this.getLastGameBot().getCreator();
    }

    public int getVersion(){
        return this.getLastGameBot().getVersion();
    }


    //Implementera singleton för detta objekt. Detta är ansvarigt för att förse spelet med ny instanser av dess bot.
}

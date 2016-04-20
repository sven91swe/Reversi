package GameBot.ThunderPlayer;

import Game.Game;
import Logger.GameLogger;
import GameBot.GameBot;
import GameBot.GameBotCreator;
import java.util.ArrayList;

/**
 * Created by Tor Djarv on 2016-04-19.
 */
public class ThunderBotCreator extends GameBotCreator {

    private int numOfContestents = 10;
    private int numOfGenerations = 10;
    private ThunderBot compete(ThunderBot b1,ThunderBot b2){
	GameLogger logger = new GameLogger((GameBot)b1,(GameBot)b2,1);
        GameBot winner = Game.playGame((GameBot)b1,(GameBot)b2,logger,false);
	if (winner == null){
	    return b1;
	}else{
	    return (ThunderBot)winner;
	}
    }
    
    private void evolve(){
	ArrayList<ThunderBot> bots = new ArrayList<ThunderBot>();
	for (int i = 0; i<numOfContestents; i++){
	    ThunderBot b = new ThunderBot();
	    b.mutate();
	    bots.add(b);
	}
	ThunderBot best=new ThunderBot();
	for (int i = 0; i<numOfGenerations; i++){
	    System.out.print("On generation "+(i+1)+" of "+numOfGenerations+"\r");
	    best = bots.get(0);
	    for (int j = 1; j<bots.size(); j++){
		best = compete(best,bots.get(j));
	    }
	    if (i<numOfGenerations-1){
		for (int j = 0; j<bots.size(); j++){
		    ThunderBot b;
		    if (j!=1){
			b = new ThunderBot(best);
		    }else{
			b = new ThunderBot();
		    }
		    if (j>1)
			b.mutate();
		    bots.set(j,b);
		}
	    }
	}
	System.out.println("");
	best.dumpValues();
	System.exit(0);
    }
    
    @Override
    protected GameBot createNewGameBot() {
	//evolve();
        return new ThunderBot();
	
    }
}

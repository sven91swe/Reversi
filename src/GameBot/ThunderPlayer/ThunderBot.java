package GameBot.ThunderPlayer;

import Board.ReversiBoard;
import GameBot.GameBot;
import Board.Move;

import java.util.ArrayList;
import java.util.Random;

public class ThunderBot extends GameBot {
    public ThunderBot(){
        super();
        this.setCreator("Thundermoose");
        this.setName("ThunderBot");
        this.setVersion(1);
	maxIter = 10;
	level = 0;
	impMinLsize = 1;
	impMaxMinStrengthEnem = 1;
	impMyPotScore = 1;
	impEnemPotScore = 1;
	impRand = 0.1;
	rand = new Random();
    }
    // Copy Constructor
    public ThunderBot(ThunderBot thb){
	super();
        this.setCreator("Thundermoose");
        this.setName("ThunderBot");
        this.setVersion(1);
	maxIter = 10;
	level = 0;
	impMinLsize = thb.impMinLsize;
	impMaxMinStrengthEnem = thb.impMaxMinStrengthEnem;
	impMyPotScore = thb.impMyPotScore;
	impEnemPotScore = thb.impEnemPotScore;
	impRand = thb.impRand;
	rand = new Random();
    }

    public void mutate(){
	impMinLsize*=(1+0.1*(2*rand.nextDouble()-1));
	impMaxMinStrengthEnem*=(1+0.1*(2*rand.nextDouble()-1));
	impMyPotScore*=(1+0.1*(2*rand.nextDouble()-1));
	impEnemPotScore*=(1+0.1*(2*rand.nextDouble()-1));
	impRand*=(1+0.1*(2*rand.nextDouble()-1));
    }
    private int maxIter;
    private int level;
    private double impMinLsize;
    private double impMaxMinStrengthEnem;
    private double impMyPotScore;
    private double impEnemPotScore;
    private double impRand;
    private Random rand;
    
    private double evaluateMove(ReversiBoard board,Move move,int color) {
	board.doMove(move,color);
	int colorenm = color%2+1;
	ArrayList<Move> list = board.allPotentialMoves(colorenm);
	
	double minStrengthEnem =0;
	if (level<maxIter){
	    level++;
	    if (list.size()!=0){
		minStrengthEnem = 100;
		for (int i = 0; i<list.size(); i++){
		    double s = evaluateMove(board,list.get(i),colorenm);
		    if (s<minStrengthEnem){
			minStrengthEnem = s;
		    }
		}
	    }else{
		minStrengthEnem = evaluateMove(board,null,colorenm);
	    }
	    level--;
	}
	return impMinLsize*(double)list.size()-
	    impMaxMinStrengthEnem*minStrengthEnem-
	    impMyPotScore*(double)board.getScore(color)+
	    impEnemPotScore*(double)board.getScore(colorenm)+
	    impRand*(2*rand.nextDouble()-1);
	
    }
    
    @Override
    public void calculateNextMove(ReversiBoard reversiBoard, int color, ArrayList<Move> allPreviousMoves) {
        this.isRunning = true;

        ArrayList<Move> list = reversiBoard.allPotentialMoves(color);
	
        Move move = null;
        if(list.size() != 0) {
	    double[] strength = new double[list.size()];
	    double minStrength=100;
	    int minStrengthAt = -1;
            for (int i = 0; i<list.size(); i++){
		strength[i] = evaluateMove(reversiBoard.copy(),list.get(i),color);
		if (strength[i]<minStrength){
		    minStrength = strength[i];
		    minStrengthAt = i;
		}
	    }
	    move = list.get(minStrengthAt);
        }else{
            move = new Move(true);
        }
        this.setMove(move);

        this.isRunning = false;
    }
    
}

package GameBot.ThunderPlayer;

import Board.ReversiBoard;
import GameBot.GameBot;
import Board.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThunderBot extends GameBot {
    private static int i = 0;
    public ThunderBot(){
        super();
        this.setCreator("Thundermoose");
        this.setName("ThunderBot");
        this.setVersion(i);
	maxIter = 10;
	level = 0;
	if (i%2==0){
	    impMinLsize = 1.0677968842632979;
	    impMaxMinStrengthEnem = 0.9631497758418451;
	    impMyPotScore = 0.756886208358836;
	    impEnemPotScore = 0.9731610639163195;
	    impRand = 0.9731610639163195;
	}else{
	    impMinLsize = 0.9594652229462161;
	    impMaxMinStrengthEnem = 0.866446369369751;
	    impMyPotScore = 0.6232029828964971;
	    impEnemPotScore = 0.8813761725790435;
	    impRand = 1.002391562502265;
	}
	i++;
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
    public void dumpValues(){
	System.out.println("impMinLsize = "+impMinLsize);
	System.out.println("impMaxMinStrengthEnem = "+impMaxMinStrengthEnem);
	System.out.println("impMyPotScore = "+impMyPotScore);
	System.out.println("impEnemPotScore = "+impEnemPotScore);
	System.out.println("impRand = "+impRand);
    }
    
    private double evaluateMove(ReversiBoard board,Move move,int color) {
	if (move != null)
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
    public void calculateNextMove(ReversiBoard reversiBoard, int color, List<Move> allPreviousMoves) {
        this.isRunning = true;

        ArrayList<Move> list = reversiBoard.allPotentialMoves(color);
	
        Move move = null;
        if(list.size() != 0) {
	    double[] strength = new double[list.size()];
	    double minStrength=Double.POSITIVE_INFINITY;
	    int minStrengthAt = -1;
            for (int i = 0; i<list.size(); i++){
		strength[i] = evaluateMove(reversiBoard.copy(),list.get(i),color);
		if (strength[i]<minStrength){
		    minStrength = strength[i];
		    minStrengthAt = i;
		}
	    }
	    if (minStrengthAt == -1){
		minStrengthAt = rand.nextInt(list.size());
	    }
	    move = list.get(minStrengthAt);
        }else{
            move = new Move(true);
        }
        this.setMove(move);

        this.isRunning = false;
    }
    
}

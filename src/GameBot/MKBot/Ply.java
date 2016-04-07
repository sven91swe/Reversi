package GameBot.MKBot;

public class Ply{
    private int activePlayer;
    private Ply parrent;
    private Move moveToGetHere;
    private ReversiBoard boardState;
    private double stateValue;
    private double branchValue;
    
    public Ply(){
        activePlayer = 1;
        stateValue = 0;
        branchValue = 0;
        boardState = null;
        parrent = null;
        moveToGetHere = null;
    }

    public Ply(double activePlayer, Ply parrent, Move moveToGetHere, ReversiBoard boardState){
        this.activePlayer = 1;
        this.parrent = null;
        this.moveToGetHere = null;
        this.boardState = null;
        this.stateValue = 0;
        this.branchValue = 0;
    }
    
    public double computeStateValue(){
        // TODO Implement a state evaluation routine.
        return stateValue;
    }
}
package Game;

import Board.Move;
import Board.ReversiBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sven Eriksson on 2016-04-22.
 */
public class GameState {
    private ReversiBoard board;
    private List<Move> listOfMoves;

    public GameState(){
        this(new ReversiBoard());
    }

    public GameState(ReversiBoard b){
        this(b, new ArrayList<Move>());
    }

    public GameState(ReversiBoard b, List<Move> l){
        this.board = b;
        this.listOfMoves = new ArrayList<Move>();
    }

    public void addMove(Move m){
        this.listOfMoves.add(m);
    }

    public void updateBoard(ReversiBoard b){
        this.board = b;
    }

    public List<Move> getListOfMoves(){
        return new ArrayList<Move>(this.listOfMoves);
    }

    public ReversiBoard getBoard(){
        return this.board.copy();
    }

    public GameState copy(){
        return new GameState(this.board.copy(), new ArrayList<Move>(this.listOfMoves));
    }
}

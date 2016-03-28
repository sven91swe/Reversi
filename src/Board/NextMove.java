package Board;

import java.util.*;

public class NextMove {
    private int x;
    private int y;
    private boolean passing;

    public NextMove(){
        this(0,0,true);
    }

    /**
     * @param pass True. This constructor should only be used when you intend to pass. False will generate IllegalArgumentException.
     */
    public NextMove(boolean pass) throws IllegalArgumentException{
       this(0,0,pass);
    }

    /**
     *
     * @param x in range [1,8]
     * @param y in range [1,8]
     * @throws IllegalArgumentException
     */
    public NextMove(int x, int y) throws IllegalArgumentException{
        this(x,y,false);
    }
    private NextMove(int x, int y, boolean pass) throws IllegalArgumentException{
        super();
        if(!pass) {
            if (x < 1 || x > 8) {
                throw new IllegalArgumentException("x is out of range [1,8]");
            }
            if (y < 1 || y > 8) {
                throw new IllegalArgumentException("y is out of range [1,8]");
            }
        }
        this.x = x;
        this.y = y;
        this.passing = pass;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isPassing() {
        return passing;
    }

    public static ArrayList<NextMove> allPotentialMoves(){
        //TODO: Return a list of all potential non-passing move. All 64 potential moves.
        return null;
    }

}

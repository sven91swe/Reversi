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
    public NextMove(boolean pass){
       this(0,0,pass);
    }
    /**
     * @param x in range [1,8]
     * @param y in range [1,8]
     */
    public NextMove(int x, int y){
        this(x,y,false);
    }
    private NextMove(int x, int y, boolean pass){
        super();
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

    public String toString(){
        return "x=" + this.x + " - y=" + this.getY() + " - pass=" + this.passing;
    }
}

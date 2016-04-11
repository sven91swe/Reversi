package Board;

public class Move {
    private int x;
    private int y;
    private boolean passing;

    public Move(){
        this(0,0,true);
    }
    /**
     * @param pass True. This constructor should only be used when you intend to pass. False will generate IllegalArgumentException.
     */
    public Move(boolean pass){
       this(0,0,pass);
    }
    /**
     * @param x in range [1,8]
     * @param y in range [1,8]
     */
    public Move(int x, int y){
        this(x,y,false);
    }
    private Move(int x, int y, boolean pass){
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
        return "x=" + this.x + ", y=" + this.getY() + ", pass=" + this.passing;
    }
    
    public boolean equals(Move m){
        int mx = m.getX();
        int my = m.getY();
        boolean mp = m.isPassing();
        return (mx == x) && (my == y) && (mp == passing);
    }
    
    public Move copy(){
        return new Move(x,y,passing);
    }
}

package Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Integer;

public class ReversiBoard {
    private int[][] boardState;

    public ReversiBoard(){
        super();
        this.boardState = ReversiBoard.newBoard();
    }

    private ReversiBoard(int[][] newBoard){
        this.boardState = newBoard;
    }

    /**
     * Provides a deep copy of this object.
     * @return a copy of this object.
     */

    public ReversiBoard copy(){
        int[][] newBoardState = this.copyBoardState();
        return new ReversiBoard(newBoardState);
    }

    /** Tests is a move is valid.
     * @param color The color of the bot that is testing.
     * @param move The Move that is supposed to be tested.
     * @return true if move is value, false if not.
     */
    public boolean isValidMove(Move move, int color) {
        if (!move.isPassing()) {
            HashMap<String, Integer> result = this.evaluateMove(move, color);
            return result.get("total") > 0;
        } else {
            return this.allPotentialMoves(color).size() == 0;
        }
    }

    /** Provides a HashMap that contains how many pieces that would be flipped in each direction by the Move.
     * @param color The color of the bot that is testing.
     * @param move The Move that is supposed to be tested.
     * @return HaspMap with the keys leftup, left, leftdown, up, down, rightup, right, rightdown and total.
     * Describes the numbers of flipped pieces in each direction.
     */
    public HashMap<String, Integer> evaluateMove(Move move, int color){
        int total = 0;
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        result.put("total", new Integer(0));
        result.put("leftup", new Integer(0));
        result.put("left", new Integer(0));
        result.put("leftdown", new Integer(0));
        result.put("up", new Integer(0));
        result.put("down", new Integer(0));
        result.put("rightup", new Integer(0));
        result.put("right", new Integer(0));
        result.put("rightdown", new Integer(0));

        if(!move.isPassing()){
            if (move.getX() < 1 || move.getX() > 8) {
                return result;
            }
            if (move.getY() < 1 || move.getY() > 8) {
                return result;
            }
            if (this.boardState[move.getX()][move.getY()] != 0) {
                return result;
            }

            int leftup = evaluateInDirection(move.getX(), move.getY(), -1, -1, color);
            if(leftup<0){
                result.put("leftup", new Integer(0));
            }else{
                result.put("leftup", new Integer(leftup));
                total += leftup;
            }

            int left = evaluateInDirection(move.getX(), move.getY(), -1, 0, color);
            if(left<0){
                result.put("left", new Integer(0));
            }else{
                result.put("left", new Integer(left));
                total += left;
            }

            int leftdown = evaluateInDirection(move.getX(), move.getY(), -1, 1, color);
            if(leftdown<0){
                result.put("leftdown", new Integer(0));
            }else{
                result.put("leftdown", new Integer(leftdown));
                total += leftdown;
            }

            int up = evaluateInDirection(move.getX(), move.getY(), 0, -1, color);
            if(up<0){
                result.put("up", new Integer(0));
            }else{
                result.put("up", new Integer(up));
                total += up;
            }

            int down = evaluateInDirection(move.getX(), move.getY(), 0, 1, color);
            if(down<0){
                result.put("down", new Integer(0));
            }else{
                result.put("down", new Integer(down));
                total += down;
            }

            int rightup = evaluateInDirection(move.getX(), move.getY(), 1, -1, color);
            if(rightup<0){
                result.put("rightup", new Integer(0));
            }else{
                result.put("rightup", new Integer(rightup));
                total += rightup;
            }

            int right = evaluateInDirection(move.getX(), move.getY(), 1, 0, color);
            if(right<0){
                result.put("right", new Integer(0));
            }else{
                result.put("right", new Integer(right));
                total += right;
            }

            int rightdown = evaluateInDirection(move.getX(), move.getY(), 1, 1, color);
            if(rightdown<0){
                result.put("rightdown", new Integer(0));
            }else{
                result.put("rightdown", new Integer(rightdown));
                total += rightdown;
            }

            result.put("total", new Integer(total));

        }

        return result;


    }

    private int evaluateInDirection(int x, int y, int deltaX, int deltaY, int color){
        int opponentColor;
        int nbrOfFlippedInDirection = 0;
        if(color==1){
            opponentColor=2;
        }else {
            opponentColor = 1;
        }

        if(this.boardState[x+deltaX][y+deltaY] == opponentColor){
            return this.evaluateInDirection(x+deltaX, y+deltaY, deltaX, deltaY, color)+1;
        }else if(this.boardState[x+deltaX][y+deltaY] == color){
            return 0;
        }else{
            return Integer.MIN_VALUE;
        }
    }

    /** Do the intended move on the board.
     * The board state will be updated once the move is done.
     * @param move
     * @param color
     * @return
     */
    public boolean doMove(Move move, int color) {
        if (isValidMove(move, color)) {
            if(!move.isPassing()) {
                this.boardState[move.getX()][move.getY()] = color;
                this.doMoveInDirection(move.getX(), move.getY(), -1, -1, color);
                this.doMoveInDirection(move.getX(), move.getY(), -1, 0, color);
                this.doMoveInDirection(move.getX(), move.getY(), -1, 1, color);
                this.doMoveInDirection(move.getX(), move.getY(), 0, -1, color);
                this.doMoveInDirection(move.getX(), move.getY(), 0, 1, color);
                this.doMoveInDirection(move.getX(), move.getY(), 1, -1, color);
                this.doMoveInDirection(move.getX(), move.getY(), 1, 0, color);
                this.doMoveInDirection(move.getX(), move.getY(), 1, 1, color);
            }
            return true;
        } else {
            return false;
        }
    }


    private boolean doMoveInDirection(int x, int y, int deltaX, int deltaY, int color){
        int opponentColor;
        if(color==1){
            opponentColor=2;
        }else {
            opponentColor = 1;
        }

        boolean temp;
        if(this.boardState[x+deltaX][y+deltaY] == opponentColor){
            temp = this.doMoveInDirection(x+deltaX, y+deltaY, deltaX, deltaY, color);
            if(temp){
                this.boardState[x+deltaX][y+deltaY]=color;
            }
            return temp;
        }else if(this.boardState[x+deltaX][y+deltaY] == color){
            return true;
        }else{
            return false;
        }
    }

    /** A list for all valid moves.
     *
     * @return a list for all valid moves.
     */
    public ArrayList<Move> allPotentialMoves(int color){
        ArrayList<Move> moves = new ArrayList<Move>();
        Move temp;
        for(int x=1; x<=8 ;x++){
            for(int y=1; y<=8; y++){
                temp = new Move(x,y);
                if(this.isValidMove(temp, color)){
                    moves.add(temp);
                }
            }
        }
        return moves;
    }

    /** Information about a potential piece in a specified location.
     * @param m a non passing move. x in range [1,8], y in range [1,8]
     * @return 0 for empty, 1 and 2 if the color of the piece at that location.
     */
    public int getPieceInformation(Move m){
        if(m.isPassing()){
            throw new IllegalArgumentException("ReversiBoard.getPieceInformation: Can't be a passing move.");
        }
        return this.getPieceInformation(m.getX(), m.getY());
    }

    /** Information about a potential piece in a specified location.
     * @param x in range [1,8]
     * @param y in range [1,8]
     * @return 0 for empty, 1 and 2 if the color of the piece at that location.
     */
    public int getPieceInformation(int x, int y){
        if (x < 1 || x > 8) {
            throw new IllegalArgumentException("x is out of range [1,8]");
        }
        if (y < 1 || y > 8) {
            throw new IllegalArgumentException("y is out of range [1,8]");
        }

        return this.boardState[x][y];
    }

    /** A 2d int array that represents the current state of the board.
     * Note that the indices are off by 1 due to Java indices starting at 0.
     * @return 8x8 integer array of the board state. Note that coordinates (x,y) corresponds to indices (i,j) though (x,y) = (i+1,j+1).
     */
    public int[][] getBoardInformation(){
        int[][] board = new int[8][8];

        for(int i=0; i<8; i++){
            for(int j=0; j<8;j++){
                board[i][j] = this.getPieceInformation(i+1, j+1);
            }
        }

        return board;
    }

    public void printBoard(){
        System.out.println(this);
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        s.append("BoardState: \n");
        for(int y=1;y<=8;y++){
            for(int x=1;x<=8;x++){
                s.append(this.boardState[x][y] + " ");
            }
            s.append("\n");
        }
        s.append("\n");

        return s.toString();
    }

    private int[][] copyBoardState(){
        int[][] newBoard = new int[10][10];

        for(int x=0;x<10;x++){
            for(int y=0;y<10;y++){
                newBoard[x][y] = this.boardState[x][y];
            }
        }
        return newBoard;
    }

    private static int[][] newBoard(){
        int[][] board = new int[10][10];
        board[4][4]=1;
        board[5][5]=1;
        board[4][5]=2;
        board[5][4]=2;

        for(int x=0;x<10;x++){
            board[x][0]= -1;
            board[x][9]= -1;
            board[0][x]= -1;
            board[9][x]= -1;
        }
        return board;
    }

    /** Get the score of one color.
     * Calculates the number of pieces of one color.
     * @param color
     * @return the number of pieces of one color.
     */
    public int getScore(int color){
        int sum = 0;
        for(int x=1; x<=8; x++){
            for(int y=1; y<=8; y++){
                if(this.boardState[x][y]==color){
                    sum++;
                }
            }
        }
        return sum;
    }
}

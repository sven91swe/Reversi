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

    public ReversiBoard copy(){
        int[][] newBoardState = this.copyBoardState();
        return new ReversiBoard(newBoardState);
    }

    /**
     * @param color
     * @param move
     * @return true if move is value, false if not.
     */
    public boolean isValidMove(NextMove move, int color) {
        if (!move.isPassing()) {
            HashMap<String, Integer> result = this.evaluateMove(move, color);
            return result.get("total") > 0;
        } else {
            return this.allPotentialMoves(color).size() == 0;
        }
    }

    /**
     * @param color
     * @param move
     * @return HaspMap with the keys leftup, left, leftdown, up, down, rightup, right, rightdown and total.
     * Describes the numbers of flipped pieces in each direction.
     */
    public HashMap<String, Integer> evaluateMove(NextMove move, int color){
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

    /**
     *
     * @param move
     * @param color
     * @return
     */
    public boolean doMove(NextMove move, int color) {
        if (isValidMove(move, color)) {
            this.boardState[move.getX()][move.getY()] = color;
            this.doMoveInDirection(move.getX(), move.getY(), -1,-1,color);
            this.doMoveInDirection(move.getX(), move.getY(), -1,0,color);
            this.doMoveInDirection(move.getX(), move.getY(), -1,1,color);
            this.doMoveInDirection(move.getX(), move.getY(), 0,-1,color);
            this.doMoveInDirection(move.getX(), move.getY(), 0,1,color);
            this.doMoveInDirection(move.getX(), move.getY(), 1,-1,color);
            this.doMoveInDirection(move.getX(), move.getY(), 1,0,color);
            this.doMoveInDirection(move.getX(), move.getY(), 1,1,color);

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

    /**
     *
     * @return a list for all potential moves.
     */
    public ArrayList<NextMove> allPotentialMoves(int color){
        ArrayList<NextMove> moves = new ArrayList<NextMove>();
        NextMove temp;
        for(int x=1; x<=8 ;x++){
            for(int y=1; y<=8; y++){
                temp = new NextMove(x,y);
                if(this.isValidMove(temp, color)){
                    moves.add(temp);
                }
            }
        }
        return moves;
    }

    /**
     * @param m a non passing move. x in range [1,8], y in range [1,8]
     * @return 0 for empty, 1 and 2 if the color of the piece at that location.
     */
    public int getPieceInformation(NextMove m){
        if(m.isPassing()){
            throw new IllegalArgumentException("ReversiBoard.getPieceInformation: Can't be a passing move.");
        }
        return this.getPieceInformation(m.getX(), m.getY());
    }

    /**
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


    public void printBoard(){
        System.out.println("BoardState:");
        for(int i=1;i<9;i++){
            for(int j=1;j<9;j++){
                System.out.print(this.boardState[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private int[][] copyBoardState(){
        int[][] newBoard = new int[10][10];

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                newBoard[i][j] = this.boardState[i][j];
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

        for(int i=0;i<10;i++){
            board[i][0]= -1;
            board[i][9]= -1;
            board[0][i]= -1;
            board[9][i]= -1;
        }
        return board;
    }
}

package Board;

import java.util.HashMap;

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
    public boolean isValidMove(NextMove move, int color){
        //TODO: Implement is valid logic. Look at function below, it is enough to implement evaluateMove and check if total>0.
        return true;
    }

    /**
     * @param color
     * @param move
     * @return HaspMap with the keys leftup, left, leftdown, up, down, rightup, right, rightdown and total.
     * Describes the numbers of flipped pieces in each direction.
     */
    public HashMap<String, Integer> evaluateMove(NextMove move, int color){
        //TODO: Implemnt logic. Look at function above.
        return null;
    }

    /**
     *
     * @param move
     * @param color
     * @return
     */
    public boolean doMove(NextMove move, int color){
        if(isValidMove(move, color)){
            //TODO: Implement change and flip the correct pieces.
            return true;
        }else{
            return false;
        }
    }

    /**
     *
     * @param m a non passing move.
     * @return 0 for empty, 1 and 2 if the color of the piece at that location.
     */
    public int getPieceInformation(NextMove m){
        if(m.isPassing()){
            throw new IllegalArgumentException("ReversiBoard.getPieceInformation: Can't be a passing move.");
        }

        return this.getPieceInformation(m.getX(), m.getY());
    }

    /**
     *
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
        for(int i=1;i<9;i++){
            for(int j=1;j<9;j++){
                System.out.print(this.boardState[i][j]);
            }
            System.out.println("");
        }
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

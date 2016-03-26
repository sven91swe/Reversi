/**
 * Created by Sven Eriksson on 2016-03-26.
 */
public class Support {
    public static void printBoard(int[][] board){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }

    public static int[][] copyBoard(int[][] board){
        int[][] newBoard = new int[10][10];

        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    public static int[][] newBoard(){
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

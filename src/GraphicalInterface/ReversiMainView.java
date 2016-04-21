package GraphicalInterface;

import Board.ReversiBoard;
import javax.swing.JFrame;

public class ReversiMainView extends JFrame{
    private ReversiBoardPanel boardPanel;
    public ReversiMainView(ReversiBoard rb){
	boardPanel = new ReversiBoardPanel(rb);
	setTitle("Reversi bots");
	setSize(400,400);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add(boardPanel);
    }

}

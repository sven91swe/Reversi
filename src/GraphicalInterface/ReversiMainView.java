package GraphicalInterface;

import Board.ReversiBoard;
import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;

public class ReversiMainView extends JFrame implements Observer{
    private ReversiBoardPanel boardPanel;

	public ReversiMainView(){
        this.boardPanel = new ReversiBoardPanel();
        setTitle("Reversi bots");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(boardPanel);
    }


    @Override
    public void update(Observable o, Object arg) {
        this.boardPanel.update(o, arg);
    }
}

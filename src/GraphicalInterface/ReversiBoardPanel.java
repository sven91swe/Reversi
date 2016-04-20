package GraphicalInterface;

import Board.ReversiBoard;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * @author Tor Djärv
 * @version 1
 * 
 */
public class ReversiBoardPanel extends JPanel implements Action{
    // All private instance variable here
    private ReversiBoard board;
    private Timer updateTimer;
    private const int updateTime = 200; // ms

    // All constructors here
    public ReversiBoardPanel(ReversiBoard board){
	super(true); // sets the JPanel to be double buffered, do not know if necessary
	this.board = board;
	updateTimer = new Timer(updateTime,this);
	updateTimer.start();
    }

    // All private methods here
    private void drawReversiBoard(Graphics g){
	Graphics2D drawArea = (Graphics2D)g;
	// The traditional colour of a Reversi board is green
	// So I stick with that
	drawArea.setBackground(Color.GREEN);
	// TODO: add so that the program can draw board
	
    }
    // All public methdos here
    
    // All overriden methods here
    @Override
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	drawReversiBoard(g);
    }

    @Override
    public void actionPerformed(ActionEvent e){
	repaint();
    }
    
    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        Container c = getParent();
        if (c != null) {
            d = c.getSize();
        } else {
            return new Dimension(10, 10);
        }
        int w = (int) d.getWidth();
        int h = (int) d.getHeight();
        int s = (w < h ? w : h);
        return new Dimension(s, s);
    }
}

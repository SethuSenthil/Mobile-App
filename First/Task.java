import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.JFrame;

public class Task extends JPanel {

	public Task(){
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		frame.setSize(800,400);
        setFocusable(true);
        addMouseListener(
            new MouseInputListener(){

                    public void mouseMoved(MouseEvent e) {
                      System.out.println("x: " + e.getX());
                      System.out.println("y: " + e.getY());

                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void mouseDragged(MouseEvent e) {
                        // TODO Auto-generated method stub

                    }

            }
        );

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	public static void main (String [] args)
	{
		Task demo = new Task();
	}



}
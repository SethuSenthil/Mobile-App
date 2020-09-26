import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Demo3 extends JPanel {

	public Demo3(){
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		frame.setSize(800,400);
        setFocusable(true);
        addKeyListener(
            new KeyListener(){

                public void keyReleased(KeyEvent e){
                    System.out.println("1- "+e.getKeyChar());
                }

                public void keyPressed (KeyEvent e){
                    System.out.println("2- "+e.getKeyChar());
                }

                public void keyTyped(KeyEvent e){
                    System.out.println("3- "+e.getKeyChar());
                }

            }
        );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	public static void main (String [] args)
	{
		Demo3 demo = new Demo3();
	}



}
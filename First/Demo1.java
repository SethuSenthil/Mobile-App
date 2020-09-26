import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Demo1 extends JPanel implements KeyListener{
	public Demo1(){
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		frame.setSize(800,400);
		setFocusable(true);
		addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void keyReleased(KeyEvent e){
		System.out.println("1- "+e.getKeyChar());
	}

	public void keyPressed (KeyEvent e){
		System.out.println("2- "+e.getKeyChar());
	}

	public void keyTyped(KeyEvent e){
		System.out.println("3- "+e.getKeyChar());
	}



	public static void main (String [] args)
	{
		Demo1 demo = new Demo1();
	}



}
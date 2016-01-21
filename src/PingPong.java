//original code by Deepak Saini

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.util.*;

public class PingPong extends Canvas{
	int x,y,dx,dy;
	int a,b,da;
	int c,d,dc;
	int p1 = 0;
	int p2 = 0;
	static int choise = 1;
	int p1Bounce = 0;
	int p2Bounce = 0;
	int p1Grow = 0;
	int p2Grow = 0;
	int speed = 5;

	public PingPong()
	{
		enableEvents(java.awt.AWTEvent.KEY_EVENT_MASK);


		x = 100;
		y = 250;
		dx = speed;
		dy = speed;

		a = 200;
		b = 440;
		da = 10;

		c = 150;
		d = 20;
		dc = 10;

		Timer t = new Timer(true);
		t.schedule(new java.util.TimerTask()
		{
			public void run()
			{
				if(choise== 1){
					opponent();
				}
				doSomething();
				repaint();
			}
		},0,30);
	}

	public void opponent(){
		a = x-30;
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillOval(x,y,20,20);

		g.setColor(Color.red);
		g.fillRect(a,b, 100+p2Grow*20,10);

		g.setColor(Color.red);
		g.fillRect(c,d, 100+p1Grow*20,10);




		g.drawString("Player 2 Score : " + p1 + "Points ", 100, 200);
		g.drawString("Player 1 Score : " + p2 + "Points ", 100, 230 );
	}


	public void processKeyEvent(KeyEvent e)
	{

		if ( e.getID() == KeyEvent.KEY_PRESSED )
		{
			if ( e.getKeyCode() == KeyEvent.VK_S && a<275)
			{
				a = a + da; 
			}

			if ( e.getKeyCode() == KeyEvent.VK_A && a>0 )
			{
				a = a - da;
			}

			if ( e.getKeyCode() == KeyEvent.VK_L && c<275)
			{
				c = c + dc; 
			}

			if ( e.getKeyCode() == KeyEvent.VK_K && c>0 )
			{
				c = c - dc;
			}
		}
	}

	public void doSomething()
	{
		x = x + dx;
		y = y + dy;

		if(x < 0 || x+20 > 360)
		{
			dx = -dx;
		}

		else if(y<20)
		{
			System.out.println("Player 1 = Game Over || Stop It Now");
			y = 200;
			p2++;
			if(p1Bounce>0){
				p1Bounce=0;
				p1Grow=0;
				speed=5;
			}
			dy = -dy;
		}

		else if( y-10 == d && x > c && x < (c+100))
		{
			dy = -dy;
			if(p1Bounce<=49){
				p1Bounce++;
			}
			if(p1Bounce % 5 == 0){
				p1Grow++;
			}
		}
		else if( y+20 == b && x > a && x < (a+100))
		{
			dy = -dy;
			if(p2Bounce<=49){
				p2Bounce++;
			}
			if(p2Bounce % 5 == 0){
				p2Grow++;
				speed=+5;
			}

		}


		else if(y > 450)
		{
			System.out.println("Player 2 = Game Over || Stop It Now");
			p1++;
			if(p2Bounce>0){
				p2Bounce=0;
				p2Grow=0;
				speed=5;
			}
			y = 300;
			dy = -dy;

		}
	}

	public boolean isFocusable() 
	{
		return true;
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Pin Ball");

		Toolkit tool = frame.getToolkit();
		Dimension screenSize = tool.getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		frame.setBounds((width/3),(height/4), width, height);
		ImageIcon img = new ImageIcon("frameIcon.GIF");
		frame.setIconImage(img.getImage());
		frame.setSize(375,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Object[] options = {"One Player",
		"Two Players"};
		int n = JOptionPane.showOptionDialog(frame,
				"Choose amount of players",
				"Players",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //do not use a custom Icon
				options,  //the titles of buttons
				options[1]); //default button title
		if(n==JOptionPane.YES_OPTION){
			choise=1;
			
		}else{
			choise=0;
		}


		System.out.println(choise);
		frame.add(new PingPong());

		frame.setVisible(true);

		SetLook.lookAndFeel();
	}

}
class SetLook
{
	public static void lookAndFeel()
	{
		try
		{
			String s = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(s);
		}
		catch(Exception e){}
	}
}
//original code by Deepak Saini

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.util.*;

public class PingPong extends Canvas
{
int x,y,dx,dy;
int a,b,da;
int c,d,dc;
int p1 = 0;
int p2 = 0;
int choise = 0;

public PingPong()
{
enableEvents(java.awt.AWTEvent.KEY_EVENT_MASK);


x = 100;
y = 250;
dx = 5;
dy = 5;

a = 200;
b = 450;
da = 10;

c = 150;
d = 20;
dc = 10;

Timer t = new Timer(true);
t.schedule(new java.util.TimerTask()
    {
    public void run()
        {
        doSomething();
        repaint();
        }
    },0,30);
}

public void opponent(){
	c = x;
}

public void paint(Graphics g)
{
g.setColor(Color.black);
g.fillOval(x,y,20,20);

g.setColor(Color.red);
g.fillRect(a,b, 100,10);

g.setColor(Color.red);
g.fillRect(c,d, 100,10);

g.drawString("Player 2 Score : " + p1 + "Points ", 100, 200);
g.drawString("Player 1 Score : " + p2 + "Points ", 100, 230 );
}


public void processKeyEvent(KeyEvent e)
{
	if(choise == 0){
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
	}else{
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

	      opponent(); 
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
        dy = -dy;
        }
        
        else if( y-10 == d && x > c && x < (c+100) )
        {
        dy = -dy;
        }

        else if( y+20 == b && x > a && x < (a+100) )
        {
        dy = -dy;
        }
        
        else if(y > 440)
        {
        System.out.println("Player 2 = Game Over || Stop It Now");
        p1++;
        y = 300;
        dy = -dy;
        }

        else{}  
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
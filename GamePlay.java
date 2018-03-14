import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
		
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 0;
	private int playerY = 540;
	
	private int monsterposX = 120;
	private int monsterposY = 350;	
	private int monsterXdir = -1;
	private int monsterYdir = -2;	
	
	public GamePlay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this); 
		timer.start();
	}
	
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 20, 540);
		g.fillRect(0, 0,760, 20);
		g.fillRect(760, 0, 20, 600);
		g.fillRect(0, 540, 800, 20);
		
		//the hero
		g.setColor(Color.blue);
		g.fillRect(playerX, playerY, 20, 20);

		//the monster
		g.setColor(Color.yellow);
		g.fillRect(monsterposX, monsterposY, 20, 20);
		
		g.dispose();
	}
	
	@Override
	// edge interaction with monster
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {	
			monsterposX += monsterXdir;
			monsterposY += monsterYdir;
			
			//border Right
			if(monsterposX < 10)
				monsterXdir = -monsterXdir;
			//border Left
			if(monsterposX > 763) 
				monsterXdir = -monsterXdir;
			//border Top
			if(monsterposY < 10) 
				monsterYdir = -monsterYdir;
			//border Down
			if(monsterposY > 539) 
				monsterYdir = -monsterYdir;
		}
		repaint();
	}

	@Override
	// Hero's movement
	public void keyPressed(KeyEvent arg0) {
		
		// Map limit
		// Right
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX>=760)
				playerX=760;
			else
				moveRight();
		} 
		// Left
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <=0 )
				playerX=0;
			else
				moveLeft();
		}
		// Down
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			if(playerY >=540 )
				playerY=540;
			else
				moveDown();
		}
		// Top
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			if(playerY <=0 )
				playerY=0;
			else 
				moveUp();
		}
	}

	// Movement
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	public void moveLeft() {
		play = true;
		playerX-=20;
	}
	public void moveDown() {
		play = true;
		playerY+=20;
	}
	public void moveUp() {
		play = true;
		playerY-=20;
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}

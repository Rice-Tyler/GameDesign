package edu.virginia.lab1test;


import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game implements MouseListener{
	Integer xPos = 450;
	Integer yPos = 450;
	Integer health = 10;
	Integer speed = 1;
	GameClock clock = new GameClock();
	

	/* Create a sprite object for our game. We'll use mario */
	Sprite mario = new Sprite("Mario", "Mario.png");
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 1000, 1000);
		this.getMainFrame().addMouseListener(this);
		
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		if(pressedKeys.contains(37)) xPos+=-speed;
		if(pressedKeys.contains(39)) xPos+=speed;
		if(pressedKeys.contains(38)) yPos +=-speed;
		if(pressedKeys.contains(40)) yPos +=speed;
		super.update(pressedKeys);
		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null) mario.update(pressedKeys);
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		Integer fontSize = 30;
		
	    double ctime = this.clock.getElapsedTime()/1000;
	    String time = String.format("%.2f",ctime);
		
		
		g.translate(xPos, yPos);
		super.draw(g);
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(mario != null) mario.draw(g);
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		g.translate(-xPos, -yPos);
		g.setColor(Color.GRAY);
		g.fillRect(10, 10, 160, 60);
		g.setColor(Color.red);
		g.fillRect(15, 15, (15*health), 50);
		g.drawString(time, 900, 50);
		g.setColor(Color.BLACK);
		g.drawString(health.toString(), 85, 50);
		
		g.setColor(Color.GREEN);
//		g.setFont(font);
		
		if(ctime >=30) {
			g.drawString("mario Wins", 500, 500);
			this.pause();
		}
		if(health <= 0) {
			g.drawString("Clicker Wins", 500, 500);
			this.pause();
		}
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabOneGame game = new LabOneGame();
		game.start();

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Integer xt = e.getX();
		Integer yt = e.getY();
		Integer xdist = xt-xPos;
		Integer ydist = yt-yPos;
		if(xdist<120 && xdist > 20 && ydist<160 && ydist > 20) {
			health-=1;
			speed++;
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
//		System.out.println('y');
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

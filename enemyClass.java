// HelloWorld.java

import javax.swing.*;
import javax.imageio.*;

import java.awt.*;
import java.io.*;
import java.util.*;

class Enemy {
	
	private double x, y, dx, dy;
	private Image [] zombie;
	private int current;
	private boolean right = true;
	private boolean moving = true;
	private boolean firstPass = true;
	private boolean rebound = false;
	/* time between flips in the animation */
	private static final double FLIP_TIME = 0.050;
	/* time since last flip */
	private double timer = 0.0;
	private int zomType;
	private boolean alive = true;
	private int health = 100;

	public Enemy(int i) {
		zomType = i;
		System.out.println(i);
		Random r = new Random(zomType);
		x = r.nextFloat( ) * 500;
		y = r.nextFloat( ) * 500;
		

		//make sure zombies start at the top
		if (firstPass == true){
			y = 500;
			firstPass = false;
		}

		/* these are now pixels / second instead of pixels per frame */
		dx = r.nextFloat( )*50 - 25;
		dy = r.nextFloat( )*50 + 100;


		/* load all the images */
		try {
			
			zombie = new Image[5];
			if (zomType == 0){
				zombie[0]  = ImageIO.read(new File("z1.png"));
				zombie[1]  = ImageIO.read(new File("z2.png"));
				zombie[2]  = ImageIO.read(new File("z3.png"));
				zombie[3]  = ImageIO.read(new File("z4.png"));
			}
			if (zomType == 1){
				zombie[0]  = ImageIO.read(new File("a1.png"));
				zombie[1]  = ImageIO.read(new File("a2.png"));
				zombie[2]  = ImageIO.read(new File("a3.png"));
				zombie[3]  = ImageIO.read(new File("a4.png"));
			}
			if (zomType == 2){
				zombie[0]  = ImageIO.read(new File("b1.png"));
				zombie[1]  = ImageIO.read(new File("b2.png"));
				zombie[2]  = ImageIO.read(new File("b3.png"));
				zombie[3]  = ImageIO.read(new File("b4.png"));
			}
			if (zomType == 3){
				zombie[0]  = ImageIO.read(new File("c1.png"));
				zombie[1]  = ImageIO.read(new File("c2.png"));
				zombie[2]  = ImageIO.read(new File("c3.png"));
				zombie[3]  = ImageIO.read(new File("c4.png"));
			}
			if (zomType == 4){
				zombie[0]  = ImageIO.read(new File("d1.png"));
				zombie[1]  = ImageIO.read(new File("d2.png"));
				zombie[2]  = ImageIO.read(new File("d3.png"));
				zombie[3]  = ImageIO.read(new File("d4.png"));
			}
		} catch(Exception e) {
			
			zombie = null;
		}
		current = 0;
	}

	public void draw(Graphics g) {
		/* add to the index if going left */
		int add = 0;
		if(!right) add = 2;

		/* draw zombie on the screen */
		g.drawImage(zombie[current + add], (int)x, (int)y, null);
	}

	public void update(double dt) {
		if (rebound == false){
			x += (dx * dt);
			y += (dy * dt);
		}
		else{
			x -= (dx * dt);
			y += (dy * dt);
		}

		if(y < 0) y = 500;
		if(y > 500) y = 0;
		if(x < 0){
			rebound = true;
			//x = 500;
		}
		if(x > 500){
			rebound = true;
			//x = 0;
		}


		/* update animation */
		if(moving) {
			timer += dt;
			if(timer > FLIP_TIME) {
				timer = 0;
				current = (current + 1) % 4;
			}
		}
	}
}


class GameWorld extends JComponent {
	private ArrayList<Enemy> EnemyFactory;
	private long elapsed;

	public GameWorld( ) {
		elapsed = new Date( ).getTime( );
		EnemyFactory = new ArrayList<Enemy>( );

		
		//update the i < # for different number of enemies
		for(int i = 0; i < 5; i++) {
			EnemyFactory.add(new Enemy(i));
		}
	}
	



	public void paintComponent(Graphics g) {
		/* set the color to light blue */
		g.setColor(new Color(100, 150, 255));
		g.fillRect(0, 0, 500, 500);



		for(Enemy f : EnemyFactory) {
			f.draw(g);
		}

		/* now update */
		long time_now = new Date( ).getTime( );

		//update the divided number higher to go slower
		double seconds = (time_now - elapsed) / 5000.0f;
		//System.out.println(seconds);
		for(Enemy f : EnemyFactory) {
			f.update(seconds);
			elapsed = time_now;
		}

		/* force an update */
		revalidate( );
		repaint( );
		/* sleep for 1/20th of a second */
		try {
			Thread.sleep(50);
		} catch(InterruptedException e) {
			Thread.currentThread( ).interrupt( );
		}
	}
}


public class enemyClass {
	public static void main(String args[]) {
		// create and set up the window.
		JFrame frame = new JFrame("EnemyFactory!");

		// make the program close when the window closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add the GameWorld component
		frame.add(new GameWorld( ));

		// display the window.
		frame.setSize(500, 500);
		frame.setVisible(true);
	}    
}

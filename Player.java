import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Player implements KeyListener, MouseListener, MouseMotionListener {
    
	final int WEST = 0;
	final int SOUTH = 1;
	final int EAST = 2;
    
	private int xMouse = 300, yMouse = 300;
    
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private Turret[] turrets;
    
	public Player(ArrayList<Enemy> pList) {
        
		enemyList = pList;
        
		turrets = new Turret[3];
		
	private int xMouse = 300, yMouse = 300;

	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private Turret[] turrets;

	public Player(ArrayList<Enemy> pList) {

		enemyList = pList;

		turrets = new Turret[3];

		turrets[WEST] = new Turret(100,100,40,1,new TurretReader("turret1.png"),new Shot(100+17,100+22,6,70));
		turrets[SOUTH] = new Turret(300,500,1,3,new TurretReader("turret3.png"),new Shot(300+17,500+22,12,5));
		turrets[EAST] = new Turret(500,100,15,2,new TurretReader("turret2.png"),new Shot(500+17,100+22,6,30));
		turrets[SOUTH].setActive(true);
	}

	public void upkeep(double dt, Graphics g) {
		for(int index = 0; index < turrets.length; index++) {
			turrets[index].upkeep(dt,xMouse,yMouse,enemyList,g);
		}
	}


	public void setEnemyArray(ArrayList<Enemy> el) {
		enemyList = el;
	}

	//MouseListener methods
    
	public void mousePressed(MouseEvent m) {
        
		int xLoc = m.getX();
		int yLoc = m.getY();
        
		for(int index = 0; index < turrets.length; index++) {
			if(turrets[index].isActivated()) {
				turrets[index].fire(xLoc,yLoc);
			}
		}
	}
	public void mouseReleased(MouseEvent m){}
	public void mouseClicked(MouseEvent m){}
	public void mouseEntered(MouseEvent m){}
	public void mouseExited(MouseEvent m){}
    
	//KeyListener methods
    
	public void keyTyped(KeyEvent k) {
		char key = k.getKeyChar();
        
		switch(key) {
			case 'a':
				turrets[WEST].setActive(true);
				turrets[SOUTH].setActive(false);
				turrets[EAST].setActive(false);
                break;
			case 's':
				turrets[WEST].setActive(false);
				turrets[SOUTH].setActive(true);
				turrets[EAST].setActive(false);
                break;
			case 'd':
				turrets[WEST].setActive(false);
				turrets[SOUTH].setActive(false);
				turrets[EAST].setActive(true);
                break;
			default:
		}
	}
	public void keyPressed(KeyEvent k){}
	public void keyReleased(KeyEvent k){}
    
	//MouseMotionListener methods

	public void mouseMoved(MouseEvent m){
		xMouse = m.getX();
		yMouse = m.getY();
		//System.out.println(xMouse + ","+yMouse);
	}
	public void mouseDragged(MouseEvent m) {
		xMouse = m.getX();
		yMouse = m.getY();
	}

	public void mouseMoved(MouseEvent m){
		xMouse = m.getX();
		yMouse = m.getY();
		//System.out.println(xMouse + ","+yMouse);
	}
	public void mouseDragged(MouseEvent m) {
		xMouse = m.getX();
		yMouse = m.getY();
	}

}

import java.awt.*;

public class AnimatedBar {

	double max;
	double current;
	int size;
	Color barColor;

	public AnimatedBar(int pMax, int pSize, Color fillColor) {
		max = pMax;
		current = pMax;
		size = pSize;
		barColor = fillColor;
	}

	public void draw(int xLoc, int yLoc, Graphics g) {
		int height = size / 6 + 1;
		g.setColor(Color.BLACK);
		g.drawRect(xLoc - size - 1, yLoc - height/2 - 1, size * 2 + 3, height + 2);
		g.setColor(barColor);
		double percentage = current/max;
		if(percentage > 1) {
			percentage = 1;
		}
		g.fillRect(xLoc - size, yLoc - height/2, (int)((size * 2 + 1) * percentage)+1, height+1);
	}

	public void update(double pCurrent) {
		current = pCurrent;
	}

}

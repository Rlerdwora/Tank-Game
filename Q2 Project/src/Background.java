import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background{
	
	//image related variables
	private Tile[][] b = new Tile[14][7];

	public Background() {
		for(int x = 0; x < 14; x ++) {
			for(int y = 0; y < 7; y ++) {
				b[x][y] = new Tile(84 * x, 1 + 84 * y, "grass");
			}
		}
	}
	
	public Background(int i) {
		for(int x = 0; x < 14; x ++) {
			for(int y = 0; y < 7; y ++) {
				b[x][y] = new Tile(84 * x, 1 + 84 * y, "grass");
			}
		}
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		for(int i = 0; i < 14; i ++) {
			for(int j = 0; j < 7; j ++) {
				b[i][j].paint(g);
			}
		}
		
		int x = 0, y = 1, xCount = 0, yCount = 0;
		
		while(xCount < 14) {
			x = 0;
			y = 1;
			while(yCount < 7) {
				g.drawRect(x + 84 * xCount, y + 84 * yCount, 84, 84);
				yCount ++;
			}
			yCount = 0;
			xCount ++;
		}
	}
}

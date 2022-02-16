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
				b[x][y] = new Tile(x, y, "grass");
			}
		}
	}
	
	public Background(int i) {
		
		//grassfield stage
		if(i == 0) {
			for(int x = 0; x < 14; x ++) {
				for(int y = 0; y < 7; y ++) {
					b[x][y] = new Tile(x, y, "grass");
				}
			}
		}
		//desert stage
		else if(i == 1) {
			for(int x = 0; x < 14; x ++) {
				for(int y = 0; y < 7; y ++) {
					b[x][y] = new Tile(x, y, "sand");
				}
			}
		}
		//warehouse stage
		else if(i == 2) {
			for(int x = 0; x < 14; x ++) {
				for(int y = 0; y < 7; y ++) {
					b[x][y] = new Tile(x, y, "stone");
				}
			}
		}
		//beach stage
		else if(i == 3) {
			for(int x = 0; x < 14; x ++) {
				for(int y = 0; y < 7; y ++) {
					b[x][y] = new Tile(x, y, "sand");
				}
			}
			
			for(int x = 8; x < 14; x ++) {
				for(int y = 0; y < 7; y ++) {
					b[x][y] = new Tile(x, y, "grass");
				}
			}
			b[6][0] = new Tile(6,0, "grass");
			b[7][0] = new Tile(7,0, "grass");
			b[7][1] = new Tile(7,1, "grass");
			b[7][5] = new Tile(7,5, "grass");
		}
		//airbase stage
		else if(i == 4) {
			for(int x = 0; x < 14; x ++) {
				for(int y = 0; y < 7; y ++) {
					b[x][y] = new Tile(x, y, "grass");
				}
			}
			for(int x = 0; x < 4; x ++) {
				for(int y = 0; y < 3; y ++) {
					b[x][y] = new Tile(x, y, "stone");
				}
			}
			for(int x = 10; x < 14; x ++) {
				for(int y = 4; y < 7; y ++) {
					b[x][y] = new Tile(x, y, "stone");
				}
			}
			for(int x = 6; x <= 7; x ++) {
				for(int y = 0; y < 7; y ++) {
					b[x][y] = new Tile(x, y, "sand");
				}
			}
			b[4][1] = new Tile(4, 1, "sand");
			b[5][1] = new Tile(5, 1, "sand");
			b[8][5] = new Tile(8, 5, "sand");
			b[9][5] = new Tile(9, 5, "sand");
		}else if(i == 5) {
			
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

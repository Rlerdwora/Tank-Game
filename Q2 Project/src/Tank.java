import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Tank{
	
	//image related variables
	int x, y, xv, yv, number, xPos = 4, yPos = 6;
	String horizontal, vertical;
	private Image img; 	
	private AffineTransform tx;

	public Tank(int x, int y, int number, String horizontal) {
		this.x = x;
		this.y = y;
		this.number = number;
		this.horizontal = horizontal;
		vertical = "";
		
		img = getImage("/imgs/tank" + this.number + this.horizontal + vertical + ".png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}

	public void moveRight() {
		xv = 3;
	}
	
	public void moveLeft() {
		xv = -3;
	}
	
	public void moveUp() {
		yv = -3;
	}
	
	public void moveDown() {
		yv = 3;
	}
	
	public void stopMoveRight() {
		if(xv > 0) {
			xv = 0;
		}
	}
	
	public void stopMoveLeft() {
		if(xv < 0) {
			xv = 0;
		}
	}
	
	public void stopMoveUp() {
		if(yv < 0) {
			yv = 0;
		}
	}
	
	public void stopMoveDown() {
		if(yv > 0) {
			yv = 0;
		}
	}
	
	
	/* update variables here */
	private void update() {
		x += xv;
		y += yv;
		
		if(yv == 0 && xv < 0) {
			horizontal = "Left";
			vertical = "";
			xPos = 4;
			yPos = 6;
		}else if(yv == 0 && xv > 0) {
			horizontal = "Right";
			vertical = "";
			xPos = 6;
			yPos = 6;
		}else if(yv < 0 && xv == 0) {
			horizontal = "";
			vertical = "Up";
			xPos = 7;
			yPos = 5;
		}else if(yv > 0 && xv == 0) {
			horizontal = "";
			vertical = "Down";
			xPos = 5;
			yPos = 6;
		}else if(yv < 0 && xv < 0) {
			horizontal = "Left";
			vertical = "Up";
			xv = -2;
			yv = -2;
			xPos = -13;
			yPos = -13;
		}else if(yv < 0 && xv > 0) {
			horizontal = "Right";
			vertical = "Up";
			xv = 2;
			yv = -2;
			xPos = -9;
			yPos = -13;
		}else if(yv > 0 && xv < 0) {
			horizontal = "Left";
			vertical = "Down";
			xv = -2;
			yv = 2;
			xPos = -14;
			yPos = -10;
		}else if(yv > 0 && xv > 0) {
			horizontal = "Right";
			vertical = "Down";
			xv = 2;
			yv = 2;
			xPos = -10;
			yPos = -10;
		}
		
		img = getImage("/imgs/tank" + this.number + this.horizontal + vertical + ".png");
		init(x + xPos, y + yPos);
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		//call update to update the actualy picture location
		update();
		
		
		
		
		g2.drawImage(img, tx, null);
		g.drawRect(x, y, 94, 94);
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75, .75);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Tank.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

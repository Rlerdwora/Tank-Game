import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class CrateParticle{
	
	//image related variables
	int x, y, xv, yv, timer;
	private Image img; 	
	private AffineTransform tx;

	public CrateParticle(int x, int y) {
		this.x = x + (int)(Math.random() * 21) - 10;
		this.y = y + (int)(Math.random() * 21) - 10;
		xv = (int)(Math.random() * 5) - 2;
		yv = (int)(Math.random() * 5) - 2;
		timer = 40;
		
		img = getImage("/imgs/crateParticle.gif");
		init(this.x, this.y);
	}
	
	public void update() {
		x += xv;
		y += yv;
		
		init(x, y);
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		if(timer > 0) {
			Graphics2D g2 = (Graphics2D) g;
			
			update();
			
			g2.drawImage(img, tx, null);
			
			timer --;
		}
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75, .75);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = CrateParticle.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Shell{
	
	//image related variables
	private int x, y, xv, yv;
	private Image img; 	
	private AffineTransform tx;

	public Shell(int x, int y, String horizontal, String vertical) {
		this.x = x;
		this.y = y;
		
		if(horizontal == "Right" && vertical == "Up") {
			xv = 7;
			yv = -7;
		}else if(horizontal == "Right" && vertical == "Down") {
			xv = 7;
			yv = 7;
		}else if(horizontal == "Right" && vertical == "") {
			xv = 10;
			yv = 0;
		}else if(horizontal == "Left" && vertical == "Up") {
			xv = -10;
			yv = 0;
		}else if(horizontal == "Left" && vertical == "Down") {
			xv = -7;
			yv = 7;
		}else if(horizontal == "Left" && vertical == "") {
			xv = -10;
			yv = 0;
		}else if(horizontal == "" && vertical == "Up") {
			xv = 0;
			yv = -10;
		}else if(horizontal == "" && vertical == "Down") {
			xv = 0;
			yv = 10;
		}
		
		img = getImage("/imgs/shell" + horizontal + vertical +".gif");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	
	/* update variables here */
	private void update() {
		y += yv;
		x += xv;
		
		init(x, y);
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(img, tx, null);
		g.drawRect(x, y, 10, 10);
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75, .75);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Shell.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

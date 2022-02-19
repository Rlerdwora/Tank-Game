import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Wall{
	
	//image related variables
	int x, y;
	private Image img; 	
	private AffineTransform tx;

	public Wall(int x, int y, String side) {
		this.x = x * 84;
		this.y = y * 84 + 1;
		img = getImage("/imgs/wall" + side + ".png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(this.x , this.y);
	}
	
	public void checkCollision(Tank tank) {
		
	}
	
	public void checkCollision(Shell shell) {
		if(shell.getX() + 44 > x && shell.getX() + 30 < x + 84
		&& shell.getY() + 42 > y && shell.getY() + 32 < y + 84) {
			shell.disappear();
		}
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(img, tx, null);
		g.drawRect(x, y, 84, 84);
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75, .75);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Wall.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Icon{
	
	//image related variables
	private int x, y, number;
	private Image img; 	
	private AffineTransform tx;

	public Icon(int x, int y, int number) {
		this.x = x;
		this.y = y;
		this.number = number;
		
		img = getImage("/imgs/tank" + number + "Right.png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}

	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(img, tx, null);
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75, .75);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Icon.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

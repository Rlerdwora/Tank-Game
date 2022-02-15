import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Crate{
	
	//image related variables
	private int x, y, height, length;
	private boolean exploded;
	private ArrayList<CrateParticle> particles = new ArrayList<CrateParticle>();
	private Image img; 	
	private AffineTransform tx;

	public Crate(int x, int y) {
		this.x = x;
		this.y = y;
		length = 84;
		height = 84;
		exploded = false;
		img = getImage("/imgs/crateA.png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Crate(int x, int y, String side) {
		this.x = x;
		this.y = y;
		if(side.equals("H")) {
			length = 168;
			height = 84;
		}else {
			length = 84;
			height = 168;
		}
		exploded = false;
		img = getImage("/imgs/crateB" + side + ".png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public void checkCollision(Tank x) {
		
	}
	
	public void checkCollision(Shell shell) {
		if(shell.getX() + 55 > x && shell.getX() < x + length
		&& shell.getY() + 52 > y && shell.getY() < y + height
		&& exploded == false) {
			shell.disappear();
			exploded = true;
			img = getImage("/imgs/crateDebris.png");
			particles.add(new CrateParticle(x, y));
		}
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(img, tx, null);
		g.drawRect(x, y, length, height);
		

	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75, .75);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Crate.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}

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
		this.x = x;
		this.y = y;
		img = getImage("/imgs/wall" + side + ".png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public void checkCollision(Tank tank) {
		//tank is to the left of crate
		if(tank.getX() + 89 > x && tank.getX() < x && tank.getY() + 88 > y && tank.getY() + 6 < y + 84) {
			tank.setX(tank.getX() - 4);
		}
		
		//tank is to the right of crate
		if(tank.getX() + 5 < x + 84 && tank.getX() > x && tank.getY() + 88 > y && tank.getY() + 6 < y + 84) {
			tank.setX(tank.getX() + 4);
		}
		
		//tank is above crate
		if(tank.getX() + 89 > x && tank.getX() + 6 < x + 84 && tank.getY() + 88 > y && tank.getY() + 80 < y) {
			tank.setY(tank.getY() - 4);
			if(tank.getX() + 89 > x && tank.getX() < x) {
				tank.setX(tank.getX() + 4);
			}else if(tank.getX() + 5 < x + 84 && tank.getX() > x) {
				tank.setX(tank.getX() - 4);
			}
		}
		
		//tank is below crate
		if(tank.getX() + 89 > x && tank.getX() + 6 < x + 84 && tank.getY() + 6 > y && tank.getY() + 6 < y + 84) {
			tank.setY(tank.getY() + 4);
			if(tank.getX() + 89 > x && tank.getX() < x) {
				tank.setX(tank.getX() + 4);
			}else if(tank.getX() + 5 < x + 84 && tank.getX() > x) {
				tank.setX(tank.getX() - 4);
			}
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

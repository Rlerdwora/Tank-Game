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
		this.x = x * 84;
		this.y = y * 84 + 1;
		length = 84;
		height = 84;
		exploded = false;
		img = getImage("/imgs/crateA.png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(this.x, this.y);
	}
	
	public Crate(int x, int y, String side) {
		this.x = x * 84;
		this.y = y * 84 + 1;
		if(side.equals("H")) {
			length = 168;
			height = 84;
		}
		if(side.equals("V")) {
			length = 84;
			height = 168;
		}
		exploded = false;
		img = getImage("/imgs/crateB" + side + ".png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(this.x, this.y);
	}
	
	public void checkCollision(Tank tank) {
		if(exploded == false) {
			//tank is to the left of crate
			if(tank.getX() + 89 > x && tank.getX() + 89 < x + length && tank.getY() + 90 > y && tank.getY() + 6 < y + height && tank.getY() + 86 > y && tank.getY() + 10 < y + height) {
				if(tank.getXV() == 4) {
					tank.setX(tank.getX() - 4);
				}else if(tank.getXV() == 3) {
					tank.setX(tank.getX() - 3);
				}
			}
			
			//tank is to the right of crate
			if(tank.getX() + 5 < x + length && tank.getX() + 5 > x && tank.getY() + 90 > y && tank.getY() + 6 < y + height && tank.getY() + 86 > y && tank.getY() + 10 < y + height) {
				if(tank.getXV() == -4) {
					tank.setX(tank.getX() + 4);
				}else if(tank.getXV() == -3) {
					tank.setX(tank.getX() + 3);
				}
			}
			
			//tank is above crate
			if(tank.getX() + 89 > x && tank.getX() + 6 < x + length && tank.getY() + 90 > y && tank.getY() + 86 <= y && tank.getY() + 90 < y + height) {
				if(tank.getYV() == 4) {
					tank.setY(tank.getY() - 4);
				}else if(tank.getYV() == 3) {
					tank.setY(tank.getY() - 3);
				}
			}
			
			//tank is below crate
			if(tank.getX() + 89 > x && tank.getX() + 6 < x + length && tank.getY() + 6 < y + height && tank.getY() + 10 >= y + height && tank.getY() + 6 > y) {
				if(tank.getYV() == -4) {
					tank.setY(tank.getY() + 4);
				}else if(tank.getYV() == -3) {
					tank.setY(tank.getY() + 3);
				}
			}
		}
	}
	
	public void checkCollision(Shell shell) {
		if(shell.getX() + 55 > x && shell.getX() + 30 < x + length
		&& shell.getY() + 52 > y && shell.getY() + 32 < y + height
		&& exploded == false) {
			shell.disappear();
			explode();
		}
	}
	
	public void explode() {
		for(int i = 0; i < 10; i ++) {
			particles.add(new CrateParticle(x + length / 2 - 20, y + height / 2 - 20));
		}
		exploded = true;
		if(height == 84 && length == 84) {
			img = getImage("/imgs/crateADebris.png");
		}else if(height == 168 && length == 84) {
			img = getImage("/imgs/crateBVDebris.png");
		}else {
			img = getImage("/imgs/crateBHDebris.png");			
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(img, tx, null);
		
		for(CrateParticle particle : particles) {
			particle.paint(g);
		}

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

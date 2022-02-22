import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.color.*;

public class Tank{
	
	//image related variables
	private int x, y, xv, yv, number, xPos = 4, yPos = 6, shellTimer, startPosX, startPosY, invinTimer; 
	private double deathTimer, respawnTimer;
	private String horizontal, vertical, action;
	private boolean exploded, control, invincible;
	private Image img; 	
	private AffineTransform tx;

	public Tank(int x, int y, int number, String horizontal) {
		this.x = x * 84 - 5;
		this.y = y * 84 - 5;
		startPosX = x * 84 - 5;
		startPosY = y * 84 - 5;
		this.number = number;
		this.horizontal = horizontal;
		control = true;
		invincible = false;
		exploded = false;
		deathTimer = 0;
		respawnTimer = 300;
		vertical = "";
		action = "Respawn";
		
		img = getImage("/imgs/tank" + this.number + action + this.horizontal + vertical + ".png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public void checkCollision(Shell shell) {
		if(shell.getX() + 55 > x + 5 && shell.getX() + 30 < x + 89
		&& shell.getY() + 52 > y + 6 && shell.getY() + 32 < y + 90) {
			shell.disappear();
			explode();
		}
	}
	
	public void explode() {
		if(invincible == false) {
			Frame.subtractLife(number);
			exploded = true;
			deathTimer = 40;
			shellTimer = 0;
			action = "Debris";
			horizontal = "";
			vertical = "";
			x += 3;
			y += 3;
			xv = 0;
			yv = 0;
		}
	}
	
	public void respawn() {
		exploded = false;
		respawnTimer = 300;
		x = startPosX;
		y = startPosY;
		vertical = "";
		action = "Respawn";
		if(number == 1) {
			horizontal = "Right";
		}else {
			horizontal = "Left";
		}
	}

	public void moveRight() {
		if(control == true) {
			if(yv == 0) {
				xv = 4;
			}else {
				xv = 3;
			}
		}
	}
	
	public void moveLeft() {
		if(control == true) {
			if(yv == 0) {
				xv = -4;
			}else {
				xv = -3;
			}
		}
	}
	
	public void moveUp() {
		if(control == true) {
			if(xv == 0) {
				yv = -4;
			}else {
				yv = -3;
			}
		}
	}
	
	public void moveDown() {
		if(control == true) {
			if(xv == 0) {
				yv = 4;
			}else {
				yv = 3;
			}
		}
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
	
	public void fire() {
		shellTimer = 80;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getXV() {
		return xv;
	}
	
	public int getYV() {
		return yv;
	}
	
	public String getH() {
		return horizontal;
	}
	
	public String getV() {
		return vertical;
	}
	
	public int getTimer() {
		return shellTimer;
	}
	
	public boolean getControl() {
		return control;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setRespawn(int x, int y) {
		this.x = x * 84 - 5;
		this.y = y * 84 - 5;
		startPosX = x * 84 - 5;
		startPosY = y * 84 - 5;
	}
	
	/* update variables here */
	private void update() {
		x += xv;
		y += yv;
		
		if(x < -5) {
			x = -5;
		}
		if(x > 1088) {
			x = 1088;
		}
		
		if(y < -4) {
			y = -4;
		}
		if(y > 500) {
			y = 500;
		}
		
		if(respawnTimer <= 0 && exploded == false && deathTimer <= 0) {
			action = "";
			
			if(yv == 0 && xv < 0) {
				horizontal = "Left";
				vertical = "";
				xPos = 4;
				yPos = 6;
				xv = -4;
			}else if(yv == 0 && xv > 0) {
				horizontal = "Right";
				vertical = "";
				xPos = 6;
				yPos = 6;
				xv = 4;
			}else if(yv < 0 && xv == 0) {
				horizontal = "";
				vertical = "Up";
				xPos = 7;
				yPos = 5;
				yv = -4;
			}else if(yv > 0 && xv == 0) {
				horizontal = "";
				vertical = "Down";
				xPos = 5;
				yPos = 6;
				yv = 4;
			}else if(yv < 0 && xv < 0) {
				horizontal = "Left";
				vertical = "Up";
				xPos = -13;
				yPos = -13;
				yv = -3;
				xv = -3;
			}else if(yv < 0 && xv > 0) {
				horizontal = "Right";
				vertical = "Up";
				xPos = -9;
				yPos = -13;
				yv = -3;
				xv = 3;
			}else if(yv > 0 && xv < 0) {
				horizontal = "Left";
				vertical = "Down";
				xPos = -14;
				yPos = -10;
				yv = 3;
				xv = -3;
			}else if(yv > 0 && xv > 0) {
				horizontal = "Right";
				vertical = "Down";
				xPos = -10;
				yPos = -10;
				yv = 3;
				xv = 3;
			}
		}
		
		if(shellTimer > 0) {
			shellTimer --;
		}
		
		if(deathTimer > 0) {
			deathTimer --;
			control = false;
			if(deathTimer == 1) {
				respawn();
			}
		}
		
		if(respawnTimer > 0) {
			control = false;
			respawnTimer -= 10;
			if(respawnTimer == 0) {
				invinTimer = 40;
				respawnTimer --;
			}
		}else {
			if(exploded == false && deathTimer <= 0) {
				control = true;
			}
		}
		
		if(respawnTimer <= 0 && exploded == false && deathTimer <= 0) {
			control = true;
		}
		
		if(invinTimer > 0) {
			invinTimer --;
			invincible = true;
		}else {
			invincible = false;
		}
		
		img = getImage("/imgs/tank" + number + action + horizontal + vertical + ".png");
		init(x + xPos, y + yPos);
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//call update to update the actualy picture location
		update();
	
		if((exploded == true && deathTimer <= 20 && deathTimer > 0 && deathTimer % 2 == 0)
		|| (invincible == true && invinTimer % 4 == 0)) {
				
		}else {
			g2.drawImage(img, tx, null);
			g.fillRect(x + 8, y, shellTimer, 10);
			//g.drawRect(x + 5, y + 6, 84, 84);
		}		
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75 + respawnTimer / 100, .75 + respawnTimer / 100);
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

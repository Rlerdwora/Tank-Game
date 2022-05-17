import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.Font;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	Tank p1 = new Tank(0,0,1,"Right");
	Tank p2 = new Tank(13,6,2,"Left");
	ArrayList<Background> backgrounds = new ArrayList<Background>();
	static ArrayList<Icon> p1Icons = new ArrayList<Icon>();
	static ArrayList<Icon> p2Icons = new ArrayList<Icon>();
	ArrayList<Shell> p1Shells = new ArrayList<Shell>();
	ArrayList<Shell> p2Shells = new ArrayList<Shell>();
	ArrayList<ArrayList<Crate>> crates = new ArrayList<ArrayList<Crate>>();
	ArrayList<ArrayList<Wall>> walls = new ArrayList<ArrayList<Wall>>();	
	Font font = new Font("font", Font.BOLD, 20);		
	int stageSelect = 0;
	boolean gameStart = false;
	int startTimer;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setFont(font);
		
		if(backgrounds.size() > 0 && walls.size() > 0 && crates.size() > 0) {
			//paint all objects
			backgrounds.get(stageSelect).paint(g);
			
			for(Crate x : crates.get(stageSelect)) {
				x.paint(g);
				x.checkCollision(p1);
				x.checkCollision(p2);
				for(Shell y : p1Shells) {
					x.checkCollision(y);
				}
				for(Shell y : p2Shells) {
					x.checkCollision(y);
				}
			}
			for(Wall x : walls.get(stageSelect)) {
				x.paint(g);
				x.checkCollision(p1);
				x.checkCollision(p2);
				for(Shell y : p1Shells) {
					x.checkCollision(y);
				}
				for(Shell y : p2Shells) {
					x.checkCollision(y);
				}
			}
			
			for(Shell x : p1Shells) {
				x.paint(g);
				p2.checkCollision(x);
			}
			for(Shell x : p2Shells) {
				x.paint(g);
				p1.checkCollision(x);
			}
			for(Icon x : p1Icons) {
				x.paint(g);
			}
			for(Icon x : p2Icons) {
				x.paint(g);
			}
			
			if(gameStart == true && startTimer <= 0) {
				p1.paint(g);
				p2.paint(g);
			}else {
				p1.preview(g);
				p2.preview(g);
			}
		}
		
		//start timer countdown
		if(startTimer > 0) {
			startTimer --;
			if(startTimer <= 120 && startTimer > 90) {
				g.drawString("3", 583, 300);
			}else if(startTimer <= 90 && startTimer > 60) {
				g.drawString("2", 583, 300);
			}else if(startTimer <= 60 && startTimer > 30) {
				g.drawString("1", 583, 300);
			}else if(startTimer <= 30 && startTimer > 0) {
				g.drawString("Go!", 574, 300);
				if(startTimer == 30) {				}
			}
		}
		
		if(p1Icons.size() == 0 && p2Icons.size() > 0) {
			g.drawString("Player 2 Wins!", 573, 300);
		}else if(p2Icons.size() == 0 && p1Icons.size() > 0) {
			g.drawString("Player 1 Wins!", 573, 300);
		}else if(p2Icons.size() == 0 && p1Icons.size() == 0){
			g.drawString("Tie!", 572, 300);
		}
		
		//messages
		if(gameStart == false) {
			g.drawString("Switch Stage with Select, Select Stage with Enter", 360, 300);
		}
		if(p1Icons.size() == 0 || p2Icons.size() == 0) {
			g.drawString("Go Back to Stage Selection with Enter", 444, 350);
		}
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Tank Game");
		f.setSize(new Dimension(1191, 727));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		for(int i = 0; i < 3; i ++) {
			p1Icons.add(i, new Icon(10 + 100 * i, 600, 1));
			p2Icons.add(i, new Icon(1085 + -100 * i, 600, 2));
		}
		
		for(int i = 0; i < 5; i ++) {
			backgrounds.add(new Background(i));
		}
		
		for(int i = 0; i < 5; i ++) {
			walls.add(new ArrayList<Wall>());
			crates.add(new ArrayList<Crate>());
		}
		
		//grass field stage obstacles
		
		//desert stage obstacles
		
		//warehouse stage obstacles
		crates.get(2).add(new Crate(5,2, "V"));
		crates.get(2).add(new Crate(8,3, "V"));
		for(int i = 3; i < 11; i ++) {
			walls.get(2).add(new Wall(i, 1, "H"));
			walls.get(2).add(new Wall(i, 5, "H"));
		}
		for(int i = 1; i < 6; i ++) {
			if(i != 3) {
				walls.get(2).add(new Wall(2, i, "V"));
				walls.get(2).add(new Wall(11, i, "V"));
			}
		}
		
		//beach stage obstacles
		
		//airbase stage obstacles
		crates.get(4).add(new Crate(3, 0, "V"));
		crates.get(4).add(new Crate(10, 5, "V"));
		crates.get(4).add(new Crate(2, 4, "V"));
		crates.get(4).add(new Crate(11, 1, "V"));
		crates.get(4).add(new Crate(5, 4));
		crates.get(4).add(new Crate(8, 2));
		
		for(int i = 0; i < 4; i ++) {
			walls.get(4).add(new Wall(i,2, "H"));
			walls.get(4).add(new Wall(i + 10,4, "H"));
		}
	}
	
	public static void subtractLife(int x) {
		if(x == 1) {
			p1Icons.remove(p1Icons.size() - 1);
		}else {
			p2Icons.remove(p2Icons.size() - 1);
		}
	}
	
	public void stageUp() {
		if(stageSelect == 4) {
			stageSelect = 0;
		}else {
			stageSelect ++;
		}
		
		switch(stageSelect) {
		case 0:
			
			break;	
		
		case 1:
			break;
				
		case 2:
			p1.setRespawn(0, 3);
			p2.setRespawn(13, 3);
			break;
			
		case 3:
			break;
	
		case 4:
			p1.setRespawn(0, 0);
			p2.setRespawn(13, 6);
			break;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		
		if(gameStart == true) {
			if(arg0.getKeyCode() == 87) {
				p1.moveUp();
			}
			
			if(arg0.getKeyCode() == 65) {
				p1.moveLeft();
			}
			
			if(arg0.getKeyCode() == 83) {
				p1.moveDown();
			}
			
			if(arg0.getKeyCode() == 68) {
				p1.moveRight();
			}
			
			if(arg0.getKeyCode() == 70 && p1.getTimer() == 0 && p1.getControl() == true) {
				p1.fire();
				Shell s = new Shell(p1.getX(), p1.getY(), p1.getH(), p1.getV());
				p1Shells.add(s);
			}
			
			if(arg0.getKeyCode() == 37) {
				p2.moveLeft();
			}
			
			if(arg0.getKeyCode() == 38) {
				p2.moveUp();
			}
			
			if(arg0.getKeyCode() == 39) {
				p2.moveRight();
			}
			
			if(arg0.getKeyCode() == 40) {
				p2.moveDown();
			}
			
			if(arg0.getKeyCode() == 46 && p2.getTimer() == 0 && p2.getControl() == true) {
				p2.fire();
				Shell s = new Shell(p2.getX(), p2.getY(), p2.getH(), p2.getV());
				p2Shells.add(s);
			}
			
			if(arg0.getKeyCode() == 10) {
				if(p1Icons.size() == 0 || p2Icons.size() == 0) {
					gameStart = false;
					while(p1Icons.size() > 0) {
						p1Icons.remove(0);
					}
					while(p2Icons.size() > 0) {
						p2Icons.remove(0);
					}
					for(int i = 0; i < 3; i ++) {
						p1Icons.add(i, new Icon(10 + 100 * i, 600, 1));
						p2Icons.add(i, new Icon(1085 + -100 * i, 600, 2));
					}
				}
			}
		}else {
			if(arg0.getKeyCode() == 16) {
				stageUp();
			}
			
			if(arg0.getKeyCode() == 10) {
				gameStart = true;
				startTimer = 120;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 87) {
			p1.stopMoveUp();
		}
		
		if(arg0.getKeyCode() == 65) {
			p1.stopMoveLeft();
		}
		
		if(arg0.getKeyCode() == 83) {
			p1.stopMoveDown();
		}
		
		if(arg0.getKeyCode() == 68) {
			p1.stopMoveRight();
		}
		
		if(arg0.getKeyCode() == 37) {
			p2.stopMoveLeft();
		}
		
		if(arg0.getKeyCode() == 38) {
			p2.stopMoveUp();
		}
		
		if(arg0.getKeyCode() == 39) {
			p2.stopMoveRight();
		}
		
		if(arg0.getKeyCode() == 40) {
			p2.stopMoveDown();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stu)b
		
	}

}

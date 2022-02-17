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

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	Background b = new Background(4);
	Tank p1 = new Tank(-5,0,1,"Right");
	Tank p2 = new Tank(1000,0,2,"Left");
	ArrayList<Background> backgrounds = new ArrayList<Background>();
	ArrayList<Icon> p1Icons = new ArrayList<Icon>();
	ArrayList<Icon> p2Icons = new ArrayList<Icon>();
	ArrayList<Shell> p1Shells = new ArrayList<Shell>();
	ArrayList<Shell> p2Shells = new ArrayList<Shell>();
	ArrayList<Crate> crates = new ArrayList<Crate>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		b.paint(g);
		for(Crate x : crates) {
			x.paint(g);
			x.checkCollision(p1);
			x.checkCollision(p2);
			for(Shell y : p1Shells) {
				x.checkCollision(y);
			}
		}
		for(Shell x : p1Shells) {
			x.paint(g);
		}
		for(Icon x : p1Icons) {
			x.paint(g);
		}
		for(Icon x : p2Icons) {
			x.paint(g);
		}
		
		p1.paint(g);		
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
		
		crates.add(new Crate(4,5,"H"));
	}
	
	public void subtractLife(int x) {
		if(x == 1) {
			p1Icons.remove(p1Icons.size() - 1);
		}else {
			p2Icons.remove(p2Icons.size() - 1);
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
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

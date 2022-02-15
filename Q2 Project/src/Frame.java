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
	
	Background b = new Background();
	Tank p1 = new Tank(0,0,1,"Right");
	ArrayList<Shell> p1Shells = new ArrayList<Shell>();
	ArrayList<Crate> crates = new ArrayList<Crate>();
	Crate c = new Crate(84,85);
	int stage;
	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		b.paint(g);
		for(Crate x : crates) {
			x.paint(g);
		}
		for(Shell x : p1Shells) {
			x.paint(g);
			for(Crate y : crates) {
				y.checkCollision(x);
			}
		}
		p1.paint(g);
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Tank Game");
		f.setSize(new Dimension(1191, 627));
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
		
		crates.add(c);
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
		
		if(arg0.getKeyCode() == 70 && p1.getTimer() == 0) {
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

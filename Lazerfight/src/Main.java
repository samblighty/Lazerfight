import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {

	/* Define the boundaries of the game */
	final int WIDTH = 600;
	final int HEIGHT = 600;
	final int SKIP = 2;
	final int SIZE = 1;
	final int SHOTSPEED = 2;
	final int SCORELIMIT = 10;

	/* Assign timer for movement and image for player one and player two */
	Timer time;
	Timer timeshot;
	Image pone;
	Image ptwo;
	Image fone;
	Image ftwo;
	Main init;
	int onescore = 0;
	int twoscore = 0;

	/* Initial starting co-ords for the players */
	int onex = 300;
	final int oney = 500;
	int twox = 300;
	final int twoy = 100;

	/* Initial starting movement for the players */
	boolean oneleft = false;
	boolean oneright = false;
	boolean twoleft = false;
	boolean tworight = false;

	/* Create main frame */
	JFrame gui = new JFrame();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JLabel playerone = new JLabel();
	JLabel playertwo = new JLabel();

	ArrayList<projectile> p1 = new ArrayList<projectile>();
	ArrayList<projectile> p2 = new ArrayList<projectile>();

	public static void main(String[] args) {
		/* Main class object */
		Main init = new Main();
		init.start(init);
		System.out.println("Initialized Variables");

	}

	public void start(Main obj) {
		/* Setup the frame */
		gui.setTitle("Lazer Tag");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gui.getContentPane().setLayout(new BorderLayout());
		gui.getContentPane().add(this, BorderLayout.CENTER);

		gui.getContentPane().add(panel2, BorderLayout.SOUTH);
		panel2.setLayout(new FlowLayout());

		gui.getContentPane().add(panel3, BorderLayout.NORTH);
		panel3.setLayout(new FlowLayout());

		panel2.add(playertwo);
		panel3.add(playerone);

		panel2.setOpaque(true);
		panel3.setOpaque(true);

		panel2.setBackground(Color.orange);
		panel3.setBackground(Color.orange);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.black);

		setVisible(true);

		gui.setResizable(false);
		gui.pack();
		gui.setVisible(true);

		/* Key adapter */
		addKeyListener(new KeyPress(obj));
		setFocusable(true);
		System.out.println("Initialized Frame");
		imageadd();
		gameplay(obj);

	}

	public void imageadd() {

		/* Adds icons to the image icons */
		ImageIcon playerone = new ImageIcon("src/one.png");
		pone = playerone.getImage();

		ImageIcon playertwo = new ImageIcon("src/two.png");
		ptwo = playertwo.getImage();

		ImageIcon flameone = new ImageIcon("src/fone.png");
		fone = flameone.getImage();

		ImageIcon flametwo = new ImageIcon("src/ftwo.png");
		ftwo = flametwo.getImage();

		System.out.println("Added Images");

	}

	public void gameplay(Main send) {
		/*
		 * Creates a new util timer that performs the timekeeper run method every SKIP
		 * ms
		 */
		time = new Timer();
		time.scheduleAtFixedRate(new timekeeper(send), 100, SKIP);

		timeshot = new Timer();
		timeshot.scheduleAtFixedRate(new shotkeeper(send), 100, SHOTSPEED);

		System.out.println("Started Timer");

	}

	public void playerMovement() {
		/* The players move by the amount of SIZE left or right */
		if (oneleft) {
			onex -= SIZE;
		}

		if (oneright) {
			onex += SIZE;
		}

		if (twoleft) {
			twox -= SIZE;
		}

		if (tworight) {
			twox += SIZE;
		}
	}

	public void collisionCheckBoundary() {
		/* Check for boundary touches, not projectile */
		if (onex >= WIDTH - SIZE - 30) {
			oneright = false;
			System.out.println("One: Hit right");
		}

		if (onex < SIZE) {
			oneleft = false;
			System.out.println("One: Hit left");
		}

		if (twox >= WIDTH - SIZE - 30) {
			tworight = false;
			System.out.println("Two: Hit right");
		}

		if (twox < SIZE) {
			twoleft = false;
			System.out.println("Two: Hit left");
		}

	}
	/* add projectiles into array list */

	public void poneshot(Main obj) {
		p1.add(new projectile(1, obj));
	}

	public void ptwoshot(Main obj) {
		p2.add(new projectile(2, obj));
	}

	public void ponemove() {

		for (int x = 0; x < p1.size(); x++) {
			p1.get(x).y += 1;
		}
	}

	public void ptwomove() {
		for (int x = 0; x < p2.size(); x++) {
			p2.get(x).y -= 1;
		}

	}

	public void shotclean() {
		for (int x = 0; x < p1.size(); x++) {
			if (p1.get(x).y > 600) {
				p1.remove(x);
			}
		}

		for (int x = 0; x < p2.size(); x++) {
			if (p2.get(x).y < 0) {
				p2.remove(x);
			}
		}

	}

	public void shotcollision() {
		for (int x = 0; x < p1.size(); x++) {
			if ((p1.get(x).y == oney) && (p1.get(x).x < (onex + 20)) && (p1.get(x).x > (onex - 10))) {
				onescore += 1;
				p1.remove(x);
			}
		}

		for (int x = 0; x < p2.size(); x++) {
			if ((p2.get(x).y == twoy) && (p2.get(x).x < (twox + 20)) && (p2.get(x).x > (twox - 10))) {
				twoscore += 1;
				p2.remove(x);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		/* Draws each player */
		if (onescore >= SCORELIMIT || twoscore >= SCORELIMIT) {
			String end = null;
			if (onescore >= SCORELIMIT) {

				 end = "Game Over, P1 Wins!";

			} else if (twoscore >= SCORELIMIT) {

				 end = "Game Over, P2 Wins!";

			}

			Font design = new Font("Helvetica", Font.BOLD, 30);
			g.setColor(Color.white);
			g.setFont(design);
			g.drawString(end, 160, 300);
			
			time.cancel();
			timeshot.cancel();
			pone = null;
			ptwo = null;
			fone = null;
			ftwo = null;
			
			
			

		} else {
			g.drawImage(pone, onex, oney, this);

			g.drawImage(ptwo, twox, twoy, this);

			g.drawImage(ftwo, (twox + 10), (twoy - 20), this);

			g.drawImage(fone, (onex + 10), (oney + 26), this);

			for (int x = 0; x < p2.size(); x++) {
				g.drawImage(p2.get(x).shot, p2.get(x).x + 10, p2.get(x).y, this);
			}

			for (int x = 0; x < p1.size(); x++) {
				g.drawImage(p1.get(x).shot, p1.get(x).x + 10, p1.get(x).y + 21, this);
			}
		}

	}

}

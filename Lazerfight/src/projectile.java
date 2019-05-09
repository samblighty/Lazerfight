
import java.awt.Image;

import javax.swing.ImageIcon;

public class projectile {

	Main mobj;
	int p, x, y;
	Image shot;

	public projectile(int player, Main mainobj) {
		p = player;
		mobj = mainobj;
		ImageIcon proj = new ImageIcon("src/red.png");
		shot = proj.getImage();

		if (p == 1) {
			x = mainobj.twox;
			y = mainobj.twoy;

			System.out.println("One set " + x + " " + y);
		}

		if (p == 2) {
			x = mainobj.onex;
			y = mainobj.oney;

			System.out.println("Two set " + x + " " + y);
		}
	}

}

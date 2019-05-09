import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyPress extends KeyAdapter {

	Main mobj;

	/* Use a hashset to have a collection of all the keys that are in use */
	/* Iterate over the hashset using a fancy loop */

	final Set<Integer> keys = new HashSet<Integer>();

	KeyPress(Main obj) {
		mobj = obj;
		System.out.println("Object initiated for Key Press");
	}

	@Override
	public void keyPressed(KeyEvent e) {

		keys.add(e.getKeyCode());

		for (Integer k : keys) {

			if ((k == KeyEvent.VK_LEFT)) {
				mobj.oneleft = true;
				mobj.oneright = false;

			}

			if ((k == KeyEvent.VK_RIGHT)) {
				mobj.oneright = true;
				mobj.oneleft = false;

			}

			if ((k == KeyEvent.VK_D)) {
				mobj.tworight = true;
				mobj.twoleft = false;

			}

			if ((k == KeyEvent.VK_A)) {
				mobj.tworight = false;
				mobj.twoleft = true;

			}

			if ((k == KeyEvent.VK_W)) {

				mobj.poneshot(mobj);

			}

			if ((k == KeyEvent.VK_UP)) {

				mobj.ptwoshot(mobj);

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int k = e.getKeyCode();

		if ((k == KeyEvent.VK_LEFT)) {
			mobj.oneleft = false;

		}

		if ((k == KeyEvent.VK_RIGHT)) {
			mobj.oneright = false;

		}

		if ((k == KeyEvent.VK_D)) {
			mobj.tworight = false;

		}

		if ((k == KeyEvent.VK_A)) {

			mobj.twoleft = false;

		}

		keys.remove(e.getKeyCode());
	}

}

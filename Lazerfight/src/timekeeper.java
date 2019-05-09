import java.util.TimerTask;

public class timekeeper extends TimerTask {

	Main mobj;

	timekeeper() {
		System.out.println("No main class object");
	}

	timekeeper(Main init) {
		mobj = init;
	}

	public void run() {

		mobj.collisionCheckBoundary();
		mobj.playerMovement();

		mobj.repaint();

	}

}

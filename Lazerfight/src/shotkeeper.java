import java.util.TimerTask;

public class shotkeeper extends TimerTask {

	Main mobj;

	shotkeeper() {
		System.out.println("No main class object");
	}

	shotkeeper(Main init) {
		mobj = init;
	}

	@Override
	public void run() {

		mobj.ponemove();
		mobj.ptwomove();
		mobj.shotclean();
		mobj.shotcollision();
		mobj.playerone.setText("PLAYER 1 SCORE: " + mobj.onescore);
		mobj.playertwo.setText("PLAYER 2 SCORE: " + mobj.twoscore);

	}

}

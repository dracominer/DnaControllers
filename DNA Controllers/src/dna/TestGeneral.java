package dna;

import javax.swing.JFrame;

import dna.controllers.DNAAbstractController;
import dna.controllers.DNALoadControllers;
import dna.controllers.DPadDir;
import dna.controllers.XBoxPad;

/**
 * this is a main testing class for showing that the system works. I will change
 * it from time to time to test different features.
 */
@SuppressWarnings("serial")
public class TestGeneral extends JFrame implements Runnable {

	public static void main(String[] args) {
		print("Game started");
		new TestGeneral().start();
	}

	protected DNAAbstractController gamepad;

	public TestGeneral() {
		super("Gamepad test");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void start() {
		new Thread(this).start();
	}

	public void run() {
		setUpController();
		if (gamepad == null) {
			System.err.println("No controllers found!");
			return;
		}
		while (true) {
			gamepad.poll();
			runTest();
		}
	}

	protected void setUpController() {
		gamepad = new DNAAbstractController(DNALoadControllers.getAllGamePads().get(0), true);
	}

	protected void runTest() {
		for (int i = 0; i < gamepad.getRegisteredButtons(); i++) {
			if (i == XBoxPad.BTN_DPad) continue;
			if (gamepad.getButton(i)) print("Btn_" + i + " is pressed");
		}
		for (int i = 0; i < gamepad.getRegisteredAxes(); i++) {
			if (Math.abs(gamepad.getAxis(i)) > 0.25f) print("Axis_" + i + " is in use [" + gamepad.getAxis(i) + "]");
		}
		DPadDir dir = gamepad.getDPadDir(XBoxPad.BTN_DPad);
		if (!dir.equals(DPadDir.None)) {
			print("DPad: " + dir.toString());
		}
	}

	private static void print(String x) {
		System.out.println(x);
	}

}

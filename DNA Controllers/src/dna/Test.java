package dna;

import javax.swing.JFrame;

import dna.controllers.DNALoadControllers;
import dna.controllers.DPadDir;
import dna.controllers.XBoxPad;

@SuppressWarnings("serial")
public class Test extends JFrame implements Runnable {

	public static void main(String[] args) {
		print("Game started");
		new Test().start();
	}

	private XBoxPad gamepad;

	public Test() {
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
		while (true) {
			gamepad.poll();
			for (int i = 0; i < gamepad.getRegisteredButtons(); i++) {
				if (i == XBoxPad.BTN_DPad) continue;
				if (gamepad.getButton(i)) print("Btn_" + i + " is pressed");
			}
			for (int i = 0; i < gamepad.getRegisteredAxes(); i++) {
				if (Math.abs(gamepad.getAxis(i)) > 0.25f) print("Axis_" + i + " is in use");
			}
			DPadDir dir = gamepad.getDPadDir(XBoxPad.BTN_DPad);
			if (!dir.equals(DPadDir.None)) {
				print("DPad: " + dir.toString());
			}
		}
	}

	private void setUpController() {
		gamepad = DNALoadControllers.loadOneXboxController();
		print("Using gamepad: " + gamepad.getName());
	}

	private static void print(String x) {
		System.out.println(x);
	}

}

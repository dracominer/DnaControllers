package dna.controllers;

import net.java.games.input.Controller;

/**
 * after this class is initialized, it can handle all input from an xbox gamepad
 * */
public class XBoxPad extends DNAController {

	public static final int BTN_A = 0;
	public static final int BTN_B = 1;
	public static final int BTN_X = 2;
	public static final int BTN_Y = 3;

	public static final int BTN_BumperLeft = 4;
	public static final int BTN_BumperRight = 5;

	public static final int BTN_View = 6;
	public static final int BTN_Menu = 7;

	public static final int BTN_DPad = 10;

	public static final int Axis_LeftY = 0;
	public static final int Axis_LeftX = 1;
	public static final int Axis_RightY = 2;
	public static final int Axis_RightX = 3;

	public XBoxPad(Controller controller) {
		super(controller);
	}

	public boolean getBtnA() {
		return getButton(BTN_A);
	}

	public boolean getBtnB() {
		return getButton(BTN_B);
	}

	public boolean getBtnX() {
		return getButton(BTN_X);
	}

	public boolean getBtnY() {
		return getButton(BTN_Y);
	}

	public boolean getBumperLeft() {
		return getButton(BTN_BumperLeft);
	}

	public boolean getBumperRight() {
		return getButton(BTN_BumperRight);
	}

}

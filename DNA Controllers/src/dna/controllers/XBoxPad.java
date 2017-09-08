package dna.controllers;

import java.util.ArrayList;
import java.util.List;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.Controller.PortType;
import net.java.games.input.Controller.Type;
import net.java.games.input.EventQueue;
import net.java.games.input.Rumbler;

/**
 * after this class is initialized, it can handle all input from an xbox gamepad
 * */
public class XBoxPad {

	private Controller controller;
	private Component[] buttons;
	private Component[] axes;

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
		this.controller = controller;
		Component[] sub = getComponents();
		List<Component> btns = new ArrayList<Component>();
		List<Component> axs = new ArrayList<Component>();
		for (int i = 0; i < sub.length; i++) {
			Component part = sub[i];
			System.out.println("Found xbox part: " + part.getName() + " [" + part.getIdentifier() + "] (analog? " + part.isAnalog() + ")");
			if (!part.isAnalog()) {
				btns.add(part);
			} else {
				axs.add(part);
			}
		}
		buttons = new Component[btns.size()];
		axes = new Component[axs.size()];
		System.out.println("Found " + buttons.length + " buttons and " + axes.length + " axes");
		for (int i = 0; i < btns.size(); i++) {
			buttons[i] = btns.get(i);
		}
		for (int i = 0; i < axs.size(); i++) {
			axes[i] = axs.get(i);
		}
	}

	public int getRegisteredButtons() {
		return buttons.length;
	}

	public int getRegisteredAxes() {
		return axes.length;
	}

	public boolean getButton(int id) {
		return getButtonRaw(id) == 1.0f;
	}

	public DPadDir getDPadDir(int id) {
		float val = getButtonRaw(id);
		if (val <= 0) return DPadDir.None;
		if (val <= 0.125) return DPadDir.NW;
		if (val <= 0.25) return DPadDir.N;
		if (val <= 0.375) return DPadDir.NE;
		if (val <= 0.5) return DPadDir.E;
		if (val <= 0.625) return DPadDir.SE;
		if (val <= 0.75) return DPadDir.S;
		if (val <= 0.875) return DPadDir.SW;
		if (val <= 1.0) return DPadDir.W;
		return DPadDir.None;
	}

	public float getButtonRaw(int id) {
		if (id < 0 || id >= buttons.length) return 0.0f;
		return buttons[id].getPollData();
	}

	public float getAxis(int id) {
		if (id < 0 || id >= axes.length) return 0.0f;
		return axes[id].getPollData();
	}

	public Controller getController() {
		return controller;
	}

	public Controller[] getControllers() {
		return controller.getControllers();
	}

	public Type getType() {
		return controller.getType();
	}

	public Component[] getComponents() {
		return controller.getComponents();
	}

	public Component getComponent(Identifier id) {
		return controller.getComponent(id);
	}

	public Rumbler[] getRumblers() {
		return controller.getRumblers();
	}

	public boolean poll() {
		return controller.poll();
	}

	public void setEventQueueSize(int size) {
		controller.setEventQueueSize(size);
	}

	public EventQueue getEventQueue() {
		return controller.getEventQueue();
	}

	public PortType getPortType() {
		return controller.getPortType();
	}

	public int getPortNumber() {
		return controller.getPortNumber();
	}

	public String getName() {
		return controller.getName();
	}

}

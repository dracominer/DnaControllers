package dna.controllers;

import java.util.ArrayList;
import java.util.List;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 * a single class that handles all controller loading.
 * It makes it easier to add in controller support.
 * */
public class DNALoadControllers {

	/**
	 * finds all active controllers that are of the defined type. This can find keybaords, mice, gamepads, and even joysticks or head trackers.
	 * */
	public static List<Controller> getAllControllersOfType(net.java.games.input.Controller.Type type) {
		List<Controller> controllers = new ArrayList<Controller>();
		Controller[] list = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int i = 0; i < list.length; i++) {
			Controller c = list[i];
			if (c == null) {
				continue;
			}
			if (c.getType().equals(type)) {
				controllers.add(c);
			}
		}
		return controllers;
	}

	/**
	 * returns all active controllers that are marked as a gamepad
	 * */
	public static List<Controller> getAllGamePads() {
		return getAllControllersOfType(net.java.games.input.Controller.Type.GAMEPAD);
	}

	public static List<XBoxPad> getAllAvailableXboxControllers() {
		List<Controller> controllers = getAllGamePads();
		List<XBoxPad> gamepads = new ArrayList<XBoxPad>();
		for (int i = 0; i < controllers.size(); i++) {
			Controller c = controllers.get(i);
			if (c.getName().toLowerCase().indexOf("xbox") != -1) gamepads.add(new XBoxPad(c));
		}
		return gamepads;
	}

	public static XBoxPad loadOneXboxController() {
		List<XBoxPad> possible = getAllAvailableXboxControllers();
		if (possible.size() <= 0) return null;
		return possible.get(0);
	}

}

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

	public static List<XBoxPad> getAllAvailableXboxControllers() {
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		List<XBoxPad> gamepads = new ArrayList<XBoxPad>();
		for (int i = 0; i < controllers.length; i++) {
			Controller c = controllers[i];
			if (c == null) {
				continue;
			}
			if (c.getType().equals(net.java.games.input.Controller.Type.GAMEPAD)) {
				if (c.getName().toLowerCase().indexOf("xbox") != -1) gamepads.add(new XBoxPad(c));
			}
		}
		return gamepads;
	}

	public static XBoxPad loadOneXboxController() {
		List<XBoxPad> possible = getAllAvailableXboxControllers();
		if (possible.size() <= 0) return null;
		return possible.get(0);
	}

}

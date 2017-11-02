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
 * this class is the main abstraction point for JInput controllers.
 * It has ease of access methods and stores appropriate variables.
 * Any child classes will provide further abstraction such as the 
 * XBox pad class which has a method for checking if the X button is pressed.
 * */
public class DNAController {

	protected Controller controller;

	protected Component[] analogs;
	protected Component[] digitals;
	protected Rumbler[] rumblers;
	protected float[] rumbleIntensities;

	public DNAController(Controller c, boolean printComponent) {
		controller = c;
		rumblers = controller.getRumblers();
		List<Component> a = new ArrayList<Component>();
		List<Component> d = new ArrayList<Component>();
		for (Component co : controller.getComponents()) {
			if (co == null) continue;
			if (co.isAnalog()) {
				a.add(co);
			} else {
				d.add(co);
			}
		}
		analogs = new Component[a.size()];
		digitals = new Component[d.size()];
		for (int i = 0; i < analogs.length; i++) {
			analogs[i] = a.get(i);
		}
		for (int i = 0; i < digitals.length; i++) {
			digitals[i] = d.get(i);
		}
		rumbleIntensities = new float[rumblers.length];
		if (printComponent) printData();
	}

	public DNAController(Controller c) {
		this(c, false);
	}

	public int getRegisteredButtons() {
		return digitals.length;
	}

	public int getRegisteredAxes() {
		return analogs.length;
	}

	public void poll() {
		controller.poll();
	}

	public void update() {
		poll();
	}

	public float getAnalog(int id) {
		if (id < 0 || id >= analogs.length) return 0.0f;
		return analogs[id].getPollData();
	}

	public float getDigital(int id) {
		if (id < 0 || id >= digitals.length) return 0.0f;
		return digitals[id].getPollData();
	}

	public void rumble(int id, float intensity) {
		if (id < 0 || id >= rumblers.length) return;
		rumbleIntensities[id] = intensity;
		rumblers[id].rumble(intensity);
	}

	public float getRumbleIntensity(int id) {
		if (id < 0 || id >= rumblers.length) return 0.0f;
		return rumbleIntensities[id];
	}

	public boolean isRumbling(int id) {
		return getRumbleIntensity(id) > 0.0f;
	}

	public float getButtonRaw(int id) {
		return getDigital(id);
	}

	public boolean getButton(int id) {
		return getButtonRaw(id) > 0.5f;
	}

	public float getAxis(int id) {
		return getAnalog(id);
	}

	public DPadDir getDPadDir(int id) {
		float v = getButtonRaw(id);
		if (v <= 0.0f) return DPadDir.None;
		if (v <= 0.125f) return DPadDir.W;
		if (v <= 0.25f) return DPadDir.NW;
		if (v <= 0.375f) return DPadDir.N;
		if (v <= 0.5f) return DPadDir.NE;
		if (v <= 0.625f) return DPadDir.E;
		if (v <= 0.75f) return DPadDir.SE;
		if (v <= 0.875f) return DPadDir.S;
		if (v <= 1.0f) return DPadDir.SW;
		return DPadDir.None;
	}

	public void printData() {
		System.out.println("Controller: " + getName());
		for (Component c : analogs) {
			System.out.println("\t" + c.getName() + "(Analog) : " + c.getIdentifier().getName() + " (" + c.getPollData() + ")");
		}
		for (Component c : digitals) {
			System.out.println("\t" + c.getName() + "(Digital) : " + c.getIdentifier().getName() + " (" + c.getPollData() + ")");
		}
		for (int i = 0; i < rumblers.length; i++) {

			if (rumblers[i] == null) {
				System.out.println("Null Rumbler!!!");
				continue;
			}
			System.out.println("\t" + rumblers[i].getAxisName() + "(Rumbler) : (" + getRumbleIntensity(i));
		}

	}

	// Delagate Methods

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

package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.stream.Collectors;

public class MachineInterpreter {

	private Machine machine;
	private State currentState;

	public void run(Machine m) {
		machine = m;
		currentState = machine.getInitialState();
	}

	public State getCurrentState() {
		return currentState;
	}

	public void processEvent(String event) {
		currentState = currentState.getTransitionByEvent(event).getTarget();
		System.out.println(".." + currentState.getName() + ".." +currentState.getTransitions().stream().map(Transition::getEvent).collect(Collectors.toList()));
	}

	public int getInteger(String string) {
		System.out.println(machine.getIntegers());
		return machine.getIntegers().get(string);
	}

}

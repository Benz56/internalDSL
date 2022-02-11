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
        for (final Transition transition : currentState.getTransitions().stream().filter(transition -> transition.getEvent().toString().equalsIgnoreCase(event)).collect(Collectors.toList())) {
            Integer value = transition.isConditional() ? machine.getIntegers().get(transition.getConditionVariableName().toString()) : null;
            if (!transition.isConditional() || (value != null && (transition.isConditionEqual() && value.equals(transition.getConditionComparedValue()) ||
                    transition.isConditionGreaterThan() && value > transition.getConditionComparedValue() ||
                    transition.isConditionLessThan() && value < transition.getConditionComparedValue()))) {
                if (transition.hasSetOperation())
                    machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                if (transition.hasIncrementOperation() || transition.hasDecrementOperation())
                    machine.getIntegers().merge(transition.getOperationVariableName().toString(), transition.hasIncrementOperation() ? 1 : -1, Integer::sum);
                currentState = transition.getTarget();
                break;
            }
        }
    }

    public int getInteger(String string) {
        return machine.getIntegers().get(string);
    }
}

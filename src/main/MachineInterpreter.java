package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.List;
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
        final List<Transition> transitions = currentState.getTransitions().stream().filter(transition -> transition.getEvent().toString().equalsIgnoreCase(event)).collect(Collectors.toList());
        for (final Transition transition : transitions) {
            boolean executed = false;
            if (transition.isConditional()) {
                Integer value = machine.getIntegers().get(transition.getConditionVariableName().toString());
                if (value != null) {
                    if (transition.isConditionEqual()) {
                        if (value.equals(transition.getConditionComparedValue())) {
                            currentState = transition.getTarget();
                            if (transition.hasSetOperation()) {
                                machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                            }
                            if (transition.hasIncrementOperation() || transition.hasDecrementOperation()) {
                                machine.getIntegers().merge(transition.getOperationVariableName().toString(), transition.hasIncrementOperation() ? 1 : -1, Integer::sum);
                            }
                            executed = true;
                        }
                    } else if (transition.isConditionGreaterThan()) {
                        if (value > transition.getConditionComparedValue()) {
                            currentState = transition.getTarget();
                            if (transition.hasSetOperation()) {
                                machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                            }
                            if (transition.hasIncrementOperation() || transition.hasDecrementOperation()) {
                                machine.getIntegers().merge(transition.getOperationVariableName().toString(), transition.hasIncrementOperation() ? 1 : -1, Integer::sum);
                            }
                            executed = true;
                        }
                    } else if (transition.isConditionLessThan()) {
                        if (value < transition.getConditionComparedValue()) {
                            currentState = transition.getTarget();
                            if (transition.hasSetOperation()) {
                                machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                            }
                            if (transition.hasIncrementOperation() || transition.hasDecrementOperation()) {
                                machine.getIntegers().merge(transition.getOperationVariableName().toString(), transition.hasIncrementOperation() ? 1 : -1, Integer::sum);
                            }
                            executed = true;
                        }
                    }
                }
                if (executed) break;
            } else {
                if (transition.hasSetOperation()) {
                    machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                }
                if (transition.hasIncrementOperation() || transition.hasDecrementOperation()) {
                    machine.getIntegers().merge(transition.getOperationVariableName().toString(), transition.hasIncrementOperation() ? 1 : -1, Integer::sum);
                }
                System.out.println(transition.hasIncrementOperation());
                currentState = transition.getTarget();
                break;
            }
        }
    }

    public int getInteger(String string) {
        return machine.getIntegers().get(string);
    }

}

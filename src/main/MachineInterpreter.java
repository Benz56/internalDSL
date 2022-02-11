package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.Optional;
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
        Optional.ofNullable(currentState.getTransitionByEvent(event)).ifPresent(transition -> {
            if (transition.hasIncrementOperation() || transition.hasDecrementOperation()) {
                machine.getIntegers().merge(transition.getOperationVariableName().toString(), transition.hasIncrementOperation() ? 1 : -1, Integer::sum);
            }
            if (transition.isConditional()) {
                Optional.ofNullable(machine.getIntegers().get(transition.getConditionVariableName().toString())).ifPresent(value -> {
                    if (transition.isConditionEqual()) {
                        if (value.equals(transition.getConditionComparedValue())) {
                            currentState = transition.getTarget();
                        }
                        if (transition.hasSetOperation()) {
                            machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                        }
                    } else if (transition.isConditionGreaterThan()) {
                        if (value > transition.getConditionComparedValue()) {
                            currentState = transition.getTarget();
                        }
                        if (transition.hasSetOperation()) {
                            machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                        }
                    } else if (transition.isConditionLessThan()) {
                        if (value < transition.getConditionComparedValue()) {
                            currentState = transition.getTarget();
                        }
                        if (transition.hasSetOperation()) {
                            machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                        }
                    }
                });
            } else {
                if (transition.hasSetOperation()) {
                    machine.getIntegers().put(transition.getOperationVariableName().toString(), transition.getOperationValue());
                }
                currentState = transition.getTarget();
            }
        });
    }

    public int getInteger(String string) {
        System.out.println(machine.getIntegers());
        return machine.getIntegers().get(string);
    }

}

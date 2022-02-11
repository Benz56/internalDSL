package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.*;

public class StateMachine {

    private final List<State> states = new ArrayList<>();
    private final Map<String, Integer> integers = new HashMap<>();
    private State initialState;
    private String currentTransitionEvent;
    private State currentState;

    public Machine build() {
        return new Machine(states, initialState, integers);
    }

    public StateMachine state(String name) {
        states.stream().filter(state -> state.getName().toString().equalsIgnoreCase(name)).findFirst().ifPresentOrElse(
                state -> this.currentState = state,
                () -> states.add(this.currentState = new State(name))
        );
        return this;
    }

    public StateMachine initial() {
        initialState = states.get(states.size() - 1); // The latest added state is the initial.
        return this;
    }

    public StateMachine when(String event) {
        currentTransitionEvent = event;
        return this;
    }

    public StateMachine to(String target) {
        states.stream().filter(state -> state.getName().toString().equalsIgnoreCase(target)).findFirst().ifPresentOrElse(
                state -> currentState.getTransitions().add(new Transition(currentTransitionEvent, state)),
                () -> { // If the target state does not exist we create it. This avoids more unnecessary intermediate object value creation caching.
                    final State state = new State(target);
                    states.add(state);
                    currentState.getTransitions().add(new Transition(currentTransitionEvent, state));
                }
        );
        return this;
    }

    public StateMachine integer(String string) {
        integers.put(string, 0);
        return this;
    }

    public StateMachine set(String variable, int value) {
        Optional.ofNullable(currentState.getTransitions().get(currentState.getTransitions().size() - 1)).ifPresent(transition -> {
            transition.setOperationVariableName(variable);
            transition.setOperationValue(value);
            transition.setSetOperation(true);
        });
        return this;
    }

    public StateMachine increment(String variable) {
        Optional.ofNullable(currentState.getTransitions().get(currentState.getTransitions().size() - 1)).ifPresent(transition -> {
            transition.setOperationVariableName(variable);
            transition.setIncrementOperation(true);
        });
        return this;
    }

    public StateMachine decrement(String variable) {
        Optional.ofNullable(currentState.getTransitions().get(currentState.getTransitions().size() - 1)).ifPresent(transition -> {
            transition.setOperationVariableName(variable);
            transition.setDecrementOperation(true);
        });
        return this;
    }

    public StateMachine ifEquals(String variable, int value) {
        Optional.ofNullable(currentState.getTransitions().get(currentState.getTransitions().size() - 1)).ifPresent(transition -> {
            transition.setConditionVariableName(variable);
            transition.setConditionComparedValue(value);
            transition.setConditionEqual(true);
        });
        return this;
    }

    public StateMachine ifGreaterThan(String variable, int value) {
        Optional.ofNullable(currentState.getTransitions().get(currentState.getTransitions().size() - 1)).ifPresent(transition -> {
            transition.setConditionVariableName(variable);
            transition.setConditionComparedValue(value);
            transition.setConditionGreaterThan(true);
        });
        return this;
    }

    public StateMachine ifLessThan(String variable, int value) {
        Optional.ofNullable(currentState.getTransitions().get(currentState.getTransitions().size() - 1)).ifPresent(transition -> {
            transition.setConditionVariableName(variable);
            transition.setConditionComparedValue(value);
            transition.setConditionLessThan(true);
        });
        return this;
    }
}

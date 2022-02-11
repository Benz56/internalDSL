package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                () -> {
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

    public StateMachine set(String string, int i) {
        integers.put(string, i);
        return this;
    }

    public StateMachine increment(String string) {
        integers.merge(string, 1, Integer::sum);
        return this;
    }

    public StateMachine decrement(String string) {
        integers.merge(string, -1, Integer::sum);
        return this;
    }

    public StateMachine ifEquals(String string, int i) {
        // TODO Auto-generated method stub
        return this;
    }

    public StateMachine ifGreaterThan(String string, int i) {
        // TODO Auto-generated method stub
        return this;
    }

    public StateMachine ifLessThan(String string, int i) {
        // TODO Auto-generated method stub
        return this;
    }

}

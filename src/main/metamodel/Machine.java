package main.metamodel;

import java.util.List;
import java.util.Map;

public class Machine {

    private final List<State> states;
    private final State initialState;
    private final Map<String, Integer> integers;

    public Machine(final List<State> states, final State initialState, final Map<String, Integer> integers) {
        this.states = states;
        this.initialState = initialState;
        this.integers = integers;
    }

    public List<State> getStates() {
        return states;
    }

    public State getInitialState() {
        return initialState;
    }

    public State getState(String string) {
        return states.stream().filter(state -> state.getName().toString().equalsIgnoreCase(string)).findFirst().orElse(null);
    }

    public int numberOfIntegers() {
        return integers.keySet().size();
    }

    public boolean hasInteger(String string) {
        return integers.containsKey(string);
    }

    // Manually added for convenience.
    public Map<String, Integer> getIntegers() {
        return integers;
    }
}

package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {

    private final List<Transition> transitions = new ArrayList<>();
    private final String name;

    public State(String name) {
        this.name = name;
    }

    public Object getName() {
        return name;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public Transition getTransitionByEvent(String string) {
        return transitions.stream().filter(transition -> transition.getEvent().toString().equalsIgnoreCase(string)).findFirst().orElse(null);
    }

}

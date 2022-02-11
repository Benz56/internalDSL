package main.metamodel;

public class Transition {

	private final String event;
	private final State targetState;

	public Transition(final String event, final State targetState) {
		this.event = event;
		this.targetState = targetState;
	}

	public Object getEvent() {
		return event;
	}

	public State getTarget() {
		return targetState;
	}

	public boolean hasSetOperation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasIncrementOperation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasDecrementOperation() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getOperationVariableName() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isConditional() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getConditionVariableName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getConditionComparedValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isConditionEqual() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isConditionGreaterThan() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isConditionLessThan() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasOperation() {
		// TODO Auto-generated method stub
		return false;
	}

}

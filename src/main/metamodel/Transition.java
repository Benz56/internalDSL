package main.metamodel;

public class Transition {

    private final String event;
    private final State targetState;
    private boolean incrementOperation;
    private boolean decrementOperation;
    private boolean setOperation;
    private Object operationVariableName;
    private Object conditionVariableName;
    private Integer operationValue;
    private boolean conditionEqual;
    private Integer conditionComparedValue;
    private boolean conditionGreaterThan;
    private boolean conditionLessThan;

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
        return setOperation;
    }

    public boolean hasIncrementOperation() {
        return incrementOperation;
    }

    public boolean hasDecrementOperation() {
        return decrementOperation;
    }

    public Object getOperationVariableName() {
        return operationVariableName;
    }

    public boolean isConditional() {
        return conditionComparedValue != null; // Could also check if any of the boolean conditions are true.
    }

    public Object getConditionVariableName() {
        return conditionVariableName;
    }

    public Integer getConditionComparedValue() {
        return conditionComparedValue;
    }

    public boolean isConditionEqual() {
        return conditionEqual;
    }

    public boolean isConditionGreaterThan() {
        return conditionGreaterThan;
    }

    public boolean isConditionLessThan() {
        return conditionLessThan;
    }

    public boolean hasOperation() {
        return setOperation || incrementOperation || decrementOperation;
    }

    // Added setter operations for convenience and to avoid an enormous constructor.
    public void setIncrementOperation(final boolean incrementOperation) {
        this.incrementOperation = incrementOperation;
    }

    public void setDecrementOperation(final boolean decrementOperation) {
        this.decrementOperation = decrementOperation;
    }

    public void setSetOperation(final boolean setOperation) {
        this.setOperation = setOperation;
    }

    public void setOperationVariableName(final Object operationVariableName) {
        this.operationVariableName = operationVariableName;
    }

    public void setConditionVariableName(final Object conditionVariableName) {
        this.conditionVariableName = conditionVariableName;
    }

    public void setConditionEqual(final boolean conditionEqual) {
        this.conditionEqual = conditionEqual;
    }

    public void setConditionComparedValue(final Integer conditionComparedValue) {
        this.conditionComparedValue = conditionComparedValue;
    }

    public void setConditionGreaterThan(final boolean conditionGreaterThan) {
        this.conditionGreaterThan = conditionGreaterThan;
    }

    public void setConditionLessThan(final boolean conditionLessThan) {
        this.conditionLessThan = conditionLessThan;
    }

    public void setOperationValue(final int operationValue) {
        this.operationValue = operationValue;
    }

    public Integer getOperationValue() {
        return operationValue;
    }
}

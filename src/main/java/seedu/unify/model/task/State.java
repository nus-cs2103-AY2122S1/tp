package seedu.unify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's State  in the Task list.
 */
public class State {

    public enum ObjectState {
        TODO,
        DONE
    }

    public static final String MESSAGE_CONSTRAINTS = "State should be TODO or DONE";

    private final ObjectState value;

    /**
     * Constructs a {@code State}.
     *
     */
    public State() {
        value = ObjectState.TODO;
    }

    /**
     * Constructs a {@code State}.
     *
     */
    public State(ObjectState state) {
        this.value = state;
    }

    /**
     * Constructs a {@code State}.
     *
     */
    public State(String state) {
        requireNonNull(state);
        checkArgument(isValidState(state), MESSAGE_CONSTRAINTS);
        this.value = ObjectState.valueOf(state);
    }

    /**
     * Returns the object state.
     *
     */
    public ObjectState getState() {
        return value;
    }

    /**
     * Returns true if the object is marked as done.
     *
     */
    public Boolean isMarkedDone() {
        return value == ObjectState.DONE;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidState(String test) {
        try {
            ObjectState.valueOf(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof State // instanceof handles nulls
                && value.equals(((State) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

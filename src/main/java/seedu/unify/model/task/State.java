package seedu.unify.model.task;

/**
 * Represents a Task's State  in the Task list.
 */
public class State {

    public enum ObjectState {
        TODO {
            public String toString() {
                return "TODO";
            }
        },
        DONE {
            public String toString() {
                return "DONE";
            }
        },
        OVERDUE {
            public String toString() {
                return "OVERDUE";
            }
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "State should be todo, done or overdue";

    private ObjectState value;

    /**
     * Constructs a {@code State}.
     *
     */
    public State() {
        value = ObjectState.TODO;
    }

    public State(ObjectState value) {
        this.value = value;
    }

    public State(String string) {
        this.value = ObjectState.valueOf(string);
    }

    public ObjectState getState() {
        return value;
    }

    /**
     * Mark object as todo.
     *
     */
    public void markAsTodo() {
        value = ObjectState.TODO;
    }

    /**
     * Mark object as done.
     *
     */
    public void markAsDone() {
        value = ObjectState.DONE;
    }

    /**
     * Mark object as overdue.
     *
     */
    public void markAsOverdue() {
        value = ObjectState.OVERDUE;
    }

    /**
     * Returns true if the object is marked as todo.
     *
     */
    public Boolean isMarkedTodo() {
        return value == ObjectState.TODO;
    }

    /**
     * Returns true if the object is marked as done.
     *
     */
    public Boolean isMarkedDone() {
        return value == ObjectState.DONE;
    }

    /**
     * Returns true if the object is marked as overdue.
     *
     */
    public Boolean isMarkedOverdue() {
        return value == ObjectState.OVERDUE;
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

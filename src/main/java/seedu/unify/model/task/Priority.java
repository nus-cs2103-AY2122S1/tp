package seedu.unify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.unify.commons.util.AppUtil.checkArgument;

public class Priority implements Comparable<Priority> {

    public enum ObjectPriority {
        LOW(1),
        MEDIUM(2),
        HIGH(3);

        private final int value;

        ObjectPriority(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "Priority should be LOW, MEDIUM or HIGH";

    private ObjectPriority priority;


    /**
     * Constructs a {@code Priority}.
     */
    public Priority() {
        priority = ObjectPriority.LOW;
    }

    /**
     * Constructs a {@code Priority}.
     */
    public Priority(ObjectPriority priority) {
        this.priority = priority;
    }

    /**
     * Constructs a {@code Priority}.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        this.priority = ObjectPriority.valueOf(priority);
    }

    public ObjectPriority getObjectPriority() {
        return priority;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidPriority(String test) {
        try {
            ObjectPriority.valueOf(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public int compareTo(Priority other) {
        return priority.compareTo(other.priority);
    }


    @Override
    public String toString() {
        return priority.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Priority // instanceof handles nulls
            && priority.equals(((Priority) other).priority)); // state check
    }

    @Override
    public int hashCode() {
        return priority.hashCode();
    }
}

package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Application's completion status in InternSHIP.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompletion(String)}
 */
public class Completion {

    public static final String MESSAGE_CONSTRAINTS = "Completion can only be 'Completed' or 'Uncompleted'.";

    public final String value;

    /**
     * Constructs a {@code Completion}.
     *
     * @param status Status of the completion value.
     */
    public Completion(String status) {
        requireNonNull(status);
        checkArgument(isValidCompletion(status), MESSAGE_CONSTRAINTS);
        value = status;
    }

    /**
     * Returns true if a given string is a valid completion value.
     */
    public static boolean isValidCompletion(String test) {
        return test.equalsIgnoreCase("Completed") || test.equalsIgnoreCase("Uncompleted");
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Converts the text into emoji for GUI display
     * @return the corresponding emoji for each completion status
     */
    public String toDisplayString() {
        return value.equals("Completed") ? "✔" : "❌";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Completion
                && value.equals(((Completion) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

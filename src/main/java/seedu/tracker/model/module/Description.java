package seedu.tracker.model.module;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Module's description in the module tracker.
 * Guarantees: immutable.}
 */
public class Description {

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        value = description;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.tracker.model.module.Description // instanceof handles nulls
                && value.equals(((seedu.tracker.model.module.Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


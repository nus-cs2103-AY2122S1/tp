package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a person's importance
 */
public class Importance {
    public static final String MESSAGE_CONSTRAINTS =
            "Importance should be only 'true' or 'false'";
    private Boolean isImportant;

    /**
     * Constructs an {@code Importance}.
     *
     * @param isImportant A non-null importance of Boolean type.
     */
    public Importance(Boolean isImportant) {
        requireNonNull(isImportant);
        this.isImportant = isImportant;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public static boolean isValidImportance(String input) {
        String lowerCaseInput = input.toLowerCase();
        return lowerCaseInput.equals("true") || lowerCaseInput.equals("false");
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isImportant() {
        return isImportant;
    }

    @Override
    public int hashCode() {
        return isImportant.hashCode();
    }
}

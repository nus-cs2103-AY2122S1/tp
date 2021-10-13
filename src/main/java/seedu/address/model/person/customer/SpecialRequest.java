package seedu.address.model.person.customer;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a SpecialRequest in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSpecialRequestName(String)}
 */
public class SpecialRequest {

    public static final String MESSAGE_CONSTRAINTS = "SpecialRequests names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String specialRequestName;

    /**
     * Constructs a {@code SpecialRequest}.
     *
     * @param specialRequestName A valid specialRequest name.
     */
    public SpecialRequest(String specialRequestName) {
        requireNonNull(specialRequestName);
        checkArgument(isValidSpecialRequestName(specialRequestName), MESSAGE_CONSTRAINTS);
        this.specialRequestName = specialRequestName;
    }

    /**
     * Returns true if a given string is a valid specialRequest name.
     */
    public static boolean isValidSpecialRequestName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SpecialRequest // instanceof
                // handles nulls
                && specialRequestName.equals(((SpecialRequest) other).specialRequestName)); // state check
    }

    @Override
    public int hashCode() {
        return specialRequestName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + specialRequestName + ']';
    }

}

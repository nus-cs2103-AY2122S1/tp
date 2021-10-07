package safeforhall.model.person;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's vaccination status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidVaccStatus(String)}
 */
public class VaccStatus {

    public static final String MESSAGE_CONSTRAINTS = "Vaccination status can be true or false (case insensitive).\n"
            + "Alternatively, 0 or 1 can be used as well, with 0 representing false and 1 true.";

    /*
     * Vaccination status can true/false (case insensitive)
     * It could also be 0/1
     */
    public static final String VALIDATION_REGEX = "^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee]|0|1)$";

    public final String vaccStatus;

    /**
     * Constructs a {@code VaccStatus}.
     *
     * @param vaccStatus A valid vaccination status.
     */
    public VaccStatus(String vaccStatus) {
        requireNonNull(vaccStatus);
        checkArgument(isValidVaccStatus(vaccStatus), MESSAGE_CONSTRAINTS);
        this.vaccStatus = vaccStatus;
    }

    /**
     * Returns true if a given string is a valid vaccination status.
     */
    public static boolean isValidVaccStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return vaccStatus;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VaccStatus // instanceof handles nulls
                && vaccStatus.equals(((VaccStatus) other).vaccStatus)); // state check
    }

    @Override
    public int hashCode() {
        return vaccStatus.hashCode();
    }

}


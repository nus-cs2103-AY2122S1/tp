package safeforhall.model.person;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's vaccination status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidVaccStatus(String)}
 */
public class VaccStatus {

    public static final String MESSAGE_CONSTRAINTS = "Vaccination status can be T or F (case insensitive).";

    /*
     * Vaccination status can T/F (case insensitive)
     */
    public static final String VALIDATION_REGEX = "^([Tt]|[Ff])$";

    public static final String DESC = "Vaccinated: ";

    public final String vaccStatus;
    public final boolean vaccinated;

    /**
     * Constructs a {@code VaccStatus}.
     *
     * @param vaccStatus A valid vaccination status.
     */
    public VaccStatus(String vaccStatus) {
        requireNonNull(vaccStatus);
        checkArgument(isValidVaccStatus(vaccStatus), MESSAGE_CONSTRAINTS);
        if (vaccStatus.equals("T") || vaccStatus.equals("t")) {
            this.vaccStatus = "T";
            this.vaccinated = true;
        } else {
            this.vaccStatus = "F";
            this.vaccinated = false;
        }
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
                && vaccinated == ((VaccStatus) other).vaccinated); // state check
    }

    @Override
    public int hashCode() {
        return vaccStatus.hashCode();
    }

}


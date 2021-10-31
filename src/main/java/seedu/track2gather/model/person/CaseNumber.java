package seedu.track2gather.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.util.AppUtil.checkArgument;

/**
 * Represents a quarantined person's case number in the contacts list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCaseNumber(String)}
 */
public class CaseNumber implements Comparable<CaseNumber> {
    public static final String MESSAGE_CONSTRAINTS =
            "Case number should be a positive integer with no leading zeros and at most 6 digits";
    public static final String VALIDATION_REGEX = "^[1-9]\\d{0,5}$";
    public final String value;

    /**
     * Constructs a {@code CaseNumber}.
     *
     * @param caseNumber A valid case number.
     */
    public CaseNumber(String caseNumber) {
        requireNonNull(caseNumber);
        checkArgument(isValidCaseNumber(caseNumber), MESSAGE_CONSTRAINTS);
        value = caseNumber;
    }

    /**
     * Returns true if a given string is a valid case number.
     */
    public static boolean isValidCaseNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CaseNumber // instanceof handles nulls
            && value.equals(((CaseNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(CaseNumber otherCaseNumber) {
        return ((Integer) Integer.parseInt(value))
                .compareTo(Integer.parseInt(otherCaseNumber.value));
    }
}

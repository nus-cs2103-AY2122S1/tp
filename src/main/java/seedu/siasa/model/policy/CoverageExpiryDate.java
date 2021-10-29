package seedu.siasa.model.policy;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

public class CoverageExpiryDate {

    public static final String MESSAGE_CONSTRAINTS = "Expiry Date should be a valid date.";

    public final LocalDate value;

    /**
     * Constructs an {@code CoverageExpiry}.
     *
     * @param expiryDate A valid expiry date.
     */
    public CoverageExpiryDate(LocalDate expiryDate) {
        requireNonNull(expiryDate);
        value = expiryDate;
    }

    /**
     * Returns true if expiry date is in the future.
     */
    public static boolean isFutureExpiryDate(LocalDate expiryDate) {
        return expiryDate.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CoverageExpiryDate // instanceof handles nulls
                && value.equals(((CoverageExpiryDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

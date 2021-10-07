package seedu.siasa.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

public class ExpiryDate {

    public static final String MESSAGE_CONSTRAINTS = "Expiry Date should be a date in the future.";

    public final LocalDate value;

    /**
     * Constructs an {@code ExpiryDate}.
     *
     * @param expiryDate A valid expiry date.
     */
    public ExpiryDate(LocalDate expiryDate) {
        requireNonNull(expiryDate);
        checkArgument(isValidExpiryDate(expiryDate), MESSAGE_CONSTRAINTS);
        value = expiryDate;
    }

    /**
     * Returns true if a given date is a valid expiry date.
     */
    public static boolean isValidExpiryDate(LocalDate expiryDate) {
        return expiryDate.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDate // instanceof handles nulls
                && value.equals(((ExpiryDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

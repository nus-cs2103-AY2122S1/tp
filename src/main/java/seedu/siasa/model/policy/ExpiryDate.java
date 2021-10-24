package seedu.siasa.model.policy;

import static java.util.Objects.requireNonNull;

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
        value = expiryDate;
    }

    /**
     * Returns true if expiry date is in the past.
     */
    public boolean isFutureExpiryDate() {
        return value.isAfter(LocalDate.now());
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

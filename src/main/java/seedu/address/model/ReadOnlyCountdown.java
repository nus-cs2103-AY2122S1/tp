package seedu.address.model;

import java.time.LocalDate;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyCountdown {

    /**
     * Returns an unmodifiable view of the wedding date.
     */
    LocalDate getDate();
}

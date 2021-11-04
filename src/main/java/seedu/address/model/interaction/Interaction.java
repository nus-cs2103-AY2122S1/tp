package seedu.address.model.interaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an interaction in the address book. Guarantees: immutable; date is
 * valid as declared in {@link #isValidDate(String)}
 */
public class Interaction {

    public static final String MESSAGE_CONSTRAINTS = "Please input a valid date (yyyy-MM-dd).";
    public final String description;
    public final LocalDate date;

    /**
     * Constructs a {@code Interaction}.
     * @param description A valid interaction description.
     * @param date A valid date string
     */
    public Interaction(String description, String date) {
        requireNonNull(description);
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
        this.description = description;
    }

    /**
     * Constructs a {@code Interaction}.
     * @param description A valid interaction description.
     */
    public Interaction(String description) {
        requireNonNull(description);
        this.date = LocalDate.now();
        this.description = description;
    }


    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidDate(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Interaction // instanceof handles nulls
                        && description.equals(((Interaction) other).description))
                        && date.equals(((Interaction) other).date); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + description + "][" + date + ']';
    }

}

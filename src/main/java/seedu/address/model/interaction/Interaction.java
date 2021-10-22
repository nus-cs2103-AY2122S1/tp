package seedu.address.model.interaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Represents an interaction in the address book. Guarantees: immutable; date is
 * valid as declared in {@link #isValidDate(String)}
 */
public class Interaction {

    public static final String MESSAGE_CONSTRAINTS = "Please input a valid date (yyyy-MM-dd).";
    public final String description;
    public final LocalDate date;

    /**
     * Constructs a {@code Tag}.
     * @param tagName A valid tag name.
     */
    public Interaction(String description, String date) {
        requireNonNull(description);
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
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

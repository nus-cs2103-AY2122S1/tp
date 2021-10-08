package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormat(String)}
 */
public class Birthday {

    private static final DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("ddMMyyyy");
    public static final String MESSAGE_CONSTRAINTS = "Birthdays should come in the form of ddMMyyyy";
    public static final String MESSAGE_INVALID_DATE = "Birthday is not a valid date";
    public static final String VALIDATION_REGEX = "\\d{8}";
    public final LocalDate birthday;

    /**
     * Constructs an {@code Birthday}.
     *
     * @param birthday A valid email address.
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidFormat(birthday), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(birthday), MESSAGE_INVALID_DATE);
        this.birthday = LocalDate.parse(birthday, ddMMyyyy);
    }

    /**
     * Returns if a given string is in valid format.
     */
    public static boolean isValidFormat(String birthday) {
        return birthday.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if a given string is in valid format.
     */
    public static boolean isValidDate(String birthday) {
        try {
            LocalDate.parse(birthday, ddMMyyyy);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.birthday.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && birthday.equals(((Birthday) other).birthday)); // state check
    }

}

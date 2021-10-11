package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormat(String)}
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthdays should come in the form of ddMMyyyy";
    public static final String MESSAGE_INVALID_DATE = "Birthday is not a valid date";
    public static final String VALIDATION_REGEX = "\\d{8}";
    private static final DateTimeFormatter BIRTHDATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMuuuu");
    public final LocalDate birthdate;

    /**
     * Constructs an {@code Birthday}.
     *
     * @param birthdate A valid date value.
     */
    public Birthday(String birthdate) {
        requireNonNull(birthdate);
        checkArgument(isValidFormat(birthdate), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDate(birthdate), MESSAGE_INVALID_DATE);
        this.birthdate = LocalDate.parse(birthdate, BIRTHDATE_FORMATTER);
    }

    /**
     * Returns if a given string is in valid birthday format.
     */
    public static boolean isValidFormat(String birthday) {
        return birthday.matches(VALIDATION_REGEX);
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String birthday) {
        try {
            LocalDate.parse(birthday, BIRTHDATE_FORMATTER.withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return BIRTHDATE_FORMATTER.format(this.birthdate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && birthdate.equals(((Birthday) other).birthdate)); // state check
    }

    /**
     *
     * @return format for UI display as dd MMM yyyy
     */
    public String display() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy").format(this.birthdate);
    }
}

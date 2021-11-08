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
    public static final String MESSAGE_BIRTHDAY_IN_FUTURE = "Birthday should not be a future date";
    public static final String MESSAGE_INVALID_DATE = "Birthday is not a valid date. " + MESSAGE_CONSTRAINTS;
    public static final String MESSAGE_INVALID_YEAR_0000 = "Year 0000 does not exist.";
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
        checkArgument(!isFutureDate(birthdate), MESSAGE_BIRTHDAY_IN_FUTURE);
        checkArgument(!isYear0000(birthdate), MESSAGE_INVALID_YEAR_0000);
        this.birthdate = LocalDate.parse(birthdate, BIRTHDATE_FORMATTER);
    }

    /**
     * Checks if given string is a date in the future.
     *
     * @param birthday input that is to be checked if it is a future date.
     * @return if a given string is a date in the future.
     */
    public static boolean isFutureDate(String birthday) {
        LocalDate givenDate = LocalDate.parse(birthday, BIRTHDATE_FORMATTER);
        return givenDate.isAfter(LocalDate.now());
    }

    /**
     * Checks if given string is in valid birthday format.
     *
     * @param birthday input that is to be checked if it is a valid birthday format.
     * @return if a given string is a valid birthday format.
     */
    public static boolean isValidFormat(String birthday) {
        return birthday.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if given string is in valid date.
     *
     * @param birthday input that is to be checked if it is a valid date.
     * @return if a given string is a valid date.
     */
    public static boolean isValidDate(String birthday) {
        try {
            LocalDate.parse(birthday, BIRTHDATE_FORMATTER.withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if given string is in year 0000 (which does not exist).
     *
     * @param birthday input that is to be checked if it is year 0000.
     * @return if a given string is in year 0000.
     */
    public static boolean isYear0000(String birthday) {
        String yearString = birthday.substring(birthday.length() - 4);
        return yearString.equals("0000");
    }

    @Override
    public String toString() {
        return BIRTHDATE_FORMATTER.format(this.birthdate);
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && birthdate.equals(((Birthday) other).birthdate)); // state check
    }

    /**
     * Returns string for Ui display.
     *
     * @return format for Ui display as dd MMM yyyy.
     */
    public String display() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy").format(this.birthdate);
    }
}

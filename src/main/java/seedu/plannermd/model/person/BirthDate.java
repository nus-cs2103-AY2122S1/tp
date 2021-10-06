package seedu.plannermd.model.person;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.ResolverStyle;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's birth date in the plannermd. Guarantees: immutable; is
 * valid as declared in {@link #isValidBirthDate(String)}
 */
public class BirthDate {

    public static final String MESSAGE_CONSTRAINTS = "Birth dates should be of the format DD/MM/YYYY "
            + "and adhere to the following constraints:\n"
            + "1. Day must be between 1-31 (0 in front of single digit is optional)\n"
            + "2. Month must be between 1-12 (0 in front of single digit is optional)\n"
            + "3. Year must be 4 characters";

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
            .withResolverStyle(ResolverStyle.SMART);

    public final String stringValue;
    public final LocalDate value;

    /**
     * Constructs a {@code BirthDate}.
     *
     * @param birthDate A valid birth date.
     */
    public BirthDate(String birthDate) {
        requireNonNull(birthDate);
        checkArgument(isValidBirthDate(birthDate), MESSAGE_CONSTRAINTS);
        stringValue = birthDate;
        value = LocalDate.from(LocalDate.parse(birthDate, formatter));
    }

    /**
     * Returns if a given string is a valid birth date.
     */
    public static boolean isValidBirthDate(String test) {
        // Done to check if input isn't just the date
        if (test.split(" ", 2).length > 1) {
            return false;
        }
        try {
            LocalDate.parse(test, formatter);
            LocalDate inputDate = LocalDate.from(LocalDate.parse(test, formatter));
            if (inputDate.isAfter(LocalDate.now())) {
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // public static boolean isValidBirthDate(LocalDate date) {
    // return date.isAfter(LocalDate.now());
    // }

    @Override
    public String toString() {
        return stringValue;
    }

    @Override
    public boolean equals(Object other) {
        BirthDate o = (BirthDate) other;
        return other == this // short circuit if same object
                || (other instanceof BirthDate // instanceof handles nulls
                        && value.equals(((BirthDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public int calculateAge() {
        return Math.abs(Period.between(LocalDate.now(), value).getYears());
    }

}

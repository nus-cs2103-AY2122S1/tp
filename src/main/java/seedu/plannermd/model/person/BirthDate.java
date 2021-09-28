package seedu.plannermd.model.person;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;


import static java.util.Objects.requireNonNull;
import static seedu.plannermd.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the plannermd.
 * Guarantees: immutable; is valid as declared in {@link #isValidDob(String)}
 */
public class BirthDate {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Birth dates should be of the format DD/MM/YYYY "
            + "and adhere to the following constraints:\n"
            + "1. Day must be between 1-31 (0 in front of single digit is optional)\n"
            + "2. Month must be between 1-12 (0 in front of single digit is optional)\n"
            + "3. Year must be 4 characters";

    public static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("d/M/yyyy")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param birthDate A valid email address.
     */
    public BirthDate(String birthDate) {
        requireNonNull(birthDate);
        checkArgument(isValidDob(birthDate), MESSAGE_CONSTRAINTS);
        value = birthDate;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidDob(String test) {
        if (test.split(" ", 2).length > 1) {
            return false;
        }

        try {
            System.out.println(LocalDateTime.parse(test, formatter));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BirthDate // instanceof handles nulls
                && value.equals(((BirthDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


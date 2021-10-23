package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.util.LessonUtil.formattedValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.Objects;
/**
 * Represents the Outstanding Fees for the lesson in the address book.
 */
public class OutstandingFees {

    public static final String MESSAGE_CONSTRAINTS =
            "Outstanding Fees should be formatted with a decimal point '.' "
                    + "as a separator between the dollars and cents, "
                    + "and adhere to the following constraints:\n"
                    + "1. Outstanding Fees should only contain numbers and at most one decimal point.\n"
                    + "2. Outstanding Fees should not start or end with a decimal point"
                    + " and should have at most two decimal places.";

    public static final String VALIDATION_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    public final String value;

    //Date of when outstanding fees were last updated
    private LastAddedDate lastAdded;

    /**
     * Constructs a {@code OutstandingFees}.
     * This constructor is called when users are modifying lesson.
     *
     * @param outstandingFees Valid Outstanding Fees.
     */
    public OutstandingFees(String outstandingFees) {
        requireNonNull(outstandingFees);
        checkArgument(isValidOutstandingFee(outstandingFees));
        value = formattedValue(fillEmptyString(outstandingFees));
        lastAdded = LastAddedDate.of();
    }

    /**
     * Constructs a {@code OutstandingFees}.
     * This constructor only called when converting from local storage to model.
     *
     * @param outstandingFees Valid Outstanding Fees.
     */
    public OutstandingFees(String outstandingFees, LastAddedDate lastAddedDate) {
        requireAllNonNull(outstandingFees, lastAddedDate);
        checkArgument(isValidOutstandingFee(outstandingFees));
        value = formattedValue(fillEmptyString(outstandingFees));
        lastAdded = lastAddedDate;
    }

    private String fillEmptyString(String outstandingFee) {
        return outstandingFee.isEmpty() ? "0.00" : outstandingFee;
    }

    /**
     * Returns true if outstandingFee value is empty.
     *
     * @return True if outstandingFee value is empty.
     */
    public boolean isEmpty() {
        return value.isEmpty();
    }

    /**
     * Returns true if a given string is a valid fee.
     */
    public static boolean isValidOutstandingFee(String test) {
        return test.isEmpty() || test.matches(VALIDATION_REGEX);
    }

    public String getValue() {
        return value;
    }

    public LastAddedDate getLastAdded() {
        return lastAdded;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OutstandingFees // instanceof handles nulls
                && value.equals(((OutstandingFees) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, lastAdded);
    }

    /**
     * Represents the last date the Outstanding Fees field was updated.
     */
    public static class LastAddedDate {

        public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format uuuu-MM-dd "
                + "and adhere to the following constraints:\n"
                + "1. dd, MM and uuuu are numerical characters.\n"
                + "2. Must be a valid date for the year.";

        private static final String VALIDATION_REGEX_DATE = "^[0-9]{4}-(0[1-9]|1[0-2])-[0-9]{2}";
        private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
                .appendPattern("uuuu-MM-dd")
                .toFormatter(Locale.ENGLISH)
                .withResolverStyle(ResolverStyle.STRICT);

        public final String lastAddedString;
        private final LocalDate lastAddedDate;

        /**
         * Constructs an {@code LastAddedDate}.
         * For when using the application.
         */
        private LastAddedDate() {
            lastAddedDate = LocalDate.now();
            lastAddedString = lastAddedDate.toString();
        }

        /**
         * Constructs an {@code LastAddedDate}.
         * For when converting from local storage to model.
         */
        public LastAddedDate(String lastAddedDate) {
            this.lastAddedDate = LocalDate.parse(lastAddedDate, FORMATTER);
            lastAddedString = lastAddedDate;
        }

        public static LastAddedDate of() {
            return new LastAddedDate();
        }

        public LocalDate getLastAddedDate() {
            return lastAddedDate;
        }

        /**
         * Returns if a given string is a valid last added date.
         *
         * @param test The string to be tested.
         */
        public static boolean isValidLastAddedDate(String test) {
            boolean isValid = true;
            try {
                LocalDate.parse(test, FORMATTER);
            } catch (DateTimeParseException e) {
                isValid = false;
            } finally {
                return test.matches(VALIDATION_REGEX_DATE) && isValid;
            }
        }

        @Override
        public String toString() {
            return lastAddedString;
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof LastAddedDate // instanceof handles nulls
                    && lastAddedString.equals(((LastAddedDate) other).lastAddedString)); // state check
        }

        @Override
        public int hashCode() {
            return lastAddedString.hashCode();
        }
    }
}

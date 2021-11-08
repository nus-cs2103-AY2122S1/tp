package seedu.track2gather.model.person.attributes;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.util.Pair;

/**
 * Represents a Person's period in the contacts list.
 * Guarantees: immutable; is valid as declared in {@link #isValidPeriod(LocalDate, LocalDate)}
 */
public class Period extends Attribute<Pair<LocalDate, LocalDate>> {
    public static final String MESSAGE_CONSTRAINTS =
            "SHN periods should comprise of two dates in the ISO-8601 format (i.e. yyyy-MM-dd), "
                    + "separated by a space. The start date should be keyed before the end date, "
                    + "and must occur earlier than the end date by at least 1 day.";
    public static final String MESSAGE_CONSTRAINTS_DATE =
            "Dates should be in the ISO-8601 format (i.e. yyyy-MM-dd)";
    public static final String MESSAGE_CONSTRAINTS_DATE_KEYWORDS =
            "Date keywords should be in the ISO-8601 format (i.e. yyyy-MM-dd)";

    /**
     * Constructs a {@code Period}.
     *
     * @param periodPair A valid period pair.
     */
    public Period(Pair<LocalDate, LocalDate> periodPair) {
        super(periodPair);
        requireNonNull(periodPair);
        requireNonNull(super.value.getKey());
        requireNonNull(super.value.getValue());
        checkArgument(isValidPeriod(super.value.getKey(), super.value.getValue()), MESSAGE_CONSTRAINTS);
    }

    /**
     * Constructs a {@code Period}.
     *
     * @param startDate A valid start date.
     * @param endDate   A valid end date.
     */
    public Period(LocalDate startDate, LocalDate endDate) {
        this(new Pair<>(startDate, endDate));
    }

    /**
     * Constructs a {@code Period} from a String.
     *
     * @param period A valid string period.
     */
    public Period(String period) {
        this(LocalDate.parse(period.split(" => ", 2)[0]),
                LocalDate.parse(period.split(" => ", 2)[1]));
    }

    /**
     * Returns true if a given Period is a valid period.
     */
    public static boolean isValidPeriod(LocalDate testStartDate, LocalDate testEndDate) {
        requireNonNull(testStartDate);
        requireNonNull(testEndDate);
        return testEndDate.compareTo(testStartDate) > 0; // end date is after start date
    }

    /**
     * Returns true if a given period in string form is a valid period.
     */
    public static boolean isValidPeriodString(String period) {
        requireNonNull(period);
        String[] dates = period.split(" => ", 2);
        if (dates.length != 2) {
            return false;
        }

        try {
            LocalDate testStartDate = LocalDate.parse(dates[0]);
            LocalDate testEndDate = LocalDate.parse(dates[1]);
            return isValidPeriod(testStartDate, testEndDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate getStartDate() {
        return value.getKey();
    }

    public LocalDate getEndDate() {
        return value.getValue();
    }

    /**
     * Returns true if a given period date is in the valid format.
     */
    public static boolean isValidDate(String date) {
        requireNonNull(date);
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if this period is completed by the given date.
     */
    public boolean isCompletedBy(LocalDate dateToCompare) {
        return getEndDate().isBefore(dateToCompare);
    }

    @Override
    public String toString() {
        return String.format("%s => %s", getStartDate(), getEndDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Period // instanceof handles nulls
                && value.equals(((Period) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

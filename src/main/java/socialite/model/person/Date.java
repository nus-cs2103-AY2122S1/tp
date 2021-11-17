package socialite.model.person;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import socialite.commons.util.AppUtil;

public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be formatted as NAME:YYYY-MM-DD or NAME:YYYY-MM-DD:[monthly|yearly] for recurring dates.\n"
                    + "The date name should have a length of up to 50 characters.";

    public static final String VALIDATION_REGEX =
            "([\\w\\s]+):(\\d{4})-(\\d{2})-(\\d{2})(?::(yearly|monthly))?\\s*";
    public static final Pattern VALIDATION_PATTERN = Pattern.compile(VALIDATION_REGEX);

    private final String name;
    private final LocalDate date;
    private final String recurrenceInterval;

    /**
     * Construct a new {@code Date} with a given name and value,
     * by parsing the input string.
     * @param date The input string to parse.
     */
    public Date(String date) {
        requireNonNull(date);
        AppUtil.checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);

        Matcher matcher = VALIDATION_PATTERN.matcher(date);
        boolean matches = matcher.matches();
        assert matches;

        String name = matcher.group(1).trim();

        int year = Integer.parseInt(matcher.group(2));
        int month = Integer.parseInt(matcher.group(3));
        int day = Integer.parseInt(matcher.group(4));
        String recurring = matcher.group(5);

        this.name = name;
        this.date = LocalDate.of(year, month, day);
        this.recurrenceInterval = recurring;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Optional<String> getRecurrenceInterval() {
        return Optional.ofNullable(recurrenceInterval);
    }

    /**
     * Returns the next occurrence of the date after the reference date.
     * Returns {@code Optional.empty()} for non-recurring dates which occur before the reference date.
     *
     * @param referenceDate The reference date.
     * @return The next occurrence of the date.
     */
    public Optional<LocalDate> getNextOccurrence(LocalDate referenceDate) {
        if (recurrenceInterval == null) {
            return date.isBefore(referenceDate) ? Optional.empty() : Optional.of(date);
        }

        Period interval = getIntervalPeriod(recurrenceInterval);
        assert interval != null;
        LocalDate newDate = LocalDate.from(date);
        while (newDate.isBefore(referenceDate)) {
            newDate = newDate.plus(interval);
        }

        return Optional.of(newDate);
    }

    /**
     * Returns the number of days from the next occurrence of the date to the reference date.
     * A negative number will be returned if the next occurrence is before the reference date.
     *
     * @param referenceDate The reference date.
     * @return The number of days.
     */
    public long getUpcomingDays(LocalDate referenceDate) {
        LocalDate nextOccurrence = getNextOccurrence(referenceDate).orElse(LocalDate.MIN);

        return ChronoUnit.DAYS.between(referenceDate, nextOccurrence);
    }

    /**
     * Returns true if a given string is a valid sequence of dates.
     */
    public static boolean isValidDate(String test) {
        Matcher matcher = VALIDATION_PATTERN.matcher(test);
        if (!matcher.matches()) {
            return false;
        }

        String name = matcher.group(1).trim();
        if (name.length() > 50) {
            return false;
        }

        int year = Integer.parseInt(matcher.group(2));
        int month = Integer.parseInt(matcher.group(3));
        int day = Integer.parseInt(matcher.group(4));

        // Ensure that date is a valid date.
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return false;
        }

        return true;
    }

    /**
     * Returns a comparator which can be used to compare dates.
     * Dates are compared based on their next occurrence, followed by their original date.
     *
     * @return a comparator
     */
    public static Comparator<Date> getComparator(LocalDate referenceDate) {
        Comparator<Date> comparator = Comparator.comparing(date -> date.getNextOccurrence(referenceDate)
                        .orElse(LocalDate.MIN),
                Comparator.reverseOrder());
        return comparator.thenComparing(Date::getDate, Comparator.reverseOrder());
    }

    private static Period getIntervalPeriod(String recurrenceInterval) {
        switch (recurrenceInterval) {
        case "yearly":
            return Period.of(1, 0, 0);
        case "monthly":
            return Period.of(0, 1, 0);
        default:
            return null;
        }
    }

    @Override
    public String toString() {
        return name + ": " + date + (recurrenceInterval == null ? "" : " (" + recurrenceInterval + ")");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && name.equals(((Date) other).name)
                && date.equals(((Date) other).date)
                && Objects.equals(recurrenceInterval, ((Date) other).recurrenceInterval)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, recurrenceInterval);
    }
}

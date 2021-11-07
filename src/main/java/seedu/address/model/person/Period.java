package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Class representing a period of dates.
 */
public class Period {
    public static final String MESSAGE_CONSTRAINTS =
            "Period should be a range of two dates with a space in between\n"
            + "E.g. 1999-10-09 1999-11-15";

    private static final String DELIMITER = " ";

    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructs a {@code Period} with two {@code LocalDate},
     * order is not needed.
     */
    public Period(LocalDate startDate, LocalDate endDate) {
        //to swap
        if (startDate.isAfter(endDate)) {
            this.startDate = endDate;
            this.endDate = startDate;
        } else {
            this.startDate = startDate;
            this.endDate = endDate;
        }

    }

    /**
     * Constructs a {@code Period} with the same data as
     * {@code period}.
     */
    public Period(Period period) {
        this.startDate = period.startDate;
        this.endDate = period.endDate;
    }

    /**
     * Constructs a {@code Period} with a single {@code date}.
     */
    public Period(LocalDate date) {
        this.startDate = date;
        this.endDate = date;
    }

    /**
     * Returns true if any dates within {@code period} is within
     * {@code this}.
     */
    public boolean isWithin(Period period) {
        return this.contains(period.startDate)
                || this.contains(period.endDate);
    }

    /**
     * Obtains a {@code Period} representing the period across the month of the input date.
     * E.g. with input date 2021-10-20, the resulting period will span 2021-10-01 to
     * 2021-10-31.
     */
    public static Period getPeriodFromDateOverMonth(LocalDate date) {
        Month month = date.getMonth();
        int year = date.getYear();
        int lastDate = month.length(year % 4 == 0);
        return new Period(LocalDate.of(year, month, 1),
                LocalDate.of(year, month, lastDate));
    }

    /**
     * Parses a {@code period} into a Period object.
     *
     * @throws DateTimeParseException when the input is invalid.
     * @throws IllegalArgumentException when the input cannot be split.
     */
    public static Period transformStringToPeriod(String period) {
        String[] splitPeriod = period.split(DELIMITER);
        if (splitPeriod.length != 2) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        return new Period(LocalDate.parse(splitPeriod[0]),
                LocalDate.parse(splitPeriod[1]));

    }

    /**
     * Checks if {@code date} is contained by the period inclusive of the
     * {@code start} and {@code end} of the Period object. Returns true
     * if it is contained and false otherwise.
     */
    public boolean contains(LocalDate date) {
        return withinExclusively(date)
                || atDelimiters(date);
    }

    /**
     * Returns true if {@code this} contains {@code period}.
     */
    public boolean contains(Period period) {
        return this.contains(period.startDate)
                && this.contains(period.endDate);
    }

    /**
     * Gets the complement duration of {@code period} and the
     * input period.
     */
    public Collection<Period> complement(Period period) {
        assert period.endDate.isAfter(period.startDate)
                || period.endDate.isEqual(period.startDate);
        assert endDate.isAfter(startDate)
                || endDate.isEqual(startDate);
        if (period.contains(this)) {
            return List.of();
        }
        if (contains(period)) {
            //when it is contained
            Period period1 = new Period(startDate, period.startDate.minusDays(1));
            Period period2 = new Period(period.endDate.plusDays(1), endDate);
            if (startDate.equals(period.startDate)) {
                //we know that period2 must be not in period
                return List.of(period2);
            }
            if (endDate.equals(period.endDate)) {
                //we know that period1 must be not in period
                return List.of(period1);
            }
            return List.of(period1, period2);
        }
        // startDate period.startDate endDate period.endDate
        // startDate <-> period.startDate - 1
        if (contains(period.startDate)) {
            return List.of(new Period(startDate, period.startDate.minusDays(1)));
        }
        // period.startDate startDate period.endDate endDate
        // period.endDate + 1 <-> endDate
        if (contains(period.endDate)) {
            return List.of(new Period(period.endDate.plusDays(1), endDate));
        }

        //when there is no need to
        return List.of(this);
    }

    /**
     * Obtains the periods within the input set that lie in this.
     * Assumes that the input periods do not have any overlaps.
     */
    public Collection<Period> intersect(Collection<? extends Period> periods) {
        return periods.stream()
                .flatMap(p -> this.intersect(p).stream())
                .collect(Collectors.toList());
    }

    /**
     * Finds the region in time where both {@code this}
     * and {@code period} lie in.
     *
     */
    public Collection<Period> intersect(Period period) {
        requireNonNull(period);
        if (this.contains(period)) {
            return List.of(period);
        }
        if (period.contains(this)) {
            return List.of(this);
        }
        if (this.contains(period.startDate)) {
            return List.of(new Period(period.startDate, this.endDate));
        }
        if (this.contains(period.endDate)) {
            return List.of(new Period(this.startDate, period.endDate));
        }
        return List.of();

    }

    /**
     * Unions the input {@code Collection<Period> periods} with {@code this}.
     */
    public Collection<Period> union(Collection<Period> periods) {
        if (periods.stream().count() == 0) {
            //nothing to union to
            return List.of(this);
        }
        //optimisation
        if (periods.stream()
                .filter(p -> p.contains(this)).count() != 0) {
            return periods;
        }
        //the list of periods to union
        List<Period> toMerge = new ArrayList<>();
        Collection<Period> result = periods.stream()
                .flatMap(p -> this.union(p, pe -> toMerge.add(pe)).stream())
                .collect(Collectors.toList());
        //merge the changed periods
        //in theory there is only two assuming collection is unique
        for (Period p : toMerge) {
            result = p.union(result);
        }
        return result;

    }

    /**
     * Unions {@code period} with {@code this}.
     * The result is placed into the consumer if it is modified.
     */
    private Collection<Period> union(Period period, Consumer<Period> consumer) {
        requireNonNull(period);
        if (this.contains(period)) {
            return List.of(this);
        }
        if (period.contains(this)) {
            return List.of(period);
        }
        //can lead to multiple unions
        if (contains(period.startDate.minusDays(1))) {
            //we know that periods endDate is not contained
            Period res = new Period(startDate, period.endDate);
            consumer.accept(res);
            return List.of();
        }
        if (contains(period.endDate.plusDays(1))) {
            //we know that period startDate is not contained
            Period res = new Period(period.startDate, endDate);
            consumer.accept(res);
            return List.of();
        }
        return List.of(period, this);
    }

    private boolean withinExclusively(LocalDate date) {
        return (this.startDate.isBefore(date))
                && (this.endDate.isAfter(date));
    }

    private boolean atDelimiters(LocalDate date) {
        return this.startDate.isEqual(date)
                || this.endDate.isEqual(date);
    }

    /**
     * Tests if the input string is a valid string representing a period.
     */
    public static boolean isValidPeriodString(String toTest) {
        String[] toTestSplit = toTest.split(DELIMITER);
        if (toTestSplit.length != 2) {
            return false;
        }
        try {
            LocalDate.parse(toTestSplit[0]);
            LocalDate.parse(toTestSplit[1]);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }

    /**
     * Returns the first date of the period.
     *
     * @return LocalDate representing the first date of the period.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns a Period that represents a week that starts from the LocalDate provided.
     *
     * @param firstDate first date of the week.
     */
    public static Period oneWeekFrom(LocalDate firstDate) {
        return new Period(firstDate, firstDate.plusDays(6));
    }

    /**
     * Gets an List representing an iteration over this period.
     *
     * @return The List.
     */
    public List<LocalDate> toList() {
        return startDate
                .datesUntil(endDate.plusDays(1)) //exclusive
                .collect(Collectors.toList());
    }

    @Override
    public final String toString() {
        return this.startDate + DELIMITER + this.endDate;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null)
                && (o instanceof Period)
                && ((Period) o).startDate.equals(startDate)
                && ((Period) o).endDate.equals(endDate);

    }

    /**
     * Returns string representation of this period in a more readable format.
     */
    public String toDisplayString() {
        return ("[" + startDate + "] ~ [" + endDate + "]" );
    }
}

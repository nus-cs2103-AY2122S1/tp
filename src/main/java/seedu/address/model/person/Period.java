package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class representing a period of dates.
 */
public class Period {
    public static final String MESSAGE_CONSTRAINTS =
            "Period should be a range of two dates with a space in between\n"
            + "E.g. 1999-10-09 1999-11-15";

    private static final String DELIMITER = " ";

    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs a {@code Period} with two {@code LocalDate},
     * order is not needed.
     */
    public Period(LocalDate start, LocalDate end) {
        //to swap
        if (start.isAfter(end)) {
            this.start = end;
            this.end = start;
        } else {
            this.start = end;
            this.end = end;
        }

    }


    /**
     * Constructs a {@code Period} with a single {@code date}.
     */
    public Period(LocalDate date) {
        this.start = date;
        this.end = date;
    }

    /**
     * Parses a {@code period} into a Period object.
     *
     * @throws DateTimeParseException when the input is invalid.
     */
    public static Period transformStringToPeriod(String period) {
        String[] splitPeriod = period.split(DELIMITER);
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
        return this.contains(period.start)
                && this.contains(period.end);

    }

    private boolean withinExclusively(LocalDate date) {
        return (this.start.isBefore(date))
                && (this.end.isAfter(date));
    }

    private boolean atDelimiters(LocalDate date) {
        return this.start.isEqual(date)
                || this.end.isEqual(date);
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


    @Override
    public String toString() {
        return this.start + DELIMITER + this.end;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null)
                && (o instanceof Period)
                && ((Period) o).start.equals(this.start)
                && ((Period) o).end.equals(this.end);

    }
}

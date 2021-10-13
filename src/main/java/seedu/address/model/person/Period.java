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
            this.startDate = endDate;
            this.endDate = endDate;
        }

    }


    /**
     * Constructs a {@code Period} with a single {@code date}.
     */
    public Period(LocalDate date) {
        this.startDate = date;
        this.endDate = date;
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


    @Override
    public String toString() {
        return this.startDate + DELIMITER + this.endDate;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null)
                && (o instanceof Period)
                && ((Period) o).contains(this)
                && this.contains((Period) o);

    }
}

package seedu.address.model.person;

import java.time.LocalDate;

/**
 * Class representing a period of dates.
 */
public class Period {
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
     * Checks if {@code date} is contained by the period inclusive of the
     * {@code start} and {@code end} of the Period object. Returns true
     * if it is contained and false otherwise.
     */
    public boolean contains(LocalDate date) {
        return withinExclusively(date)
                || atDelimiters(date);

    }

    private boolean withinExclusively(LocalDate date) {
        return (this.start.isBefore(date))
                && (this.end.isAfter(date));
    }

    private boolean atDelimiters(LocalDate date) {
        return this.start.isEqual(date)
                || this.end.isEqual(date);
    }

}

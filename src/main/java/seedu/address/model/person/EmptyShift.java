package seedu.address.model.person;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import seedu.address.model.RecurrencePeriod;

/**
 * Class representing a shift that is not being used.
 */
public class EmptyShift extends Shift {

    /**
     * Creates an {@code EmptyShift} object which represents a shift where the staff is not
     * working from the oldest date in its history to now and the future.
     */
    public EmptyShift(DayOfWeek dayOfWeek, Slot slot) {
        super(dayOfWeek, slot);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }


    @Override
    public boolean isWorking(LocalTime time, Period period) {
        return false;
    }


    @Override
    public Shift add(LocalDate startDate, LocalDate endDate) {
        return new Shift(dayOfWeek, slot, List.of(new RecurrencePeriod(new Period(startDate, endDate), slot)));
    }

    @Override
    public Shift remove(LocalDate startDate, LocalDate endDate) {
        throw new UnsupportedOperationException("This method should not be called.");
    }

}

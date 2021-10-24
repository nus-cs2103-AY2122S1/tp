package seedu.address.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import seedu.address.model.person.Period;
import seedu.address.model.person.Shift;
import seedu.address.model.person.Slot;

public class EmptyShift extends Shift {

    public EmptyShift(DayOfWeek dayOfWeek, Slot slot, List<Period> history) {
        super(dayOfWeek, slot);
        this.history.addAll(history);
        isWorking = false;

    }



    @Override
    public boolean isWorking(LocalTime time) {
        return false;
    }

    @Override
    public Shift activate(LocalDate startDate) {
        return new Shift(dayOfWeek, slot, startDate, history);
    }

    @Override
    public Shift remove(LocalDate endDate) {
        throw new UnsupportedOperationException("This method should not be called.");
    }

}
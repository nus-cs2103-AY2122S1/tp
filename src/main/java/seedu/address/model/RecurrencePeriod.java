package seedu.address.model;

import static seedu.address.commons.util.TimeUtil.DEFAULT_AFTERNOON_END_TIME;
import static seedu.address.commons.util.TimeUtil.DEFAULT_AFTERNOON_START_TIME;
import static seedu.address.commons.util.TimeUtil.DEFAULT_MORNING_END_TIME;
import static seedu.address.commons.util.TimeUtil.DEFAULT_MORNING_START_TIME;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.person.Period;
import seedu.address.model.person.Slot;

/**
 * Utility class to represent the recurrence of a shift over a period of dates.
 */
public class RecurrencePeriod extends Period {
    private final Period period;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Creates an {@code RecurrencePeriod} using its corresponding fields.
     */
    public RecurrencePeriod(Period period, LocalTime startTime, LocalTime endTime) {
        super(period);
        this.period = period;
        assert startTime.isBefore(endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Default recurrence period for test cases.
     *
     */
    public RecurrencePeriod(Period period, Slot slot) {
        super(period);
        this.period = period;
        if (slot.equals(Slot.MORNING)) {
            this.startTime = DEFAULT_MORNING_START_TIME;
            this.endTime = DEFAULT_MORNING_END_TIME;
        } else if (slot.equals(Slot.AFTERNOON)) {
            this.startTime = DEFAULT_AFTERNOON_START_TIME;
            this.endTime = DEFAULT_AFTERNOON_END_TIME;
        } else {
            throw new IllegalStateException("This should not occur");
        }


    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public Period getPeriod() {
        return this.period;
    }

    public long getWorkingHour() {
        return Duration.between(this.startTime, this.endTime).toHours();
    }

    /**
     * Unions the period represented by this {@code RecurrencePeriod} with the {@code RecurrencePeriod}
     * that have an overlapping period and the same start and end time.
     */
    public Collection<RecurrencePeriod> unionByDuration(Collection<RecurrencePeriod> periods) {
        List<RecurrencePeriod> result = new ArrayList<>();
        Collection<RecurrencePeriod> filteredPeriods = periods.stream()
                .filter(p -> p.isSameDuration(this))
                .collect(Collectors.toList());
        //remove the periods from the input
        result.addAll(periods);
        result.removeAll(filteredPeriods);
        Collection<Period> underlyingPeriods = filteredPeriods.stream()
                .map(p -> p.getPeriod())
                .collect(Collectors.toList());
        underlyingPeriods = this.period.union(underlyingPeriods);
        result = underlyingPeriods.stream()
                .map(p -> new RecurrencePeriod(p, startTime, endTime))
                .collect(Collectors.toList());
        return result;

    }


    public Collection<RecurrencePeriod> complementWithInformation(Period period) {
        Collection<Period> periods = super.complement(period);
        return periods.stream()
                .map(p -> new RecurrencePeriod(period, startTime, endTime))
                .collect(Collectors.toList());

    }


    private boolean isSameDuration(RecurrencePeriod period) {
        return getStartTime().equals(period.getStartTime())
                && getEndTime().equals(period.getEndTime());
    }

    /**
     * Checks if a specified timing is within the slot period.
     *
     * @param time The time which will be checked against.
     * @return
     */
    public boolean isWithinSlotPeriod(LocalTime time) {
        return time.equals(startTime) || time.equals(endTime)
                || time.isBefore(endTime) && time.isAfter(startTime);
    }
}

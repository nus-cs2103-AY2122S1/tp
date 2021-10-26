package seedu.address.model.person;

import static seedu.address.commons.util.TimeUtil.DEFAULT_AFTERNOON_END_TIME;
import static seedu.address.commons.util.TimeUtil.DEFAULT_AFTERNOON_START_TIME;
import static seedu.address.commons.util.TimeUtil.DEFAULT_MORNING_END_TIME;
import static seedu.address.commons.util.TimeUtil.DEFAULT_MORNING_START_TIME;
import static seedu.address.commons.util.TimeUtil.isValidTime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.InvalidShiftTimeException;
import seedu.address.model.EmptyShift;
import seedu.address.model.RecurrencePeriod;
import seedu.address.model.person.exceptions.NoShiftException;

/**
 * Represents a piece of work for a staff.
 */
public class Shift {

    public static final String DELIMITER = "-";

    protected Slot slot;
    protected DayOfWeek dayOfWeek;
    protected List<RecurrencePeriod> recurrences = new ArrayList<>();

    /**
     * Constructor of Task given its weekday, time, and name.
     *
     * @param dayOfWeek Weekday of the shift.
     * @param slot The slot when the shift located.
     */
    public Shift(DayOfWeek dayOfWeek, Slot slot) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;

    }

    /**
     * Creates a shift at {@code dayOfWeek} in {@code Slot slot} at {@code LocalDate startDate}
     * with a history of changes {@code Set<Shift> history}.
     */
    public Shift(DayOfWeek dayOfWeek, Slot slot, List<RecurrencePeriod> recurrences) {
        this.dayOfWeek = dayOfWeek;
        this.slot = slot;
        this.recurrences.addAll(recurrences);

    }


    public Slot getSlot() {
        return this.slot;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }


    public List<RecurrencePeriod> getRecurrences() {
        return Collections.unmodifiableList(recurrences);
    }

    /**
     * Returns if this is an empty shift.
     *
     */
    public boolean isEmpty() {
        return false;
    }


    /**
     * Returns whether the shift is happening in the morning.
     *
     * @return whether the shift is happening in the morning.
     */
    public boolean isInMorning() {
        return this.slot.getValue().equals("morning");
    }

    /**
     * Returns whether the shift is happening in the afternoon.
     *
     * @return whether the shift is happening in the afternoon.
     */
    public boolean isInAfternoon() {
        return this.slot.getValue().equals("afternoon");
    }

    /**
     * Returns whether the staff is working during this {@code LocalTime time}
     * Within {@code Period period.}
     */
    public boolean isWorking(LocalTime time, Period period) {
        List<LocalDate> dates = period.toList()
                .stream()
                .filter(p -> p.getDayOfWeek().equals(dayOfWeek))
                .collect(Collectors.toList());
        long numOfRecurrences = recurrences.stream()
                .filter(p -> period.isWithin(p))
                .filter(p -> dates.stream().filter(date -> p.contains(date)).count() != 0)
                .filter(p -> p.isWithinSlotPeriod(time))
                .count();
        return numOfRecurrences != 0;
    }

    /**
     * Returns whether the shift
     * represents that it is working during {@code period}.
     */
    public boolean isWorking(Period period) {
        if (this.recurrences.size() == 0) {
            return false;
        }

        List<LocalDate> dates = period.toList()
                .stream()
                .filter(p -> p.getDayOfWeek().equals(dayOfWeek))
                .collect(Collectors.toList());

        long numOfRecurrences = recurrences.stream()
                .filter(p ->
                        0 != dates.stream()
                                .filter(date -> p.contains(date)) //find any date within the period
                                .count() //that is in recurrence
                )
                .count();

        return numOfRecurrences != 0;
    }

    public long getWorkingHour(Period period) {
        List<LocalDate> dates = period.toList()
                .stream()
                .filter(p -> p.getDayOfWeek().equals(dayOfWeek))
                .collect(Collectors.toList());
        return recurrences.stream()
                .filter(p ->
                        0 != dates.stream()
                                .filter(date -> p.contains(date)) //find any date within the period
                                .count() //that is in recurrence
                )
                .mapToLong(p -> p.getWorkingHour())
                .sum();


    }





    /**
     * Removes the shift that is withing the input dates.
     */
    public Shift remove(LocalDate startDate, LocalDate endDate) throws NoShiftException {
        assert endDate.isAfter(startDate) || endDate.isEqual(startDate);
        //check if this period is already within the current set
        Period period = new Period(startDate, endDate);
        List<RecurrencePeriod> recurrences = this.recurrences.stream()
                .flatMap(p -> p.complementWithInformation(period).stream())
                .collect(Collectors.toList());
        if (recurrences.equals(this.recurrences)) {
            throw new NoShiftException();
        }
        if (recurrences.size() == 0) {
            return new EmptyShift(dayOfWeek, slot);
        }
        return new Shift(dayOfWeek, slot, recurrences);

    }

    /**
     * Checks if the input date is already logged.
     */
    public boolean isRegistered(LocalDate date) {
        long val = this.recurrences.stream()
                .filter(p -> p.getPeriod().contains(date))
                .count();
        return val != 0;
    }

    /**
     * Makes this shift a working shift
     */
    public Shift add(LocalDate startDate, LocalDate endDate) {
        Period period = new Period(startDate, endDate);
        RecurrencePeriod recurrencePeriod = new RecurrencePeriod(period, slot);
        List<RecurrencePeriod> recurrences = recurrencePeriod.unionByDuration(this.recurrences).stream()
                .collect(Collectors.toList());
        return new Shift(dayOfWeek, slot, recurrences);
    }


    /**
     * Returns if a given string is a valid DayOfWeek.
     */
    public static boolean isValidDayOfWeek(String test) {
        for (DayOfWeek d :DayOfWeek.values()) {
            if (d.toString().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Check if the timing for a shift is valid, and then update them.
     * @param startTime Start time of the shift
     * @param endTime End time of the shift
     * @param order Indicate the shift is in the morning slot or the afternoon slot.
     * @throws InvalidShiftTimeException Throws when the timing is invalid. There are two cases, one is the startTime
     * is later then the endTime, the other is that the time is out of the bound of the default one.
     */
    public Shift setTime(LocalTime startTime, LocalTime endTime, int order,
                        LocalDate startDate, LocalDate endDate) throws InvalidShiftTimeException {
        checkTimeOrder(startTime, endTime, order);
        List<RecurrencePeriod> result = new ArrayList<>();
        result.addAll(recurrences);
        Period period = new Period(startDate, endDate);
        Collection<Period> intersects = period.intersect(recurrences);
        Collection<RecurrencePeriod> recurrenceIntersects = recurrences.stream() //obtain the periods to remove
                .filter(p -> intersects.contains(p.getPeriod()))
                .collect(Collectors.toList());
        result.removeAll(recurrenceIntersects);
        recurrenceIntersects = intersects.stream()
                .map(p -> new RecurrencePeriod(p, startTime, endTime))
                .collect(Collectors.toList());
        result.addAll(recurrenceIntersects);
        return new Shift(dayOfWeek, slot, result);


    }

    private void checkTimeOrder(LocalTime startTime, LocalTime endTime, int order) throws InvalidShiftTimeException {
        if (startTime.isAfter(endTime)) {
            throw new InvalidShiftTimeException();
        }
        if (order == 0) {
            if (startTime.isBefore(DEFAULT_MORNING_START_TIME) || endTime.isAfter(DEFAULT_MORNING_END_TIME)) {
                throw new InvalidShiftTimeException();
            }
        } else {
            if (startTime.isBefore(DEFAULT_AFTERNOON_START_TIME) || endTime.isAfter(DEFAULT_AFTERNOON_END_TIME)) {
                throw new InvalidShiftTimeException();
            }
        }
    }


    /**
     * Returns if a given string is a valid shift.
     */
    public static boolean isValidShift(String test) {
        if (test.equals("")) {
            return false;
        }
        String[] stringSplit = test.split(DELIMITER);
        if (stringSplit.length != 4) {
            return false;
        }
        String dayString = stringSplit[0];
        String slotString = stringSplit[1];
        String startTimeString = stringSplit[2];
        String endTimeString = stringSplit[3];
        return isValidDayOfWeek(dayString)
                && Slot.isValidSlot(slotString)
                && isValidTime(startTimeString)
                && isValidTime(endTimeString);

    }



    /**
     * Returns a string of staff names that work on a specified shift. Result string is numbered and
     * has each staff in a new line.
     *
     * @param stafflist full list of staff in Staff'd.
     * @param day day of shift to be compared to.
     * @param time time of shift to be compared to.
     */
    public static String filterListByShift(ObservableList<Person> stafflist, DayOfWeek day,
                                           LocalTime time, Period period) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Person p : stafflist) {
            boolean hasShift = p.isWorking(day, time, period);
            if (hasShift) {
                result.append(counter).append(". ").append(p.getName()).append("\n");
                counter++;
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Shift)) {
            return false;
        }
        Shift otherShift = (Shift) other;
        return slot.equals(otherShift.slot) && dayOfWeek.equals(otherShift.dayOfWeek)
                && this.recurrences.equals(otherShift.recurrences);
    }





}

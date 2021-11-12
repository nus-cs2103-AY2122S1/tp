package seedu.address.model.person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.RecurrencePeriod;

public abstract class PersonTestUtil {

    /**
     * Convenience method to create test periods over the range
     * 1 to 28.
     */
    public static Period createPeriod(int val1, int val2) {
        assert isValidInt(val1);
        assert isValidInt(val2);
        return new Period(LocalDate.of(1, 1, val1 % 30),
                LocalDate.of(1, 1, val2 % 30));
    }

    /**
     * A method to create test dates over the range 1 to 28.
     */
    public static List<LocalDate> createDates(int val1, int val2) {
        assert isValidInt(val1);
        assert isValidInt(val2);
        ArrayList<LocalDate> result = new ArrayList<>();
        for (int i = val1; i <= val2; i++) {
            result.add(LocalDate.of(1, 1, i));
        }
        return List.copyOf(result);
    }


    private static boolean isValidInt(int val) {
        return val <= 28 && val > 0;
    }

    /**
     * Creates a {@code RecurrencePeriod} in the morning with test dates within the range
     * 1 and 28.
     */
    public static RecurrencePeriod createRecurrencePeriod(int i, int j) {
        return new RecurrencePeriod(createPeriod(i, j), Slot.MORNING);
    }

    /**
     * Creates a {@code RecurrencePeriod} in the time range in between {@code LocalTime startTime}
     * and {@code LocalTime endTime}.
     *
     */
    public static RecurrencePeriod createRecurrencePeriod(int i, int j, LocalTime startTime, LocalTime endTime) {
        assert endTime.isAfter(startTime);
        return new RecurrencePeriod(createPeriod(i, j), startTime, endTime);
    }
}

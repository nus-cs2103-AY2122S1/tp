package seedu.address.logic.commands;

import java.time.DayOfWeek;
import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Period;

public abstract class CommandUtil {

    public static final String MESSAGE_SHIFT_NOT_IN_RANGE = "The date range provided does not contain the shift.";

    /**
     * Throws a {@code CommandException} if the {@code DayOfWeek} is not found in the date range provided.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @param day The day to find.
     * @throws CommandException The exception thrown.
     */
    public static void checkDateForDayOfWeek(LocalDate startDate,
                                             LocalDate endDate, DayOfWeek day) throws CommandException {
        Period period = new Period(startDate, endDate);
        checkDateForDayOfWeek(period, day);
    }

    /**
     * Throws a {@code CommandException} if the {@code DayOfWeek} is not in the input {@code Period}.
     *
     * @param period The period to look through.
     * @param day The day to find.
     * @throws CommandException The exception to throw.
     */
    public static void checkDateForDayOfWeek(Period period, DayOfWeek day) throws CommandException {
        if (!isDayInPeriod(period, day)) {
            throw new CommandException(MESSAGE_SHIFT_NOT_IN_RANGE);
        }
    }

    private static boolean isDayInPeriod(Period period, DayOfWeek day) {
        long result = period.toList().stream()
                .filter(d -> d.getDayOfWeek().equals(day))
                .count();
        return result != 0;
    }
}

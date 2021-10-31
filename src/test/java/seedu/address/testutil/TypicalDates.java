package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_NEXT_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TUE;

import java.time.LocalDate;

import seedu.address.model.lesson.Date;

/**
 * A utility class containing a list of {@code Dates} objects to be used in tests.
 */
public class TypicalDates {
    public static final Date DATE_CURRENT_WEEK = DateUtil.build(LocalDate.now());
    public static final Date DATE_ONE_WEEK_AGO = DateUtil.build(LocalDate.now().minusWeeks(1));
    public static final Date DATE_ONE_WEEK_LATER = DateUtil.build(LocalDate.now().plusWeeks(1));
    public static final Date DATE_TWO_WEEKS_LATER = DateUtil.build(LocalDate.now().plusWeeks(2));
}

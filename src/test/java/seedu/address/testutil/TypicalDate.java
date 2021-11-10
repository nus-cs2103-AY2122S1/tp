package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;

import java.time.LocalDate;

import seedu.address.model.Countdown;

public class TypicalDate {
    public static final LocalDate MY_WEDDING_DATE = VALID_DATE;

    private TypicalDate() {
    } // prevents instantiation

    /**
     * Returns a {@code Countdown} with the typical wedding date.
     */
    public static Countdown getTypicalCountdown() {
        Countdown cd = new Countdown();
        cd.setDate(MY_WEDDING_DATE);
        return cd;
    }

}

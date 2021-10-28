package seedu.address.logic.commands;

import static seedu.address.model.person.PersonTestUtil.createPeriod;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Period;

public class CommandUtilTest {

    @Test
    public void test_dayInPeriod() {
        Period testPeriod = createPeriod(1, 1);
        assertThrows(CommandException.class, () ->
                CommandUtil.checkDateForDayOfWeek(testPeriod, DayOfWeek.TUESDAY));
        Period testPeriod2 = createPeriod(1, 6);
        assertThrows(CommandException.class, () ->
                CommandUtil.checkDateForDayOfWeek(testPeriod2, DayOfWeek.SUNDAY));
    }
}

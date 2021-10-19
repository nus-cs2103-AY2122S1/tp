package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class viewShiftCommandTest {

    private final viewShiftCommand firstCommand = new viewShiftCommand(DayOfWeek.MONDAY,
            seedu.address.logic.commands.viewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.NOON);
    private final viewShiftCommand firstCommandDuplicate = new viewShiftCommand(DayOfWeek.MONDAY,
            seedu.address.logic.commands.viewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.NOON);
    private final viewShiftCommand secondCommand = new viewShiftCommand(DayOfWeek.TUESDAY,
            seedu.address.logic.commands.viewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.of(17, 0));
    private final viewShiftCommand thirdCommand = new viewShiftCommand(DayOfWeek.WEDNESDAY, 0, null);
    private final viewShiftCommand fourthCommand = new viewShiftCommand(DayOfWeek.THURSDAY, 0, null);
    private final viewShiftCommand fifthCommand = new viewShiftCommand(DayOfWeek.FRIDAY,
            seedu.address.logic.commands.viewShiftCommand.INVALID_SLOT_NUMBER, null);
    private final viewShiftCommand sixthCommand = new viewShiftCommand(DayOfWeek.SATURDAY, 0,
            LocalTime.of(10, 0));

    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        // Object should equal itself
        assertEquals(firstCommand, firstCommand);

        // Not case-sensitive
        assertEquals(firstCommand, firstCommandDuplicate);

        // Tests
        assertNotEquals(firstCommand, secondCommand);
        assertNotEquals(firstCommand, fourthCommand);
        assertNotEquals(firstCommand, fifthCommand);

        // Not equal null
        assertNotEquals(null, firstCommand);
    }
}

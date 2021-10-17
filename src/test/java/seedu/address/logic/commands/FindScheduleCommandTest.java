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


public class FindScheduleCommandTest {

    private final FindScheduleCommand firstCommand = new FindScheduleCommand(DayOfWeek.MONDAY,
            FindScheduleCommand.INVALID_SLOT_NUMBER, LocalTime.NOON);
    private final FindScheduleCommand firstCommandDuplicate = new FindScheduleCommand(DayOfWeek.MONDAY,
            FindScheduleCommand.INVALID_SLOT_NUMBER, LocalTime.NOON);
    private final FindScheduleCommand secondCommand = new FindScheduleCommand(DayOfWeek.TUESDAY,
            FindScheduleCommand.INVALID_SLOT_NUMBER, LocalTime.of(17, 0));
    private final FindScheduleCommand thirdCommand = new FindScheduleCommand(DayOfWeek.WEDNESDAY, 0, null);
    private final FindScheduleCommand fourthCommand = new FindScheduleCommand(DayOfWeek.THURSDAY, 0, null);
    private final FindScheduleCommand fifthCommand = new FindScheduleCommand(DayOfWeek.FRIDAY,
            FindScheduleCommand.INVALID_SLOT_NUMBER, null);
    private final FindScheduleCommand sixthCommand = new FindScheduleCommand(DayOfWeek.SATURDAY, 0,
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

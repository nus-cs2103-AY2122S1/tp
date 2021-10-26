package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.model.person.PersonTestUtil.createPeriod;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Period;
import seedu.address.model.person.predicates.PersonIsWorkingPredicate;


public class ViewShiftCommandTest {

    private static final Period TEST_PERIOD = createPeriod(1, 5);

    private final ViewShiftCommand firstCommand = new ViewShiftCommand(DayOfWeek.MONDAY,
            ViewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.NOON, TEST_PERIOD);
    private final ViewShiftCommand firstCommandDuplicate = new ViewShiftCommand(DayOfWeek.MONDAY,
            ViewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.NOON, TEST_PERIOD);
    private final ViewShiftCommand secondCommand = new ViewShiftCommand(DayOfWeek.TUESDAY,
            ViewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.of(17, 0), TEST_PERIOD);
    private final ViewShiftCommand thirdCommand = new ViewShiftCommand(DayOfWeek.WEDNESDAY, 0,
            null, TEST_PERIOD);
    private final ViewShiftCommand fourthCommand = new ViewShiftCommand(DayOfWeek.THURSDAY, 0,
            null, TEST_PERIOD);
    private final ViewShiftCommand fifthCommand = new ViewShiftCommand(DayOfWeek.FRIDAY,
            ViewShiftCommand.INVALID_SLOT_NUMBER, null, TEST_PERIOD);
    private final ViewShiftCommand sixthCommand = new ViewShiftCommand(DayOfWeek.SATURDAY, 0,
            LocalTime.of(10, 0), TEST_PERIOD);

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

    @Test
    public void test_executeViewShiftByTime() {
        // Test successes
        PersonIsWorkingPredicate predicate1 = new PersonIsWorkingPredicate(DayOfWeek.MONDAY,
                ViewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.NOON, TEST_PERIOD);
        expectedModel.updateFilteredPersonList(predicate1);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList());

        PersonIsWorkingPredicate predicate2 = new PersonIsWorkingPredicate(DayOfWeek.TUESDAY,
                ViewShiftCommand.INVALID_SLOT_NUMBER, LocalTime.of(17, 0), TEST_PERIOD);
        expectedModel.updateFilteredPersonList(predicate1);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList());
    }

    @Test
    public void test_executeViewShiftBySlotNum() {
        // Test successes
        PersonIsWorkingPredicate predicate1 = new PersonIsWorkingPredicate(DayOfWeek.WEDNESDAY,
                0, null, TEST_PERIOD);
        expectedModel.updateFilteredPersonList(predicate1);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList());

        PersonIsWorkingPredicate predicate2 = new PersonIsWorkingPredicate(DayOfWeek.THURSDAY,
                0, null, TEST_PERIOD);
        expectedModel.updateFilteredPersonList(predicate2);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredPersonList());
    }
}

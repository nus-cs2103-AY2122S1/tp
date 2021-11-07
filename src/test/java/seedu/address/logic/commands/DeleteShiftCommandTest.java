package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Schedule;
import seedu.address.model.person.Slot;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ScheduleBuilder;


public class DeleteShiftCommandTest {

    private static final LocalDate[] dates = DateTimeUtil.getDisplayedDateArray();
    private static final LocalDate START_DATE = dates[0];
    private static final LocalDate END_DATE = dates[1];

    @Test
    public void equals() {
        DeleteShiftCommand firstCommand = new DeleteShiftCommand(Index.fromOneBased(1), new Name("testingName"),
                "monday-1", START_DATE, END_DATE);
        DeleteShiftCommand secondCommand = new DeleteShiftCommand(Index.fromOneBased(1), new Name("testingName"),
                "MONDAY-1", START_DATE, END_DATE);
        DeleteShiftCommand thirdCommand = new DeleteShiftCommand(Index.fromOneBased(2), new Name("testingName"),
                "monday-1", START_DATE, END_DATE);
        DeleteShiftCommand fourthCommand = new DeleteShiftCommand(Index.fromOneBased(1), new Name("differentName"),
                "monday-1", START_DATE, END_DATE);
        DeleteShiftCommand fifthCommand = new DeleteShiftCommand(Index.fromOneBased(1), new Name("testingName"),
                "tuesday-1", START_DATE, END_DATE);

        // Object should equal itself
        assertEquals(firstCommand, firstCommand);

        // Not case sensitive
        assertEquals(firstCommand, secondCommand);

        assertNotEquals(firstCommand, thirdCommand);
        assertNotEquals(firstCommand, fourthCommand);
        assertNotEquals(firstCommand, fifthCommand);

        // Not equal null
        assertNotEquals(null, firstCommand);
    }

    @Test
    public void execute_command_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        DeleteShiftCommand nameNotFoundCommand = new DeleteShiftCommand(null, new Name("Cannot find"),
                "monday-1", START_DATE, END_DATE);
        DeleteShiftCommand indexOutOfBoundCommand = new DeleteShiftCommand(Index.fromOneBased(100), null,
                "monday-1", START_DATE, END_DATE);
        DeleteShiftCommand personIsNullCommand = new DeleteShiftCommand(null, null,
                "monday-1", START_DATE, END_DATE);

        assertCommandFailure(nameNotFoundCommand, model, Messages.MESSAGE_INVALID_PERSON_SEARCHED);
        assertCommandFailure(indexOutOfBoundCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertCommandFailure(personIsNullCommand, model, DeleteShiftCommand.MESSAGE_USAGE);
    }

    @Test
    public void execute_name_success() {
        Schedule aliceSchedule = new Schedule();
        aliceSchedule.addShift(DayOfWeek.MONDAY, Slot.MORNING, START_DATE, END_DATE);

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").withSchedule(new ScheduleBuilder(aliceSchedule)).build();
        Person aliceCopy = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").build();

        model.addPerson(alice);
        expectedModel.addPerson(aliceCopy);

        DeleteShiftCommand command = new DeleteShiftCommand(null, alice.getName(),
                "monday-0", START_DATE, END_DATE);

        assertCommandSuccess(command, model, String.format(DeleteShiftCommand.MESSAGE_DELETE_SHIFT_SUCCESS,
                alice.getName(), DayOfWeek.MONDAY, Slot.MORNING), expectedModel);
    }

    @Test
    public void execute_index_success() {
        Schedule aliceSchedule = new Schedule();
        aliceSchedule.addShift(DayOfWeek.MONDAY, Slot.MORNING, START_DATE, END_DATE);

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").withSchedule(new ScheduleBuilder(aliceSchedule)).build();
        Person aliceCopy = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").build();

        model.addPerson(alice);
        expectedModel.addPerson(aliceCopy);

        DeleteShiftCommand command = new DeleteShiftCommand(Index.fromOneBased(1), null,
                "monday-0", START_DATE, END_DATE);

        assertCommandSuccess(command, model, String.format(DeleteShiftCommand.MESSAGE_DELETE_SHIFT_SUCCESS,
                alice.getName(), DayOfWeek.MONDAY, Slot.MORNING), expectedModel);
    }

    @Test
    public void execute_shiftDoesNotExist_commandFailure() {
        Model model = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").build();

        model.addPerson(alice);

        DeleteShiftCommand command = new DeleteShiftCommand(null, alice.getName(),
                "monday-0", START_DATE, END_DATE);

        assertCommandFailure(command, model, DeleteShiftCommand.MESSAGE_SHIFT_DOESNT_EXIST);
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SetShiftTimeCommand.MESSAGE_SET_SHIFT_TIME_SUCCESS;
import static seedu.address.logic.commands.SetShiftTimeCommand.MESSAGE_USAGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.InvalidShiftTimeException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.testutil.PersonBuilder;


public class SetShiftTimeCommandTest {


    public static final LocalTime[] TIMES = new LocalTime[]{LocalTime.of(18, 0), LocalTime.of(19,
            0)};
    private static final LocalDate START_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate END_DATE = START_DATE.plusDays(7);


    @Test
    public void equals() {
        SetShiftTimeCommand firstCommand = new SetShiftTimeCommand(Index.fromOneBased(1), new Name("testingName"),
                "monday-1", TIMES, START_DATE, END_DATE);
        SetShiftTimeCommand secondCommand = new SetShiftTimeCommand(Index.fromOneBased(1), new Name("testingName"),
                "MONDAY-1", TIMES, START_DATE, END_DATE);
        SetShiftTimeCommand thirdCommand = new SetShiftTimeCommand(Index.fromOneBased(2), new Name("testingName"),
                "monday-1", TIMES, START_DATE, END_DATE);
        SetShiftTimeCommand fourthCommand = new SetShiftTimeCommand(Index.fromOneBased(1), new Name("differentName"),
                "monday-1", TIMES, START_DATE, END_DATE);
        SetShiftTimeCommand fifthCommand = new SetShiftTimeCommand(Index.fromOneBased(1), new Name("testingName"),
                "tuesday-1", TIMES, START_DATE, END_DATE);
        SetShiftTimeCommand sixthCommand = new SetShiftTimeCommand(Index.fromOneBased(1), new Name("testingName"),
                "tuesday-1", new LocalTime[]{LocalTime.of(18, 0),
                LocalTime.of(20, 0)}, START_DATE, END_DATE);

        // Object should equal itself
        assertEquals(firstCommand, firstCommand);

        // Not case sensitive
        assertEquals(firstCommand, secondCommand);

        assertNotEquals(firstCommand, thirdCommand);
        assertNotEquals(firstCommand, fourthCommand);
        assertNotEquals(firstCommand, fifthCommand);
        assertNotEquals(firstCommand, sixthCommand);

        // Not equal null
        assertNotEquals(null, firstCommand);
    }

    @Test
    public void execute_command_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        SetShiftTimeCommand nameNotFoundCommand = new SetShiftTimeCommand(null, new Name("Cannot found"),
                "monday-1", TIMES, START_DATE, END_DATE);
        SetShiftTimeCommand indexOutOfBoundCommand = new SetShiftTimeCommand(Index.fromOneBased(100),
                new Name("Alice Pauline"), "monday-1", TIMES, START_DATE, END_DATE);
        SetShiftTimeCommand personIsNullCommand = new SetShiftTimeCommand(null, null,
                "monday-1", TIMES, START_DATE, END_DATE);

        assertCommandFailure(indexOutOfBoundCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertCommandFailure(personIsNullCommand, model, MESSAGE_USAGE);
    }

    @Test
    public void execute_name() throws InvalidShiftTimeException {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();
        Person copyOfAlice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();

        model.addPerson(alice);
        expectedModel.addPerson(copyOfAlice);

        SetShiftTimeCommand firstCommand = new SetShiftTimeCommand(null, alice.getName(),
                "tuesday-1", TIMES, START_DATE, END_DATE);

        expectedModel.findPersonByName(new Name("Alice Pauline")).setShiftTime(DayOfWeek.TUESDAY, Slot.AFTERNOON,
                TIMES[0], TIMES[1], START_DATE, END_DATE);
        assertCommandSuccess(firstCommand, model, String.format(MESSAGE_SET_SHIFT_TIME_SUCCESS, alice.getName(),
                DayOfWeek.TUESDAY, Slot.AFTERNOON, START_DATE, END_DATE, TIMES[0], TIMES[1]), expectedModel);
    }

    @Test
    public void execute_index() throws InvalidShiftTimeException {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();

        Person copyOfAlice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();

        model.addPerson(alice);
        expectedModel.addPerson(copyOfAlice);

        SetShiftTimeCommand firstCommand = new SetShiftTimeCommand(Index.fromOneBased(1), null,
                "tuesday-1", TIMES, START_DATE, END_DATE);

        expectedModel.findPersonByName(new Name("Alice Pauline")).setShiftTime(DayOfWeek.TUESDAY, Slot.AFTERNOON,
                TIMES[0], TIMES[1], START_DATE, END_DATE);
        assertCommandSuccess(firstCommand, model, String.format(MESSAGE_SET_SHIFT_TIME_SUCCESS, alice.getName(),
                DayOfWeek.TUESDAY, Slot.AFTERNOON, START_DATE, END_DATE, TIMES[0], TIMES[1]), expectedModel);
    }

    @Test
    public void execute_throw_exception() {
        Model model = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000000")
                .withStatus("fulltime").withTags("friends").build();
        model.addPerson(alice);

        LocalTime[] wrongOrderTimes = new LocalTime[]{TIMES[1], TIMES[0]};
        LocalTime[] outOfBoundMorningTimes = new LocalTime[]{LocalTime.of(9, 10),
                LocalTime.of(10, 11)};
        LocalTime[] outOfBoundAfternoonTimes = new LocalTime[]{LocalTime.of(12, 0),
                LocalTime.of(23, 59)};

        SetShiftTimeCommand wrongOrderTimesCommand = new SetShiftTimeCommand(null, alice.getName(),
                "monday-1", wrongOrderTimes, START_DATE, END_DATE);
        SetShiftTimeCommand outOfBoundMorningCommand = new SetShiftTimeCommand(null, alice.getName(),
                "monday-0", outOfBoundMorningTimes, START_DATE, END_DATE);
        SetShiftTimeCommand outOfBoundAfternoonCommand = new SetShiftTimeCommand(null, alice.getName(),
                "monday-1", outOfBoundAfternoonTimes, START_DATE, END_DATE);

        assertThrows(CommandException.class, () -> wrongOrderTimesCommand.execute(model));
        assertThrows(CommandException.class, () -> outOfBoundMorningCommand.execute(model));
        assertThrows(CommandException.class, () -> outOfBoundAfternoonCommand.execute(model));
    }
}

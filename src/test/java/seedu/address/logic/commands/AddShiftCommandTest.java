package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.AddShiftCommand.MESSAGE_ADD_SHIFT_SUCCESS;
import static seedu.address.logic.commands.AddShiftCommand.MESSAGE_USAGE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.testutil.PersonBuilder;


public class AddShiftCommandTest {
    private static final LocalDate START_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate END_DATE = START_DATE.plusDays(7);

    @Test
    public void equals() {
        AddShiftCommand firstCommand = new AddShiftCommand(Index.fromOneBased(1), new Name("testingName"),
                "monday-1", START_DATE, END_DATE);
        AddShiftCommand secondCommand = new AddShiftCommand(Index.fromOneBased(1), new Name("testingName"),
                "MONDAY-1", START_DATE, END_DATE);
        AddShiftCommand thirdCommand = new AddShiftCommand(Index.fromOneBased(2), new Name("testingName"),
                "monday-1", START_DATE, END_DATE);
        AddShiftCommand fourthCommand = new AddShiftCommand(Index.fromOneBased(1), new Name("differentName"),
                "monday-1", START_DATE, END_DATE);
        AddShiftCommand fifthCommand = new AddShiftCommand(Index.fromOneBased(1), new Name("testingName"),
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

        AddShiftCommand nameNotFoundCommand = new AddShiftCommand(null, new Name("Cannot found"),
                "monday-1", START_DATE, END_DATE);
        AddShiftCommand indexOutOfBoundCommand = new AddShiftCommand(Index.fromOneBased(100),
                new Name("Alice Pauline"), "monday-1", START_DATE, END_DATE);
        AddShiftCommand personIsNullCommand = new AddShiftCommand(null, null, "monday-1",
                START_DATE, END_DATE);

        assertCommandFailure(indexOutOfBoundCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertCommandFailure(personIsNullCommand, model, MESSAGE_USAGE);
    }

    @Test
    public void execute_name() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").build();
        Person copyOfAlice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").build();

        model.addPerson(alice);
        expectedModel.addPerson(copyOfAlice);

        AddShiftCommand firstCommand = new AddShiftCommand(null, alice.getName(),
                "tuesday-1", START_DATE, END_DATE);

        expectedModel.findPersonByName(new Name("Alice Pauline")).addShift(DayOfWeek.TUESDAY,
                Slot.AFTERNOON, START_DATE, END_DATE);
        assertCommandSuccess(firstCommand, model, String.format(MESSAGE_ADD_SHIFT_SUCCESS, alice.getName(),
                DayOfWeek.TUESDAY, Slot.AFTERNOON), expectedModel);
    }

    @Test
    public void execute_index() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").build();
        Person copyOfAlice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000").withStatus("fulltime")
                .withTags("friends").build();

        model.addPerson(alice);
        expectedModel.addPerson(copyOfAlice);

        AddShiftCommand firstCommand = new AddShiftCommand(Index.fromOneBased(1), null,
                "tuesday-1", START_DATE, END_DATE);

        expectedModel.findPersonByName(new Name("Alice Pauline")).addShift(DayOfWeek.TUESDAY,
                Slot.AFTERNOON, START_DATE, END_DATE);
        assertCommandSuccess(firstCommand, model, String.format(MESSAGE_ADD_SHIFT_SUCCESS, alice.getName(),
                DayOfWeek.TUESDAY, Slot.AFTERNOON), expectedModel);
    }
}

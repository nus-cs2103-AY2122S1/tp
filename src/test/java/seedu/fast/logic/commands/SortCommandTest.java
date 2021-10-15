package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.testutil.TypicalPersons.BENSON;
import static seedu.fast.testutil.TypicalPersons.ELLE;
import static seedu.fast.testutil.TypicalPersons.GRABAHAN;
import static seedu.fast.testutil.TypicalPersons.JOE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.sort.SortByAppointment;
import seedu.fast.commons.util.sort.SortByName;
import seedu.fast.commons.util.sort.SortByPriority;
import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;
import seedu.fast.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        expectedModel = new ModelManager();

        getCustomPersons(model, GRABAHAN, BENSON, ELLE, JOE);
    }

    @Test
    public void equals() {
        SortByName sortByName = new SortByName();
        SortByPriority sortByPriority = new SortByPriority();
        SortByAppointment sortByAppointment = new SortByAppointment();

        SortCommand sortNameCommand = new SortCommand(sortByName, SortByName.KEYWORD);
        SortCommand sortPriorityCommand = new SortCommand(sortByPriority, SortByPriority.KEYWORD);
        SortCommand sortAppointmentCommand = new SortCommand(sortByAppointment, sortByAppointment.KEYWORD);

        // same object -> returns true
        assertTrue(sortNameCommand.equals(sortNameCommand));

        // same values -> returns true
        SortCommand sortPriorityCommandCopy = new SortCommand(sortByPriority, SortByPriority.KEYWORD);
        assertTrue(sortPriorityCommand.equals(sortPriorityCommandCopy));

        // different types -> returns false
        assertFalse(sortAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(sortAppointmentCommand.equals(null));

        // different person -> returns false
        assertFalse(sortPriorityCommand.equals(sortNameCommand));
    }

    @Test
    public void execute_sortByName_listSortedByName() {
        getCustomPersons(expectedModel, BENSON, ELLE, GRABAHAN, JOE);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortByName.KEYWORD);
        SortCommand command = new SortCommand(new SortByName(), SortByName.KEYWORD);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByAppointment_listSortedByAppointment() {
        getCustomPersons(expectedModel, ELLE, BENSON, GRABAHAN, JOE);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortByAppointment.KEYWORD);
        SortCommand command = new SortCommand(new SortByAppointment(), SortByAppointment.KEYWORD);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortByPriority_listSortedByPriority() {
        getCustomPersons(expectedModel, JOE, GRABAHAN, BENSON, ELLE);

        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortByPriority.KEYWORD);
        SortCommand command = new SortCommand(new SortByPriority(), SortByPriority.KEYWORD);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    /**
     * Adds the specified Persons into the Model
     * @param model The model to add persons.
     * @param persons Variable length of Person to be added.
     */
    private void getCustomPersons(Model model, Person ...persons) {
        for (Person p: persons) {
            model.addPerson(p);
        }
    }

}

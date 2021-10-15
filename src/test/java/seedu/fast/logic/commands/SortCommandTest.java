package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.testutil.TypicalPersons.GRABAHAN;
import static seedu.fast.testutil.TypicalPersons.BENSON;
import static seedu.fast.testutil.TypicalPersons.ELLE;
import static seedu.fast.testutil.TypicalPersons.JOE;


import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fast.commons.util.sort.SortByAppointment;
import seedu.fast.commons.util.sort.SortByName;
import seedu.fast.commons.util.sort.SortByPriority;
import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;
import seedu.fast.model.UserPrefs;
import seedu.fast.model.person.NameContainsKeywordsPredicate;

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

        model.addPerson(GRABAHAN);
        model.addPerson(BENSON);
        model.addPerson(ELLE);
        model.addPerson(JOE);
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
        expectedModel.addPerson(BENSON);
        expectedModel.addPerson(ELLE);
        expectedModel.addPerson(GRABAHAN);
        expectedModel.addPerson(JOE);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortByName.KEYWORD);
        SortCommand command = new SortCommand(new SortByName(), SortByName.KEYWORD);
        assertCommandSuccess(command, model, expectedMessage,  expectedModel);
    }

    @Test
    public void execute_sortByAppointment_listSortedByAppointment() {
        expectedModel.addPerson(ELLE);
        expectedModel.addPerson(BENSON);
        expectedModel.addPerson(GRABAHAN);
        expectedModel.addPerson(JOE);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortByAppointment.KEYWORD);
        SortCommand command = new SortCommand(new SortByAppointment(), SortByAppointment.KEYWORD);
        assertCommandSuccess(command, model, expectedMessage,  expectedModel);
    }

    @Test
    public void execute_sortByPriority_listSortedByPriority() {
        expectedModel.addPerson(JOE);
        expectedModel.addPerson(GRABAHAN);
        expectedModel.addPerson(BENSON);
        expectedModel.addPerson(ELLE);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, SortByPriority.KEYWORD);
        SortCommand command = new SortCommand(new SortByPriority(), SortByPriority.KEYWORD);
        assertCommandSuccess(command, model, expectedMessage,  expectedModel);
    }

}

package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;
import static safeforhall.testutil.TypicalPersons.ALICE;
import static safeforhall.testutil.TypicalPersons.CARL;
import static safeforhall.testutil.TypicalPersons.ELLE;
import static safeforhall.testutil.TypicalPersons.FIONA;
import static safeforhall.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.NameMissedDeadlinePredicate;
import safeforhall.model.person.NameNearLastDatePredicate;
import safeforhall.model.person.Person;
import safeforhall.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class DeadlineCommandTest {
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DeadlineCommand firstDeadlineCommand = new DeadlineCommand("f", new LastDate("10-10-2021"));
        DeadlineCommand secondDeadlineCommand = new DeadlineCommand("c", new LastDate("12-10-2021"));

        // same object -> returns true
        assertTrue(firstDeadlineCommand.equals(firstDeadlineCommand));

        // same values -> returns true
        DeadlineCommand findFirstCommandCopy = new DeadlineCommand("f", new LastDate("10-10-2021"));
        assertTrue(firstDeadlineCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(firstDeadlineCommand.equals(1));

        // null -> returns false
        assertFalse(firstDeadlineCommand.equals(null));

        // different person -> returns false
        assertFalse(firstDeadlineCommand.equals(secondDeadlineCommand));
    }

    @Test
    public void execute_oneLastDateFetNotFound() {
        String expectedMessage = DeadlineCommand.MESSAGE_SUCCESS_FET;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("f", new LastDate("17-09-2021"));
        DeadlineCommand command = new DeadlineCommand("f", new LastDate("17-09-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_twoLastDateArtNotFound() {
        String expectedMessage = DeadlineCommand.MESSAGE_SUCCESS_ART;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("c", new LastDate("10-09-2021"),
                new LastDate("15-09-2021"));
        DeadlineCommand command = new DeadlineCommand("c", new LastDate("10-09-2021"), new LastDate("15-09-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.EMPTY_LIST, expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_oneLastDateFet() {
        String expectedMessage = DeadlineCommand.MESSAGE_SUCCESS_FET;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("f", new LastDate("17-10-2021"));
        DeadlineCommand command = new DeadlineCommand("f", new LastDate("17-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Person> validPeople = new ArrayList<>();
        validPeople.add(CARL);
        assertEquals(validPeople, expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_oneLastDateCollection() {
        String expectedMessage = DeadlineCommand.MESSAGE_SUCCESS_ART;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"));
        DeadlineCommand command = new DeadlineCommand("c", new LastDate("10-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Person> validPeople = new ArrayList<>();
        validPeople.add(ALICE);
        validPeople.add(CARL);
        assertEquals(validPeople, expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_twoLastDateFet() {
        String expectedMessage = DeadlineCommand.MESSAGE_SUCCESS_FET;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("f", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        DeadlineCommand command = new DeadlineCommand("f", new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Person> validPeople = new ArrayList<>();
        validPeople.add(ALICE);
        validPeople.add(ELLE);
        assertEquals(validPeople, expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_twoLastDateCollection() {
        String expectedMessage = DeadlineCommand.MESSAGE_SUCCESS_ART;
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"),
                new LastDate("15-10-2021"));
        DeadlineCommand command = new DeadlineCommand("c", new LastDate("10-10-2021"), new LastDate("15-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Person> validPeople = new ArrayList<>();
        validPeople.add(ALICE);
        validPeople.add(CARL);
        validPeople.add(FIONA);
        assertEquals(validPeople, expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_oneLateFet() {
        String expectedMessage = DeadlineCommand.MESSAGE_SUCCESS_MISSED_FET;
        NameMissedDeadlinePredicate predicate = new NameMissedDeadlinePredicate("f", new LastDate("17-10-2021"));
        DeadlineCommand command = new DeadlineCommand("lf", new LastDate("17-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Person> validPeople = new ArrayList<>();
        validPeople.add(ALICE);
        validPeople.add(ELLE);
        assertEquals(validPeople, expectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_oneLateCollection() {
        String expectedMessage = DeadlineCommand.MESSAGE_SUCCESS_MISSED_ART;
        NameMissedDeadlinePredicate predicate = new NameMissedDeadlinePredicate("c", new LastDate("10-10-2021"));
        DeadlineCommand command = new DeadlineCommand("lc", new LastDate("10-10-2021"));
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Person> validPeople = new ArrayList<>();
        validPeople.add(GEORGE);
        assertEquals(validPeople, expectedModel.getFilteredPersonList());
    }
}

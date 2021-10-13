package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparators.SortByAddress;
import seedu.address.model.person.comparators.SortByClientID;
import seedu.address.model.person.comparators.SortByCurrentPlan;
import seedu.address.model.person.comparators.SortByDisposableIncome;
import seedu.address.model.person.comparators.SortByEmail;
import seedu.address.model.person.comparators.SortByLastMet;
import seedu.address.model.person.comparators.SortByName;
import seedu.address.model.person.comparators.SortByRiskAppetite;
import seedu.address.model.person.comparators.SortDirection;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        SortDirection ascending = new SortDirection("asc");
        SortDirection descending = new SortDirection("dsc");
        SortByAddress sortByAddressAscending = new SortByAddress(ascending);
        SortByAddress sortByAddressDesceding = new SortByAddress(descending);
        SortByClientID sortByClientIdAscending = new SortByClientID(ascending);
        SortByClientID sortByClientIdDescending = new SortByClientID(descending);

        SortCommand sortCommandAddressAsc = new SortCommand(sortByAddressAscending, "Address");
        SortCommand sortCommandAddressDsc = new SortCommand(sortByAddressDesceding, "Address");
        SortCommand sortCommandClientIdAsc = new SortCommand(sortByClientIdAscending, "Client ID");
        SortCommand sortCommandClientIdDsc = new SortCommand(sortByClientIdDescending, "Client Id");

        // same object -> returns true
        assertTrue(sortCommandAddressAsc.equals(sortCommandAddressAsc));

        // same values -> returns true
        SortCommand sortAddressSortCommandAsc = new SortCommand(sortByAddressAscending, "Address");
        assertTrue(sortCommandAddressAsc.equals(sortAddressSortCommandAsc));

        // different sort direction -> returns false
        assertFalse(sortCommandAddressAsc.equals(sortCommandAddressDsc));

        // different fields -> returns false
        assertFalse(sortCommandClientIdAsc.equals(sortCommandClientIdDsc));

        // null -> returns false
        assertFalse(sortCommandAddressAsc.equals(null));

    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToClientId() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Client ID");
        SortDirection direction = new SortDirection("dsc");
        Comparator<Person> sorter = new SortByClientID(direction);
        SortCommand command = new SortCommand(sorter, "Client ID");
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToName() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Risk Appetite");
        SortDirection direction = new SortDirection("asc");
        Comparator<Person> sorter = new SortByName(direction);
        SortCommand command = new SortCommand(sorter, "Risk Appetite");
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToEmail() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Risk Appetite");
        SortDirection direction = new SortDirection("asc");
        Comparator<Person> sorter = new SortByEmail(direction);
        SortCommand command = new SortCommand(sorter, "Risk Appetite");
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, GEORGE, DANIEL, CARL, BENSON, FIONA, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToAddress() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Risk Appetite");
        SortDirection direction = new SortDirection("dsc");
        Comparator<Person> sorter = new SortByAddress(direction);
        SortCommand command = new SortCommand(sorter, "Risk Appetite");
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA, GEORGE, BENSON, ALICE, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToRiskAppetite() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Risk Appetite");
        SortDirection direction = new SortDirection("asc");
        Comparator<Person> sorter = new SortByRiskAppetite(direction);
        SortCommand command = new SortCommand(sorter, "Risk Appetite");
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, ALICE, CARL, FIONA, BENSON, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToDisposableIncome() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Risk Appetite");
        SortDirection direction = new SortDirection("asc");
        Comparator<Person> sorter = new SortByDisposableIncome(direction);
        SortCommand command = new SortCommand(sorter, "Risk Appetite");
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(GEORGE, CARL, ALICE, FIONA, BENSON, ELLE, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToCurrentPlan() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Risk Appetite");
        SortDirection direction = new SortDirection("asc");
        Comparator<Person> sorter = new SortByCurrentPlan(direction);
        SortCommand command = new SortCommand(sorter, "Risk Appetite");
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, GEORGE, DANIEL, CARL, BENSON, ALICE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_listSortedAccordingToLastMet() {
        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "Risk Appetite");
        SortDirection direction = new SortDirection("asc");
        Comparator<Person> sorter = new SortByLastMet(direction);
        SortCommand command = new SortCommand(sorter, "Risk Appetite");
        expectedModel.sortFilteredPersonList(sorter);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, GEORGE, FIONA, CARL, ALICE, BENSON), model.getFilteredPersonList());
    }

}

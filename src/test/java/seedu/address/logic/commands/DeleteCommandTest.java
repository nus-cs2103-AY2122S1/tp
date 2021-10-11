package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersons;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteAllUnfilteredList_success() {
        DeleteCommand deleteCommand = DeleteCommand.all();

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_ALL_PERSONS_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs());
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteAllFilteredList_success() {
        DeleteCommand deleteCommand = DeleteCommand.all();

        showAllPersons(model);
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());

        showPersons(model, persons.get(0), persons.get(1), persons.get(2));

        assertTrue(model.getFilteredPersonList().size() == 3);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_ALL_PERSONS_SUCCESS;

        ModelManager expectedModel = new ModelManager(new AddressBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteAllShownUnfilteredList_success() {
        DeleteCommand deleteCommand = DeleteCommand.allShown();

        showAllPersons(model);
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());

        StringBuilder resultSb = new StringBuilder(DeleteCommand.MESSAGE_DELETE_ALL_SHOWN_PERSONS_SUCCESS);
        for (Person person : persons) {
            resultSb.append("\n");
            resultSb.append(person);
        }

        String expectedMessage = String.format(resultSb.toString(), persons.size());

        ModelManager expectedModel = new ModelManager(new AddressBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteAllShownFilteredList_success() {
        DeleteCommand deleteCommand = DeleteCommand.allShown();

        showAllPersons(model);
        List<Person> persons = new ArrayList<>(model.getFilteredPersonList());

        Person[] personsToDelete = {persons.get(0), persons.get(1), persons.get(2)};

        showPersons(model, personsToDelete);

        System.out.println(model.getFilteredPersonList());

        StringBuilder resultSb = new StringBuilder(DeleteCommand.MESSAGE_DELETE_ALL_SHOWN_PERSONS_SUCCESS);
        for (Person person : personsToDelete) {
            resultSb.append("\n");
            resultSb.append(person);
        }

        String expectedMessage = String.format(resultSb.toString(), personsToDelete.length);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        for (Person person : personsToDelete) {
            expectedModel.deletePerson(person);
        }
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        DeleteCommand deleteAllCommand = DeleteCommand.all();
        DeleteCommand deleteAllShownCommand = DeleteCommand.allShown();

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // same quantity -> returns true
        DeleteCommand deleteAllCommandCopy = DeleteCommand.all();
        assertTrue(deleteAllCommand.equals(deleteAllCommandCopy));
        DeleteCommand deleteAllShownCommandCopy = DeleteCommand.allShown();
        assertTrue(deleteAllShownCommand.equals(deleteAllShownCommandCopy));

        // different quantity -> returns false
        assertFalse(deleteFirstCommand.equals(deleteAllCommand));
        assertFalse(deleteAllCommand.equals(deleteAllShownCommand));
        assertFalse(deleteAllShownCommand.equals(deleteAllCommand));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }


    /**
     * Updates {@code model}'s filtered list to show everyone.
     */
    private void showAllPersons(Model model) {
        model.updateFilteredPersonList(p -> true);

        assertTrue(model.getFilteredPersonList().size() == model.getAddressBook().getPersonList().size());
    }
}

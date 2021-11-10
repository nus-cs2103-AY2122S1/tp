package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Shortcut;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), new Shortcut());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNameUnfilteredList_success() {
        Person personToDelete = CARL;
        DeleteCommand deleteCommand = new DeleteCommand(new Name("Carl Kurz"));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
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

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNameFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = ALICE;
        DeleteCommand deleteCommand = new DeleteCommand(new Name("Alice Pauline"));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getCountdown(),
                new UserPrefs(), new Shortcut());
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
    public void execute_validTagsFound_success() throws CommandException {
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_TAG_SUCCESS, "[friends]");
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
                Arrays.asList("friends".split("\\s+"))
        );
        DeleteCommand deleteTagCommand = new DeleteCommand(predicate, "friends");
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
                new UserPrefs(), new Shortcut());
        expectedModel.deletePerson(ALICE);
        expectedModel.deletePerson(BENSON);
        expectedModel.deletePerson(DANIEL);
        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noTagsFound() throws CommandException {
        String expectedMessage = String.format(DeleteCommand.MESSAGE_UNKNOWN_PERSON_TAG, "[random]");
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(
                Arrays.asList("random".split("\\s+"))
        );
        DeleteCommand deleteTagcommand = new DeleteCommand(predicate, "random");
        assertCommandFailure(deleteTagcommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Name nameNotFound = new Name("Benson Meier");

        DeleteCommand deleteCommand = new DeleteCommand(nameNotFound);

        assertCommandFailure(deleteCommand, model, String.format(DeleteCommand.MESSAGE_UNKNOWN_PERSON_NAME,
                nameNotFound));
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);
        DeleteCommand deleteAliceCommand = new DeleteCommand(new Name("Alice Pauline"));
        DeleteCommand deleteCarlCommand = new DeleteCommand(new Name("Carl Kurz"));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
        assertTrue(deleteAliceCommand.equals(deleteAliceCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));
        DeleteCommand deleteAliceCommandCopy = new DeleteCommand(new Name("Alice Pauline"));
        assertTrue(deleteAliceCommand.equals(deleteAliceCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));
        assertFalse(deleteAliceCommand.equals("Alice Pauline"));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));
        assertFalse(deleteAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
        assertFalse(deleteAliceCommand.equals(deleteCarlCommand));
    }

    @Test
    public void equalsDeleteTag() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));

        DeleteCommand firstDeleteCommand = new DeleteCommand(firstPredicate, "first");
        DeleteCommand secondDeleteCommand = new DeleteCommand(secondPredicate, "second");

        // same object -> returns true
        assertTrue(firstDeleteCommand.equals(firstDeleteCommand));

        // same values -> returns true
        DeleteCommand findFirstCommandCopy = new DeleteCommand(firstPredicate, "first");
        assertTrue(firstDeleteCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(firstDeleteCommand.equals(1));

        // null -> returns false
        assertFalse(firstDeleteCommand.equals(null));

        // different person -> returns false
        assertFalse(firstDeleteCommand.equals(secondDeleteCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

}


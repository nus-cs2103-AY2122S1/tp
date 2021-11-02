package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fast.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fast.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.fast.testutil.TypicalPersons.getTypicalFast;
import static seedu.fast.testutil.TypicalPersons.getTypicalFastSetThree;

import org.junit.jupiter.api.Test;

import seedu.fast.commons.core.Messages;
import seedu.fast.commons.core.index.Index;
import seedu.fast.model.Model;
import seedu.fast.model.ModelManager;
import seedu.fast.model.UserPrefs;
import seedu.fast.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalFast(), new UserPrefs());

    @Test
    public void execute_validSingleIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(new Index[] {INDEX_FIRST_PERSON});

        String expectedMessage = String.format(DeleteCommand.MESSAGE_SINGLE_DELETE_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getFast(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(new Index[] {outOfBoundIndex});

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(new Index[] {INDEX_FIRST_PERSON});

        String expectedMessage = String.format(DeleteCommand.MESSAGE_SINGLE_DELETE_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getFast(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFast().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(new Index[] {outOfBoundIndex});

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand singleDeleteFirstCommand = new DeleteCommand(new Index[] {INDEX_FIRST_PERSON});
        DeleteCommand singleDeleteSecondCommand = new DeleteCommand(new Index[] {INDEX_SECOND_PERSON});
        DeleteCommand multipleDeleteFirstCommand = new DeleteCommand(new Index[] {INDEX_FIRST_PERSON,
            INDEX_SECOND_PERSON});
        DeleteCommand multipleDeleteSecondCommand = new DeleteCommand(new Index[] {INDEX_SECOND_PERSON,
            INDEX_FIRST_PERSON});

        // same object -> returns true
        assertTrue(singleDeleteFirstCommand.equals(singleDeleteFirstCommand));
        assertTrue(multipleDeleteFirstCommand.equals(multipleDeleteFirstCommand));

        // same values -> returns true
        DeleteCommand singleDeleteFirstCommandCopy = new DeleteCommand(new Index[] {INDEX_FIRST_PERSON});
        assertTrue(singleDeleteFirstCommand.equals(singleDeleteFirstCommandCopy));
        DeleteCommand multipleDeleteSecondCommandCopy = new DeleteCommand(new Index[] {INDEX_SECOND_PERSON,
            INDEX_FIRST_PERSON});
        assertTrue(multipleDeleteSecondCommandCopy.equals(multipleDeleteSecondCommandCopy));

        // different types -> returns false
        assertFalse(singleDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(singleDeleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(singleDeleteFirstCommand.equals(singleDeleteSecondCommand));
        assertFalse(multipleDeleteFirstCommand.equals(multipleDeleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    // Multiple Delete Tests
    @Test
    public void execute_validMultipleIndexUnfilteredList_success() {
        Person personToDelete1 = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personToDelete2 = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(new Index[] {INDEX_FIRST_PERSON, INDEX_SECOND_PERSON});

        String expectedMessage = String.format(DeleteCommand.MESSAGE_MULTIPLE_DELETE_SUCCESS, 2);

        ModelManager expectedModel = new ModelManager(model.getFast(), new UserPrefs());
        expectedModel.deletePerson(personToDelete1);
        expectedModel.deletePerson(personToDelete2);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMultipleIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(new Index[] {INDEX_FIRST_PERSON, outOfBoundIndex});

        ModelManager expectedModel = new ModelManager(model.getFast(), new UserPrefs());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_MULTIPLE_DELETE_INVALID_INDEX_DETECTED,
                outOfBoundIndex.getOneBased());

        assertCommandFailure(deleteCommand, model, expectedModel, expectedMessage);
    }

    @Test
    public void execute_multipleIndexExceedLimitUnfilteredList_throwsCommandException() {
        // Model with more than 10 people
        Model newModel = new ModelManager(getTypicalFastSetThree(), new UserPrefs());

        Index[] array = new Index[11];
        for (int i = 0; i < 11; i++) {
            array[i] = Index.fromOneBased(i + 1);
        }

        DeleteCommand deleteCommand = new DeleteCommand(array);
        String expectedMessage = String.format(DeleteCommand.MESSAGE_MULTIPLE_DELETE_FAILED_EXCEED_LIMIT);
        assertCommandFailure(deleteCommand, newModel, expectedMessage);
    }

    @Test
    public void execute_multipleIndexDuplicatedUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(new Index[] {INDEX_SECOND_PERSON, INDEX_SECOND_PERSON});
        String expectedMessage = String.format(DeleteCommand.MESSAGE_MULTIPLE_DELETE_FAILED_DUPLICATES);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidMultipleIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(new Index[] {INDEX_SECOND_PERSON, INDEX_FIRST_PERSON});
        String expectedMessage = String.format(DeleteCommand.MESSAGE_MULTIPLE_DELETE_FAILED_LARGER_THAN_CONTACTS);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }
}

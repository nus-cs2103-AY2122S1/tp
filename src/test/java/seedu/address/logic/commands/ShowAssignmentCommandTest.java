package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowAssignmentCommand}.
 */
class ShowAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validIndexPersonList_success() {
        Person personToShow = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ShowAssignmentCommand showAssignmentCommand = new ShowAssignmentCommand(INDEX_FIRST_PERSON);

        String expectedMessage =
                String.format(ShowAssignmentCommand.MESSAGE_SHOW_ASSIGNMENT_SUCCESS, personToShow.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateAssignmentList(personToShow);

        assertCommandSuccess(showAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexPersonList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ShowAssignmentCommand showAssignmentCommand = new ShowAssignmentCommand(outOfBoundIndex);

        assertCommandFailure(showAssignmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShowAssignmentCommand showAssignmentFirstCommand = new ShowAssignmentCommand(INDEX_FIRST_PERSON);
        ShowAssignmentCommand showAssignmentSecondCommand = new ShowAssignmentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(showAssignmentFirstCommand.equals(showAssignmentFirstCommand));

        // same values -> returns true
        ShowAssignmentCommand showAssignmentFirstCommandCopy = new ShowAssignmentCommand(INDEX_FIRST_PERSON);
        assertTrue(showAssignmentFirstCommand.equals(showAssignmentFirstCommandCopy));

        // different types -> returns false
        assertFalse(showAssignmentFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showAssignmentFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(showAssignmentFirstCommand.equals(showAssignmentSecondCommand));
    }
}

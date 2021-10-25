package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.TaskMatchesKeywordPredicate;

public class ViewTaskListCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        ViewTaskListCommand viewTaskListFirstCommand = new ViewTaskListCommand(INDEX_FIRST_PERSON);
        ViewTaskListCommand viewTaskListSecondCommand = new ViewTaskListCommand(INDEX_SECOND_PERSON);
        ViewTaskListCommand viewFilteredTaskListCommand =
                new ViewTaskListCommand(INDEX_FIRST_PERSON, Arrays.asList("cook", "project"));

        // same object -> returns true
        assertEquals(viewTaskListFirstCommand, viewTaskListFirstCommand);

        // same values -> returns true
        ViewTaskListCommand viewTaskListFirstCommandCopy = new ViewTaskListCommand(INDEX_FIRST_PERSON);
        assertEquals(viewTaskListFirstCommand, viewTaskListFirstCommandCopy);

        // different types -> returns false
        assertFalse(viewTaskListFirstCommand.equals(1));

        // null -> returns false
        assertNotEquals(null, viewTaskListFirstCommand);

        // different person -> returns false
        assertNotEquals(viewTaskListFirstCommand, viewTaskListSecondCommand);

        // different keywords -> returns false
        assertNotEquals(viewTaskListFirstCommand, viewFilteredTaskListCommand);
    }

    @Test
    void execute_invalidTargetPersonIndex_throwsCommandException() {
        Index targetPersonIndex = Index.fromZeroBased(model.getFilteredPersonList().size());
        ViewTaskListCommand viewTaskListCommand = new ViewTaskListCommand(targetPersonIndex);

        assertCommandFailure(viewTaskListCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    void execute_validTargetPersonIndex_success() {
        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewTaskListCommand viewTaskListCommand =
                new ViewTaskListCommand(INDEX_FIRST_PERSON, Arrays.asList("sleep", "work"));

        String expectedMessage = String.format(ViewTaskListCommand.MESSAGE_VIEW_TASKS_SUCCESS, personToView.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.displayFilteredPersonTaskList(personToView,
                new TaskMatchesKeywordPredicate(Arrays.asList("sleep", "work")));

        assertCommandSuccess(viewTaskListCommand, model, expectedMessage, expectedModel);
    }
}

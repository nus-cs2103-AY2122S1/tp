package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalAddressBookWithTasks;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code MarkTaskDoneCommand}.
 */
public class MarkTaskDoneCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBookWithTasks(), new UserPrefs());

    // Pass
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMarkAsDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(List.of(INDEX_FIRST_TASK));

        Task messageCopy = TaskBuilder.of(taskToMarkAsDone).build();
        messageCopy.toggleIsDone();

        String expectedMessage = String.format(
                MarkTaskDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS,
                messageCopy.getStatusString().toLowerCase(), messageCopy);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Task expectedCopy = TaskBuilder.of(taskToMarkAsDone).build();
        expectedModel.setTask(taskToMarkAsDone, expectedCopy);
        expectedModel.toggleTaskIsDone(expectedCopy);

        assertCommandSuccess(markTaskDoneCommand, model, expectedMessage, expectedModel);
    }

    // Pass
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(List.of(outOfBoundIndex));

        assertCommandFailure(markTaskDoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToMarkAsDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(List.of(INDEX_FIRST_TASK));

        Task messageCopy = TaskBuilder.of(taskToMarkAsDone).build();
        messageCopy.toggleIsDone();

        String expectedMessage = String.format(
                MarkTaskDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS,
                messageCopy.getStatusString().toLowerCase(), messageCopy);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showTaskAtIndex(expectedModel, INDEX_FIRST_TASK);

        Task expectedCopy = TaskBuilder.of(taskToMarkAsDone).build();
        expectedModel.setTask(taskToMarkAsDone, expectedCopy);
        expectedModel.toggleTaskIsDone(expectedCopy);

        assertCommandSuccess(markTaskDoneCommand, model, expectedMessage, expectedModel);
    }

    // Pass
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        MarkTaskDoneCommand markTaskDoneCommand = new MarkTaskDoneCommand(List.of(outOfBoundIndex));

        assertCommandFailure(markTaskDoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    // Pass
    @Test
    public void equals() {
        MarkTaskDoneCommand markFirstTaskDoneCommand = new MarkTaskDoneCommand(List.of(INDEX_FIRST_TASK));
        MarkTaskDoneCommand markSecondTaskDoneCommand = new MarkTaskDoneCommand(List.of(INDEX_SECOND_TASK));

        // same object -> returns true
        assertEquals(markFirstTaskDoneCommand, markFirstTaskDoneCommand);

        // same values -> returns true
        MarkTaskDoneCommand markFirstTaskDoneCommandCopy = new MarkTaskDoneCommand(List.of(INDEX_FIRST_TASK));
        assertEquals(markFirstTaskDoneCommand, markFirstTaskDoneCommandCopy);

        // different types -> returns false
        assertNotEquals(1, markFirstTaskDoneCommand);

        // null -> returns false
        assertNotEquals(null, markFirstTaskDoneCommand);

        // different task -> returns false
        assertNotEquals(markFirstTaskDoneCommand, markSecondTaskDoneCommand);
    }

}

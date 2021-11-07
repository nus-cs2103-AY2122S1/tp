package seedu.unify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.unify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unify.testutil.TypicalIndexes.INDEX_DONE_TASK;
import static seedu.unify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.unify.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.unify.testutil.TypicalTasks.getTypicalUniFy;

import org.junit.jupiter.api.Test;

import seedu.unify.commons.core.Messages;
import seedu.unify.commons.core.index.Index;
import seedu.unify.model.Model;
import seedu.unify.model.ModelManager;
import seedu.unify.model.UserPrefs;
import seedu.unify.model.task.State;
import seedu.unify.model.task.Task;

public class UndoneCommandTest {

    private Model model = new ModelManager(getTypicalUniFy(), new UserPrefs());

    @Test
    public void execute_validIndexUndoneCommand_success() {
        Task taskToMark = model.getFilteredTaskList().get(INDEX_DONE_TASK.getZeroBased());
        UndoneCommand undoneCommand = new UndoneCommand(INDEX_DONE_TASK);

        String expectedMessage = String.format(UndoneCommand.MESSAGE_UNDONE_TASK_SUCCESS, taskToMark);

        ModelManager expectedModel = new ModelManager(model.getUniFy(), new UserPrefs());
        expectedModel.setTask(taskToMark,
                new Task(
                        taskToMark.getName(),
                        taskToMark.getTime(),
                        taskToMark.getDate(),
                        taskToMark.getTags(),
                        new State(State.ObjectState.TODO),
                        taskToMark.getPriority()));

        assertCommandSuccess(undoneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUndoneCommand_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UndoneCommand undoneCommand = new UndoneCommand(outOfBoundIndex);

        assertCommandFailure(undoneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UndoneCommand firstUndoneCommand = new UndoneCommand(INDEX_FIRST_TASK);
        UndoneCommand secondUndoneCommand = new UndoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(firstUndoneCommand.equals(firstUndoneCommand));

        // same values -> returns true
        UndoneCommand firstCopy = new UndoneCommand(INDEX_FIRST_TASK);
        assertTrue(firstUndoneCommand.equals(firstCopy));

        // different types -> returns false
        assertFalse(firstUndoneCommand.equals(INDEX_FIRST_TASK));

        // null -> returns false
        assertFalse(firstUndoneCommand.equals(null));

        // different commands and different indexes -> returns false
        assertFalse(firstUndoneCommand.equals(secondUndoneCommand));
    }
}

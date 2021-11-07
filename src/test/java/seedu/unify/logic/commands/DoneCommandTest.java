package seedu.unify.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.unify.logic.commands.CommandTestUtil.assertCommandSuccess;
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


public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalUniFy(), new UserPrefs());

    @Test
    public void execute_validIndexDoneCommand_success() {
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS,taskToMark);

        ModelManager expectedModel = new ModelManager(model.getUniFy(), new UserPrefs());
        expectedModel.setTask(taskToMark,
                new Task(
                        taskToMark.getName(),
                        taskToMark.getTime(),
                        taskToMark.getDate(),
                        taskToMark.getTags(),
                        new State(State.ObjectState.DONE),
                        taskToMark.getPriority()));

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexDoneCommand_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DoneCommand firstDoneCommand = new DoneCommand(INDEX_FIRST_TASK);
        DoneCommand secondDoneCommand = new DoneCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(firstDoneCommand.equals(firstDoneCommand));

        // same values -> returns true
        DoneCommand firstCopy = new DoneCommand(INDEX_FIRST_TASK);
        assertTrue(firstDoneCommand.equals(firstCopy));

        // different types -> returns false
        assertFalse(firstDoneCommand.equals(INDEX_FIRST_TASK));

        // null -> returns false
        assertFalse(firstDoneCommand.equals(null));

        // different tasks -> returns false
        assertFalse(firstDoneCommand.equals(secondDoneCommand));
    }
}

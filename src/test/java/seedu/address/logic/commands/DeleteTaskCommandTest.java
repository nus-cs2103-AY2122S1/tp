package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.ModelStub;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Date;
import seedu.address.model.Label;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Task;

public class DeleteTaskCommandTest {
    private static final Task testTask = new Task(new Label("test label"),
            new Date("2001-10-12"), new TaskTag("SO100"));

    private Model model = new ModelManager(getTypicalAddressBook(),
            getTypicalTaskBook(), getTypicalOrderBook(), new UserPrefs());

    //I followed the style of AddCommand test instead of DeleteTaskCommand test since I thought using a modelStub
    //was more stylistically appropriate for testing.

    @Test
    public void execute_validIndexDeletion_success() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOneTask modelStub = new ModelStubWithOneTask();

        CommandResult commandResult = new DeleteTaskCommand(targetIndex).execute(modelStub);

        assertEquals(String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, testTask),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(), modelStub.listWithOneTask);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(2);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        ModelStubWithOneTask modelStub = new ModelStubWithOneTask();

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, (
        ) -> deleteTaskCommand.execute(modelStub));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(),
                model.getTaskBook(), model.getOrderBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;

        // ensures that outOfBoundIndex is still in bounds of task book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskBook().getTaskList().size());

        DeleteTaskCommand deleteCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    private class ModelStubWithOneTask extends ModelStub {
        private final ObservableList<Task> listWithOneTask = FXCollections.observableArrayList(testTask);

        @Override
        public void deleteTask(Task task) {
            requireNonNull(task);
            listWithOneTask.remove(task);
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return listWithOneTask;
        }
    }


    /**
     * Updates {@code model}'s filtered list to show no task.
     */
    public static void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }

}

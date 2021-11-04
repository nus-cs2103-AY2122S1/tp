package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.DeleteTaskCommandTest.showNoTask;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalOrders.getTypicalOrderBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.ModelStub;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

class MarkTaskCommandTest {
    private static final String TEST_DATE = "04 July 2020";

    private Task testTask;

    @BeforeEach
    public void setUp() {
        testTask = new TaskBuilder().withLabel("test label")
                .withDate(TEST_DATE).withTaskTag("SO100").build();
    }

    @Test
    public void execute_validIndexMarkTask_success() throws Exception {
        ModelStubWithOneTask modelStub = new ModelStubWithOneTask();
        assertFalse(testTask.getIsDone());

        CommandResult commandResult = new MarkTaskCommand(INDEX_FIRST_TASK).execute(modelStub);
        assertTrue(testTask.getIsDone());
        assertEquals(String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, testTask),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_markAlreadyDone_notification() throws Exception {
        Index targetIndex = Index.fromOneBased(1);
        ModelStubWithOneTask modelStub = new ModelStubWithOneTask();

        // mark the task twice, verify that notification is given.
        new MarkTaskCommand(targetIndex).execute(modelStub);
        CommandResult commandResult = new MarkTaskCommand(targetIndex).execute(modelStub);

        assertEquals(String.format(MarkTaskCommand.MESSAGE_TASK_ALREADY_MARKED, testTask),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(2);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);
        ModelStubWithOneTask modelStub = new ModelStubWithOneTask();

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, (
        ) -> markTaskCommand.execute(modelStub));
    }

    @Test
    public void execute_filterIncompleteTasks_removesTask() throws Exception {
        TaskBook taskBookWithIncompleteTask = new TaskBook();
        taskBookWithIncompleteTask.addTask(testTask);

        Model model = new ModelManager(getTypicalAddressBook(),
                taskBookWithIncompleteTask, getTypicalOrderBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(),
                taskBookWithIncompleteTask, getTypicalOrderBook(), new UserPrefs());

        model.updateFilteredTaskList(task -> !task.getIsDone());
        showNoTask(expectedModel);

        CommandResult commandResult = new MarkTaskCommand(INDEX_FIRST_TASK).execute(model);
        assertTrue(testTask.getIsDone());
        assertEquals(String.format(MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS, testTask),
                commandResult.getFeedbackToUser());
    }

    private class ModelStubWithOneTask extends ModelStub {
        private final ObservableList<Task> listWithOneTask = FXCollections.observableArrayList(testTask);

        @Override
        public boolean markTask(Task task) {
            return listWithOneTask.get(0).markDone();
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            return listWithOneTask;
        }
    }
}

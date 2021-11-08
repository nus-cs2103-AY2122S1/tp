package dash.logic.commands.taskcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.commons.core.index.Index;
import dash.logic.commands.CommandTestUtil;
import dash.model.AddressBook;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserInputList;
import dash.model.UserPrefs;
import dash.model.task.Task;
import dash.model.task.TaskList;
import dash.testutil.EditTaskDescriptorBuilder;
import dash.testutil.TaskBuilder;
import dash.testutil.TypicalIndexes;
import dash.testutil.TypicalTasks;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPersonCommand.
 */
public class EditTaskCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), TypicalTasks.getTypicalTaskList(),
            new UserInputList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder().withTaskDate("07/11/2021, 1900").build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(TypicalIndexes.INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask =
                taskInList.withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_TP)
                        .withTags(CommandTestUtil.VALID_TAG_PROJECT).build();

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_TP)
                .withTags(CommandTestUtil.VALID_TAG_PROJECT).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(indexLastTask.getZeroBased(), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(TypicalIndexes.INDEX_FIRST,
                new EditTaskCommand.EditTaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(TypicalIndexes.INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showTaskAtIndex(model, TypicalIndexes.INDEX_FIRST);

        Task taskInFilteredList =
                model.getFilteredTaskList().get(TypicalIndexes.INDEX_FIRST.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList)
                .withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_TP).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(TypicalIndexes.INDEX_FIRST,
                new EditTaskDescriptorBuilder().withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_TP).build());

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskCommand.EditTaskDescriptor descriptor =
                new EditTaskDescriptorBuilder().withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_TP).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of task list
     */
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        CommandTestUtil.showTaskAtIndex(model, TypicalIndexes.INDEX_FIRST);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getObservableTaskList().size());

        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withTaskDescription(CommandTestUtil.VALID_TASK_DESCRIPTION_TP).build());

        CommandTestUtil.assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(TypicalIndexes.INDEX_FIRST,
                CommandTestUtil.DESC_CS2103T_TP);

        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor copyDescriptor =
                new EditTaskCommand.EditTaskDescriptor(CommandTestUtil.DESC_CS2103T_TP);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(TypicalIndexes.INDEX_FIRST,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearTaskCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(TypicalIndexes.INDEX_SECOND,
                CommandTestUtil.DESC_CS2103T_TP)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(TypicalIndexes.INDEX_FIRST,
                CommandTestUtil.DESC_CS2100_TEST)));
    }

}

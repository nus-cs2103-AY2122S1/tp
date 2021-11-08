package dash.logic.commands.taskcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.logic.commands.exceptions.CommandException;
import dash.model.AddressBook;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserInputList;
import dash.model.UserPrefs;
import dash.model.task.Task;
import dash.model.task.TaskList;
import dash.testutil.Assert;
import dash.testutil.EditTaskDescriptorBuilder;
import dash.testutil.TaskBuilder;
import dash.testutil.TypicalIndexes;
import dash.testutil.TypicalTasks;

public class TagTaskCommandTest {

    private TaskList taskList = new TaskList();
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), taskList,
            new UserInputList());

    @Test
    public void constructor_nullTagTask_throwsNullPointerException() {
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(TypicalTasks.ASSIGNMENT).build();
        Assert.assertThrows(NullPointerException.class, () -> new TagTaskCommand(null, descriptor));
        Assert.assertThrows(NullPointerException.class, () -> new TagTaskCommand(TypicalIndexes.INDEX_FIRST, null));
    }

    @Test
    public void execute_oneTagSpecifiedUnfilteredList_success() {
        Task taskToAdd = new TaskBuilder().withTaskDescription("stuff").build();
        model.getTaskList().add(taskToAdd);

        Task task = model.getTaskList().getObservableTaskList().get(0);
        Task editedTask = new TaskBuilder(task).withTags("tag").build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        TagTaskCommand tagTaskCommand = new TagTaskCommand(TypicalIndexes.INDEX_FIRST, descriptor);

        String expectedMessage = String.format(TagTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(tagTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moreThanOneTagSpecifiedUnfilteredList_success() {
        Task taskToAdd = new TaskBuilder().withTaskDescription("stuff").build();
        model.getTaskList().add(taskToAdd);

        Task task = model.getTaskList().getObservableTaskList().get(0);
        Task editedTask = new TaskBuilder(task).withTags("important", "assignment", "homework").build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        TagTaskCommand tagTaskCommand = new TagTaskCommand(TypicalIndexes.INDEX_FIRST, descriptor);

        String expectedMessage = String.format(TagTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(tagTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagSpecifiedUnfilteredList_throwsCommandException() {
        Task taskToAdd = new TaskBuilder().withTaskDescription("stuff").build();
        model.getTaskList().add(taskToAdd);

        Task task = model.getTaskList().getObservableTaskList().get(0);
        Task editedTask = new TaskBuilder(task).withTags("important", "assignment", "homework").build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        TagTaskCommand tagTaskCommand = new TagTaskCommand(TypicalIndexes.INDEX_THIRD, descriptor);

        Assert.assertThrows(CommandException.class, () -> tagTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(TypicalTasks.ASSIGNMENT).build();
        TagTaskCommand tagFirstCommand = new TagTaskCommand(TypicalIndexes.INDEX_FIRST, descriptor);
        TagTaskCommand tagSecondCommand = new TagTaskCommand(TypicalIndexes.INDEX_SECOND, descriptor);

        // same object -> returns true
        assertTrue(tagFirstCommand.equals(tagFirstCommand));

        // same values -> returns true
        TagTaskCommand tagFirstCommandCopy = new TagTaskCommand(TypicalIndexes.INDEX_FIRST, descriptor);
        assertTrue(tagFirstCommand.equals(tagFirstCommandCopy));

        // different types -> returns false
        assertFalse(tagSecondCommand.equals(1));

        // null -> returns false
        assertFalse(tagFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(tagFirstCommand.equals(tagSecondCommand));
    }
}

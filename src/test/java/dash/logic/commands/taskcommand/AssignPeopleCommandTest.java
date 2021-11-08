package dash.logic.commands.taskcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.commons.core.index.Index;
import dash.logic.commands.CommandTestUtil;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserInputList;
import dash.model.UserPrefs;
import dash.model.person.Person;
import dash.model.task.Task;
import dash.model.task.TaskList;
import dash.testutil.EditTaskDescriptorBuilder;
import dash.testutil.TaskBuilder;
import dash.testutil.TypicalIndexes;
import dash.testutil.TypicalPersons;
import dash.testutil.TypicalTasks;

class AssignPeopleCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(),
            TypicalTasks.getTypicalTaskList(), new UserInputList());

    @Test
    public void execute_multiplePeopleAdded_success() {
        Index indexOfFirstPerson = TypicalIndexes.INDEX_SECOND;
        Index indexOfSecondPerson = TypicalIndexes.INDEX_THIRD;

        Person firstPerson = model.getAddressBook().getPersonList().get(indexOfFirstPerson.getZeroBased());
        Person secondPerson = model.getAddressBook().getPersonList().get(indexOfSecondPerson.getZeroBased());

        Index indexOfTask = TypicalIndexes.INDEX_FIRST;
        Task task = model.getTaskList().getObservableTaskList().get(indexOfTask.getZeroBased());

        Task editedTask = new TaskBuilder(task).withPeople(firstPerson, secondPerson).build();

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        AssignPeopleCommand assignPeopleCommand = new AssignPeopleCommand(indexOfTask, descriptor);

        String expectedMessage = String.format(AssignPeopleCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(assignPeopleCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_singlePersonAdded_success() {
        Index indexOfFirstPerson = TypicalIndexes.INDEX_SECOND;

        Person firstPerson = model.getAddressBook().getPersonList().get(indexOfFirstPerson.getZeroBased());

        Index indexOfTask = TypicalIndexes.INDEX_FIRST;
        Task task = model.getTaskList().getObservableTaskList().get(indexOfTask.getZeroBased());

        Task editedTask = new TaskBuilder(task).withPeople(firstPerson).build();

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        AssignPeopleCommand assignPeopleCommand = new AssignPeopleCommand(indexOfTask, descriptor);

        String expectedMessage = String.format(AssignPeopleCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(assignPeopleCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_noPeopleAdded_success() {
        Index indexOfTask = TypicalIndexes.INDEX_FIRST;
        Task task = model.getTaskList().getObservableTaskList().get(indexOfTask.getZeroBased());

        Task editedTask = new TaskBuilder(task).build();

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        AssignPeopleCommand assignPeopleCommand = new AssignPeopleCommand(indexOfTask, descriptor);

        String expectedMessage = String.format(AssignPeopleCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(assignPeopleCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_filteredList_success() {
        Index indexOfFirstPerson = TypicalIndexes.INDEX_SECOND;
        Index indexOfSecondPerson = TypicalIndexes.INDEX_THIRD;

        Person firstPerson = model.getAddressBook().getPersonList().get(indexOfFirstPerson.getZeroBased());
        Person secondPerson = model.getAddressBook().getPersonList().get(indexOfSecondPerson.getZeroBased());

        Index indexOfTask = TypicalIndexes.INDEX_FIRST;
        CommandTestUtil.showTaskAtIndex(model, indexOfTask);

        Task taskInFilteredList =
                model.getFilteredTaskList().get(indexOfTask.getZeroBased());

        Task editedTask = new TaskBuilder(taskInFilteredList).withPeople(firstPerson, secondPerson).build();

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withPeople(firstPerson, secondPerson).build();
        AssignPeopleCommand assignPeopleCommand = new AssignPeopleCommand(indexOfTask, descriptor);

        String expectedMessage = String.format(AssignPeopleCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(),
                new TaskList(model.getTaskList()), new UserInputList());
        expectedModel.setTask(0, editedTask);

        assertCommandSuccess(assignPeopleCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void equals() {
        Index indexOfFirstPerson = TypicalIndexes.INDEX_SECOND;
        Index indexOfSecondPerson = TypicalIndexes.INDEX_THIRD;
        Index indexOfTask = TypicalIndexes.INDEX_FIRST;

        Person firstPerson = model.getAddressBook().getPersonList().get(indexOfFirstPerson.getZeroBased());
        Person secondPerson = model.getAddressBook().getPersonList().get(indexOfSecondPerson.getZeroBased());

        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withPeople(firstPerson, secondPerson).build();

        EditTaskCommand.EditTaskDescriptor differentDescriptor = new EditTaskDescriptorBuilder()
                .withPeople(firstPerson).build();

        final AssignPeopleCommand standardCommand = new AssignPeopleCommand(indexOfTask, descriptor);

        EditTaskCommand.EditTaskDescriptor copyDescriptor = new EditTaskDescriptorBuilder()
                .withPeople(firstPerson, secondPerson).build();
        final AssignPeopleCommand copyCommand = new AssignPeopleCommand(indexOfTask, copyDescriptor);

        // same values -> returns true
        assertTrue(standardCommand.equals(copyCommand));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearTaskCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AssignPeopleCommand(TypicalIndexes.INDEX_SECOND, descriptor)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AssignPeopleCommand(indexOfTask, differentDescriptor)));
    }

}

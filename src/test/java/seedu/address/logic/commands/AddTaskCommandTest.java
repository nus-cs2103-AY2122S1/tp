package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_LIST_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_LIST_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;

public class AddTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArg_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, null));
        assertThrows(NullPointerException.class, () ->
                new AddTaskCommand(
                    Index.fromOneBased(model.getFilteredPersonList().size()),
                    null)
        );
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(new TaskName("Task1"), null, null, null));
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, taskList));
    }

    @Test
    public void constructor_correctArgs_success() {
        assertAll(() -> new AddTaskCommand(INDEX_FIRST_PERSON, VALID_TASK_LIST_1));
    }

    @Test
    public void execute_addTask_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        List<Task> tasks = new ArrayList<>(personToEdit.getTasks());
        List<Task> newTasks = new ArrayList<>();
        newTasks.add(new Task(new TaskName("walk"), null, null, null));
        tasks.addAll(newTasks);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), tasks, personToEdit.getDescription(),
                personToEdit.getImportance()
        );
        AddTaskCommand addTaskCommand = new AddTaskCommand(INDEX_FIRST_PERSON, newTasks);

        String expectedMessage = String.format(
                AddTaskCommand.MESSAGE_SUCCESS, newTasks.size(),
                StringUtil.singularOrPlural("task", newTasks.size()), personToEdit.getName()
        );

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AddTaskCommand addTaskCommand = new AddTaskCommand(outOfBoundIndex, new ArrayList<>());

        assertCommandFailure(addTaskCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddTaskCommand standardCommand = new AddTaskCommand(INDEX_FIRST_PERSON, VALID_TASK_LIST_1);

        // same values -> returns true
        AddTaskCommand commandWithSameValues = new AddTaskCommand(INDEX_FIRST_PERSON, VALID_TASK_LIST_1);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTaskCommand(INDEX_SECOND_PERSON, VALID_TASK_LIST_1)));

        // different tasklist -> returns false
        assertFalse(standardCommand.equals(new AddTaskCommand(INDEX_FIRST_PERSON, VALID_TASK_LIST_2)));

    }
}

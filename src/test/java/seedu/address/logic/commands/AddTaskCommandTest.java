package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_LIST_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_LIST_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

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
        taskList.add(new Task("Task1"));
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, taskList));
    }

    @Test
    public void constructor_correctArgs_success() {
        assertAll(() -> new AddTaskCommand(INDEX_FIRST_PERSON, VALID_TASK_LIST_1));
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

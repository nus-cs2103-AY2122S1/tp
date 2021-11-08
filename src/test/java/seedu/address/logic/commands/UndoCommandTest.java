package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index invalidIndex = Index.fromOneBased(10);
        Index validTaskIndex = Index.fromOneBased(1);
        ArrayList<Index> validTaskIndexes = new ArrayList<>();
        validTaskIndexes.add(validTaskIndex);
        UndoCommand undoCommand = new UndoCommand(invalidIndex, validTaskIndexes);

        assertCommandFailure(undoCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        // One task index, invalid value
        Index validIndex = Index.fromOneBased(1);
        Person personToDisplay = model.getViewAllTaskListPersons().get(validIndex.getZeroBased());
        model.displayPersonTaskList(personToDisplay);
        Index invalidTaskIndex = Index.fromOneBased(8);
        ArrayList<Index> invalidTaskIndexes = new ArrayList<>();
        invalidTaskIndexes.add(invalidTaskIndex);
        UndoCommand undoCommand = new UndoCommand(validIndex, invalidTaskIndexes);

        assertCommandFailure(undoCommand, model, String.format(Messages.MESSAGE_INVALID_TASK, ALICE.getName()));

        // Two task indexes, one valid value
        Index validTaskIndex = Index.fromOneBased(1);
        invalidTaskIndexes.add(0, validTaskIndex);
        invalidTaskIndexes.add(invalidTaskIndex);
        UndoCommand undoCommand2 = new UndoCommand(validIndex, invalidTaskIndexes);

        assertCommandFailure(undoCommand, model, String.format(Messages.MESSAGE_INVALID_TASK, ALICE.getName()));
    }

    @Test
    public void execute_validIndex_success() {
        // Set up valid UndoCommand marking 2 tasks as not done
        Index validIndex = Index.fromOneBased(8); // GEORGE
        Person personToDisplay = model.getViewAllTaskListPersons().get(validIndex.getZeroBased());
        model.displayPersonTaskList(personToDisplay);
        Index validTaskIndex = Index.fromOneBased(1);
        Index validTaskIndex2 = Index.fromOneBased(2);
        ArrayList<Index> validTaskIndexes = new ArrayList<>();
        validTaskIndexes.add(validTaskIndex);
        validTaskIndexes.add(validTaskIndex2);
        UndoCommand undoCommand = new UndoCommand(validIndex, validTaskIndexes);

        // Set up expected model by marking task of GEORGE as not done
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.displayPersonTaskList(personToDisplay);
        Person expectedPerson = new PersonBuilder(GEORGE).build();
        markPersonTaskAsUndone(expectedPerson, validTaskIndexes);
        String expectedMessage = String.format(MESSAGE_SUCCESS, 0, "task", expectedPerson.getName())
                + "\n" + String.format("%1$d %2$s are already not done.", 2, "tasks");
        expectedModel.setPerson(GEORGE, expectedPerson);

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ArrayList<Index> taskIndexes = new ArrayList<>();
        taskIndexes.add(INDEX_FIRST_TASK);
        taskIndexes.add(INDEX_SECOND_TASK);
        UndoCommand undoCommandOne = new UndoCommand(INDEX_FIRST_PERSON, taskIndexes);

        // same object -> returns true
        assertEquals(undoCommandOne, undoCommandOne);

        // same values -> return true
        UndoCommand undoCommandTwo = new UndoCommand(INDEX_FIRST_PERSON, taskIndexes);
        assertEquals(undoCommandOne, undoCommandTwo);

        // null -> return false;
        assertNotEquals(null, undoCommandOne);

        // different command -> return false
        assertNotEquals(new ClearCommand(), undoCommandOne);

        // different person index -> return false
        UndoCommand undoCommandThree = new UndoCommand(INDEX_SECOND_PERSON, taskIndexes);
        assertNotEquals(undoCommandThree, undoCommandOne);

        // different task indexes -> return false
        ArrayList<Index> taskIndexes2 = new ArrayList<>();
        taskIndexes2.add(INDEX_FIRST_TASK);
        UndoCommand undoCommandFour = new UndoCommand(INDEX_FIRST_PERSON, taskIndexes2);
        assertNotEquals(undoCommandFour, undoCommandOne);
    }

    public static void markPersonTaskAsUndone(Person person, List<Index> taskIndexes) {
        ArrayList<Task> taskList = new ArrayList<Task>(person.getTasks());
        for (Index index : taskIndexes) {
            Task task = taskList.get(index.getZeroBased());
            task.setNotDone();
        }
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DoneCommand.MESSAGE_SUCCESS;
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

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DoneCommand}.
 */
public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index invalidIndex = Index.fromOneBased(10);
        Index validTaskIndex = Index.fromOneBased(1);
        ArrayList<Index> validTaskIndexes = new ArrayList<>();
        validTaskIndexes.add(validTaskIndex);
        DoneCommand doneCommand = new DoneCommand(invalidIndex, validTaskIndexes);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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
        DoneCommand doneCommand = new DoneCommand(validIndex, invalidTaskIndexes);

        assertCommandFailure(doneCommand, model, String.format(Messages.MESSAGE_INVALID_TASK, ALICE.getName()));

        // Two task indexes, one valid value
        Index validTaskIndex = Index.fromOneBased(1);
        invalidTaskIndexes.add(0, validTaskIndex);
        invalidTaskIndexes.add(invalidTaskIndex);
        DoneCommand doneCommand2 = new DoneCommand(validIndex, invalidTaskIndexes);

        assertCommandFailure(doneCommand, model, String.format(Messages.MESSAGE_INVALID_TASK, ALICE.getName()));
    }

    @Test
    public void execute_validIndex_success() {
        // Set up valid DoneCommand marking 2 tasks as done
        Index validIndex = Index.fromOneBased(8); // GEORGE
        Person personToDisplay = model.getViewAllTaskListPersons().get(validIndex.getZeroBased());
        model.displayPersonTaskList(personToDisplay);
        Index validTaskIndex = Index.fromOneBased(1);
        Index validTaskIndex2 = Index.fromOneBased(2);
        ArrayList<Index> validTaskIndexes = new ArrayList<>();
        validTaskIndexes.add(validTaskIndex);
        validTaskIndexes.add(validTaskIndex2);
        DoneCommand doneCommand = new DoneCommand(validIndex, validTaskIndexes);

        // Set up expected model by marking task of GEORGE as done
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.displayPersonTaskList(personToDisplay);
        Person expectedPerson = new PersonBuilder(GEORGE).build();
        markPersonTaskAsDone(expectedPerson, validTaskIndexes);
        String expectedMessage = String.format(MESSAGE_SUCCESS, 0, "task", expectedPerson.getName())
                + "\n" + String.format("%1$d %2$s are already done.", 2, "tasks");
        expectedModel.setPerson(GEORGE, expectedPerson);

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ArrayList<Index> taskIndexes = new ArrayList<>();
        taskIndexes.add(INDEX_FIRST_TASK);
        taskIndexes.add(INDEX_SECOND_TASK);
        DoneCommand doneCommandOne = new DoneCommand(INDEX_FIRST_PERSON, taskIndexes);

        // same object -> returns true
        assertEquals(doneCommandOne, doneCommandOne);

        // same values -> return true
        DoneCommand doneCommandTwo = new DoneCommand(INDEX_FIRST_PERSON, taskIndexes);
        assertEquals(doneCommandOne, doneCommandTwo);

        // null -> return false;
        assertNotEquals(null, doneCommandOne);

        // different command -> return false
        assertNotEquals(new ClearCommand(), doneCommandOne);

        // different person index -> return false
        DoneCommand doneCommandThree = new DoneCommand(INDEX_SECOND_PERSON, taskIndexes);
        assertNotEquals(doneCommandThree, doneCommandOne);

        // different task indexes -> return false
        ArrayList<Index> taskIndexes2 = new ArrayList<>();
        taskIndexes2.add(INDEX_FIRST_TASK);
        DoneCommand doneCommandFour = new DoneCommand(INDEX_FIRST_PERSON, taskIndexes2);
        assertNotEquals(doneCommandFour, doneCommandOne);
    }

    public static void markPersonTaskAsDone(Person person, List<Index>taskIndexes) {
        ArrayList<Task> taskList = new ArrayList<Task>(person.getTasks());
        for (Index index : taskIndexes) {
            Task task = taskList.get(index.getZeroBased());
            task.setDone();
        }
    }
}

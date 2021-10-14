package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        Index targetTasksIndex = Index.fromZeroBased(model.getFilteredPersonList()
                        .get(1)
                        .getTasks()
                        .size());

        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, null));
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(Index.fromOneBased(1), null));
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, targetTasksIndex));
    }

    @Test
    public void constructor_correctArgs_success() {
        Index targetTasksIndex = Index.fromOneBased(model.getFilteredPersonList()
                .get(0)
                .getTasks()
                .size());

        assertAll(() -> new DeleteTaskCommand(Index.fromZeroBased(0), targetTasksIndex));
    }

    @Test
    void execute_invalidTargetPersonIndex_throwsCommandException() {
        int numOfPersons = model.getFilteredPersonList().size();
        Index targetPersonIndex = Index.fromZeroBased(numOfPersons);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(targetPersonIndex, Index.fromZeroBased(0));

        CommandException commandException = Assertions.assertThrows(
            CommandException.class, () -> deleteTaskCommand.execute(model));

        assert commandException != null;

        assertEquals(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, commandException.getMessage());
    }

    @Test
    void execute_invalidTargetTaskIndex_throwsCommandException() {
        Person person = model.getFilteredPersonList().get(0);
        Name personName = person.getName();
        Index targetTaskIndex = Index.fromZeroBased(person.getTasks().size());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, targetTaskIndex);

        CommandException commandException = Assertions.assertThrows(
            CommandException.class, () -> deleteTaskCommand.execute(model));

        assert commandException != null;

        assertEquals(String.format(Messages.MESSAGE_INVALID_TASK, personName),
                commandException.getMessage());
    }

    @Test
    void execute_success() {
        try {
            Index personIndex = Index.fromOneBased(model.getFilteredPersonList().size());
            Person person = model.getFilteredPersonList().get(personIndex.getZeroBased());

            List<Task> taskListCopy = new ArrayList<>(person.getTasks());
            Index targetTaskIndex = Index.fromOneBased(taskListCopy.size());
            String taskName = taskListCopy.get(taskListCopy.size() - 1).getTaskName();
            taskListCopy.remove(targetTaskIndex.getZeroBased());

            DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(personIndex, targetTaskIndex);

            String feedback = String.format(DeleteTaskCommand.MESSAGE_SUCCESS, taskName, person.getName());
            CommandResult resultCopy = new CommandResult(feedback);
            CommandResult result = deleteTaskCommand.execute(model);

            person = model.getFilteredPersonList().get(personIndex.getZeroBased());

            assertEquals(person.getTasks(), taskListCopy);
            assertEquals(result, resultCopy);
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    void getCommand_success() {
        String commandWord = DeleteTaskCommand.COMMAND_WORD;
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));
        assertEquals(deleteTaskCommand.getCommand(), commandWord);
    }

    @Test
    void getDescription_success() {
        String description = DeleteTaskCommand.DESCRIPTION;
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));
        assertEquals(deleteTaskCommand.getDescription(), description);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1));
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(INDEX_FIRST_PERSON, Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_PERSON, Index.fromOneBased(1));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));
        assertFalse(deleteFirstCommand.equals(new DeleteCommand(INDEX_FIRST_PERSON)));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(
                new DeleteTaskCommand(INDEX_SECOND_PERSON, Index.fromOneBased(1))));
    }
}

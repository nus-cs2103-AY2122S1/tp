package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.notor.logic.commands.CommandTestUtil.assertExecuteFailure;
import static seedu.notor.logic.commands.CommandTestUtil.assertExecuteSuccess;
import static seedu.notor.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.notor.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.notor.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notor.commons.core.Messages;
import seedu.notor.commons.core.index.Index;
import seedu.notor.logic.commands.person.PersonDeleteCommand;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.executors.person.PersonDeleteExecutor;
import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.UserPrefs;
import seedu.notor.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class PersonDeleteCommandTest {

    private final Model model = new ModelManager(getTypicalNotor(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        Executor.setup(model);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonDeleteCommand deleteCommand = new PersonDeleteCommandStub(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(PersonDeleteExecutor.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getNotor(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        CommandTestUtil.assertExecuteSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PersonDeleteCommand deleteCommand = new PersonDeleteCommandStub(outOfBoundIndex);

        assertExecuteFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PersonDeleteCommand deleteCommand = new PersonDeleteCommandStub(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(PersonDeleteExecutor.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getNotor(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertExecuteSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of notor list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getNotor().getPersonList().size());

        PersonDeleteCommand deleteCommand = new PersonDeleteCommand(outOfBoundIndex);

        assertExecuteFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PersonDeleteCommand deleteFirstCommand = new PersonDeleteCommand(INDEX_FIRST_PERSON);
        PersonDeleteCommand deleteSecondCommand = new PersonDeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        PersonDeleteCommand deleteFirstCommandCopy = new PersonDeleteCommand(INDEX_FIRST_PERSON);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    private static class PersonDeleteExecutorStub extends PersonDeleteExecutor {
        public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

        public PersonDeleteExecutorStub(Index index) {
            super(index);
        }

        @Override
        public CommandResult execute() throws ExecuteException {
            Person toBeDeletedPerson = super.getPerson();
            model.deletePerson(toBeDeletedPerson);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, toBeDeletedPerson));
        }

    }
    private static class PersonDeleteCommandStub extends PersonDeleteCommand {

        private final PersonDeleteExecutorStub executor;

        /**
         * Constructor for a PersonDeleteCommand.
         *
         * @param index Index of the person to be deleted.
         */
        public PersonDeleteCommandStub(Index index) {
            super(index);
            requireNonNull(index);
            this.executor = new PersonDeleteExecutorStub(index);
        }

        @Override
        public CommandResult execute() throws ExecuteException {
            return executor.execute();
        }

    }

}

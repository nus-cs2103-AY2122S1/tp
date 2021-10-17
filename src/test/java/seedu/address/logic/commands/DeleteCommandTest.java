package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonHasId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validClientIdUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(2);
        ClientId clientId = new ClientId(personToDelete.getClientId().value);
        Predicate<Person> predicates = new PersonHasId(clientId);

        DeleteCommand deleteCommand = new DeleteCommand(predicates);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidClientIdUnfilteredList_throwsCommandException() {
        ClientId clientId = new ClientId("20");
        Predicate<Person> predicates = new PersonHasId(clientId);
        DeleteCommand deleteCommand = new DeleteCommand(predicates);

        assertCommandFailure(deleteCommand, model, DeleteCommand.MESSAGE_DELETE_PERSON_FAILURE);
    }


    @Test
    public void equals() {

        ClientId clientIdOne = new ClientId("1");
        Predicate<Person> predicatesOne = new PersonHasId(clientIdOne);
        DeleteCommand deleteFirstCommand = new DeleteCommand(predicatesOne);

        ClientId clientIdTwo = new ClientId("2");
        Predicate<Person> predicatesTwo = new PersonHasId(clientIdTwo);
        DeleteCommand deleteSecondCommand = new DeleteCommand(predicatesTwo);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ClientId clientIdTest = new ClientId("1");
        Predicate<Person> predicatesTest = new PersonHasId(clientIdTest);
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(predicatesTest);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}

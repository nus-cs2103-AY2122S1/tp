package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddFavCommand.
 */
public class RemFavCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }


    @Test
    public void execute_removePersonFromFavourite_success() {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);
        String personIdString = editedPerson.getStudentId().value;
        AddFavCommand addFavCommand = new AddFavCommand(personIdString);
        addFavCommand.execute(model);
        assertTrue(editedPerson.getIsFavourite());
        RemFavCommand remFavCommand = new RemFavCommand(personIdString);
        remFavCommand.execute(model);
        assertFalse(editedPerson.getIsFavourite());
    }

    @Test
    public void execute_removeNotFavouriteFromFavourite_failure() {
        String expectedMessage = Messages.MESSAGE_PERSON_IS_NOT_FAVOURITE;
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);
        String personIdString = editedPerson.getStudentId().value;
        assertFalse(editedPerson.getIsFavourite());
        RemFavCommand remFavCommand = new RemFavCommand(personIdString);
        assertEquals(remFavCommand.execute(model), new CommandResult(expectedMessage));
        assertFalse(editedPerson.getIsFavourite());
    }

    @Test
    public void execute_removeNotFoundFromFavourite_failure() {
        String expectedMessage = Messages.MESSAGE_NO_SUCH_ID_FOUND;
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);
        String personIdString = editedPerson.getStudentId().value;
        AddFavCommand addFavCommand = new AddFavCommand(personIdString);
        addFavCommand.execute(model);
        assertTrue(editedPerson.getIsFavourite());
        RemFavCommand remFavCommand = new RemFavCommand("a9999999r");
        assertEquals(remFavCommand.execute(model), new CommandResult(expectedMessage));
    }
}

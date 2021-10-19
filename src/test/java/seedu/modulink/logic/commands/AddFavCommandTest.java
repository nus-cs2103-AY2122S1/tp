package seedu.modulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulink.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.model.Model;
import seedu.modulink.model.ModelManager;
import seedu.modulink.model.UserPrefs;
import seedu.modulink.model.person.Person;
import seedu.modulink.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddFavCommand.
 */
public class AddFavCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_personSetAsFavourite_success() {
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);
        String personIdString = editedPerson.getStudentId().value;
        assertFalse(editedPerson.getIsFavourite());
        AddFavCommand command = new AddFavCommand(personIdString);
        command.execute(model);
        assertTrue(editedPerson.getIsFavourite());
    }

    @Test
    public void execute_setFavouriteAsFavourite_failure() {
        String expectedMessage = Messages.MESSAGE_PERSON_ALREADY_FAVOURITE;
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);
        String personIdString = editedPerson.getStudentId().value;
        AddFavCommand command = new AddFavCommand(personIdString);
        command.execute(model);
        assertTrue(editedPerson.getIsFavourite());
        assertEquals(command.execute(model), new CommandResult(expectedMessage));
        assertTrue(editedPerson.getIsFavourite());
    }

    @Test
    public void execute_addNotFoundAsFavourite_failure() {
        String expectedMessage = Messages.MESSAGE_NO_SUCH_ID_FOUND;
        Person editedPerson = new PersonBuilder().build();
        model.addPerson(editedPerson);
        assertFalse(editedPerson.getIsFavourite());
        AddFavCommand addFavCommand = new AddFavCommand("a9999999r");
        assertEquals(addFavCommand.execute(model), new CommandResult(expectedMessage));
    }


}

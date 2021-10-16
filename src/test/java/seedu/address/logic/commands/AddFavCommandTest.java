package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddFavCommand.
 */
public class AddFavCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


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




}

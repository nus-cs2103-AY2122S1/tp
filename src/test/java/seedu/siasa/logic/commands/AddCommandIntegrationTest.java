package seedu.siasa.logic.commands;

import static seedu.siasa.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.siasa.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.siasa.model.Model;
import seedu.siasa.model.ModelManager;
import seedu.siasa.model.UserPrefs;
import seedu.siasa.model.person.Person;
import seedu.siasa.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSiasa(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getSiasa(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getSiasa().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

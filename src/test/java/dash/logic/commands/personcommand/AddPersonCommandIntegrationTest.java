package dash.logic.commands.personcommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dash.logic.commands.CommandTestUtil;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserPrefs;
import dash.model.person.Person;
import dash.model.task.TaskList;
import dash.testutil.PersonBuilder;
import dash.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPersonCommand}.
 */
public class AddPersonCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(), new TaskList());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new TaskList());
        expectedModel.addPerson(validPerson);

        CommandTestUtil.assertCommandSuccess(new AddPersonCommand(validPerson), model,
                String.format(AddPersonCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        CommandTestUtil.assertCommandFailure(new AddPersonCommand(personInList), model,
                AddPersonCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

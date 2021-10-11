package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertExecuteFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.person.PersonCreateCommand;
import seedu.address.logic.executors.Executor;
import seedu.address.logic.executors.person.PersonCreateExecutor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class PersonCreateCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Executor.setup(model);
    }

    @Test
    public void execute_newPerson_success() throws ParseException {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.createPerson(validPerson);

        CommandTestUtil.assertExecuteSuccess(new PersonCreateCommand(null, validPerson), model,
                String.format(PersonCreateExecutor.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() throws ParseException {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertExecuteFailure(new PersonCreateCommand(null, personInList), model,
                PersonCreateExecutor.MESSAGE_DUPLICATE_PERSON);
    }

}

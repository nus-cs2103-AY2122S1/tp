package seedu.notor.logic.commands;

import static seedu.notor.logic.commands.CommandTestUtil.assertExecuteFailure;
import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.notor.logic.commands.person.PersonCreateCommand;
import seedu.notor.logic.executors.Executor;
import seedu.notor.logic.executors.person.PersonCreateExecutor;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.UserPrefs;
import seedu.notor.model.person.Person;
import seedu.notor.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class PersonCreateCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalNotor(), new UserPrefs());
        Executor.setup(model);
    }

    @Test
    public void execute_newPerson_success() throws ParseException {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getNotor(), new UserPrefs());
        expectedModel.createPerson(validPerson);

        CommandTestUtil.assertExecuteSuccess(new PersonCreateCommand(null, validPerson), model,
                String.format(PersonCreateExecutor.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() throws ParseException {
        Person personInList = model.getNotor().getPersonList().get(0);
        assertExecuteFailure(new PersonCreateCommand(null, personInList), model,
                PersonCreateExecutor.MESSAGE_DUPLICATE_PERSON);
    }

}

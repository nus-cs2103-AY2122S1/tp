package seedu.edrecord.logic.commands;

import static seedu.edrecord.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.edrecord.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.edrecord.testutil.TypicalModules.getTypicalModuleSystem;
import static seedu.edrecord.testutil.TypicalPersons.getTypicalEdRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.edrecord.model.Model;
import seedu.edrecord.model.ModelManager;
import seedu.edrecord.model.UserPrefs;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEdRecord(), getTypicalModuleSystem(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getEdRecord(), getTypicalModuleSystem(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getEdRecord().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

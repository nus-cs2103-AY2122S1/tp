package seedu.address.logic.candidate;

import static seedu.address.logic.candidate.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.candidate.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCandidateCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHrManager(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCandidateCommand(validPerson), model,
                String.format(AddCandidateCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getHrManager().getPersonList().get(0);
        assertCommandFailure(new AddCandidateCommand(personInList), model,
               String.format(AddCandidateCommand.MESSAGE_DUPLICATE_PERSON, personInList.getEmail()));
    }

}

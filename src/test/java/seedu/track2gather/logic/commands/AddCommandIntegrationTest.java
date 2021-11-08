package seedu.track2gather.logic.commands;

import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.person.Person;
import seedu.track2gather.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getTrack2Gather().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

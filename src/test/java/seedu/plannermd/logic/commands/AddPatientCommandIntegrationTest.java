package seedu.plannermd.logic.commands;

import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.TypicalPersons.getTypicalPlannerMd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.addcommand.AddPatientCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPatientCommand}.
 */
public class AddPatientCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getPlannerMd(), new UserPrefs());
        expectedModel.addPatient(validPerson);

        assertCommandSuccess(new AddPatientCommand(validPerson), model,
                String.format(AddPatientCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getPlannerMd().getPatientList().get(0);
        assertCommandFailure(new AddPatientCommand(personInList), model, AddPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }

}

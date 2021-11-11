package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.getTypicalInternship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternship(), new UserPrefs());
    }

    @Test
    public void execute_newApplication_success() {
        Application validApplication = new ApplicationBuilder().build();

        Model expectedModel = new ModelManager(model.getInternship(), new UserPrefs());
        expectedModel.addApplication(validApplication);

        assertCommandSuccess(new AddCommand(validApplication), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validApplication), expectedModel);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        Application applicationInList = model.getInternship().getApplicationList().get(0);
        assertCommandFailure(new AddCommand(applicationInList),
                model, AddCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

}

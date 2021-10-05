package seedu.tracker.logic.commands;

import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tracker.testutil.TypicalModules.getTypicalModuleTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tracker.model.Model;
import seedu.tracker.model.ModelManager;
import seedu.tracker.model.UserPrefs;
import seedu.tracker.model.module.Module;
import seedu.tracker.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModuleTracker(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        // initialise new valid module
        ModuleBuilder validModuleBuilder = new ModuleBuilder().withCode("CS1231").withTitle("Discrete Structures")
                .withDescription("Introduces mathematical tools required in the study of computer science")
                .withMc(4);
        Module validModule = validModuleBuilder.build();

        Model expectedModel = new ModelManager(model.getModuleTracker(), new UserPrefs());
        expectedModel.addModule(validModule);

        assertCommandSuccess(new AddCommand(validModule), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validModule), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Module personInList = model.getModuleTracker().getModuleList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_MODULE);
    }

}

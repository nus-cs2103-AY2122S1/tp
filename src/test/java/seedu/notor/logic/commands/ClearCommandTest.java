package seedu.notor.logic.commands;

import static seedu.notor.testutil.TypicalPersons.getTypicalNotor;

import org.junit.jupiter.api.Test;

import seedu.notor.model.Model;
import seedu.notor.model.ModelManager;
import seedu.notor.model.Notor;
import seedu.notor.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyNotor_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertExecuteSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyNotor_success() {
        Model model = new ModelManager(getTypicalNotor(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalNotor(), new UserPrefs());
        expectedModel.setNotor(new Notor());

        CommandTestUtil.assertExecuteSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

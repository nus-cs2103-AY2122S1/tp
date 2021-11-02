package seedu.address.logic.commands.person;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalConthacks;

import org.junit.jupiter.api.Test;

import seedu.address.model.Conthacks;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearPersonCommandTest {

    @Test
    public void execute_emptyConthacks_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearPersonCommand(), model, ClearPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyConthacks_success() {
        Model model = new ModelManager(getTypicalConthacks(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalConthacks(), new UserPrefs());
        expectedModel.setConthacks(new Conthacks());

        assertCommandSuccess(new ClearPersonCommand(), model, ClearPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

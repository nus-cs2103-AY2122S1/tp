package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearFacilitiesCommandTest {

    @Test
    public void execute_emptySportsPA_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        assertCommandSuccess(
                new ClearFacilitiesCommand(), model, ClearFacilitiesCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySportsPA_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.resetFacilityList();

        assertCommandSuccess(
                new ClearFacilitiesCommand(), model, ClearFacilitiesCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

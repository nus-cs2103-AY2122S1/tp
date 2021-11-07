package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSportsPa.getTypicalSportsPa;
import static seedu.address.testutil.TypicalSportsPa.getTypicalSportsPaEmptyFacilityList;

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
        Model model = new ModelManager(getTypicalSportsPa(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSportsPaEmptyFacilityList(), new UserPrefs());
        assertCommandSuccess(
                new ClearFacilitiesCommand(), model, ClearFacilitiesCommand.MESSAGE_SUCCESS, expectedModel);
    }
}

package seedu.siasa.logic.commands.policy;

import static seedu.siasa.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.siasa.testutil.TypicalSiasa.getTypicalSiasa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.siasa.logic.commands.CommandTestUtil;
import seedu.siasa.model.Model;
import seedu.siasa.model.ModelManager;
import seedu.siasa.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPolicyCommand.
 */
public class ListPolicyCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSiasa(), new UserPrefs());
        expectedModel = new ModelManager(model.getSiasa(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(new ListPolicyCommand(), model, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        assertCommandSuccess(new ListPolicyCommand(), model, expectedModel);
    }
}

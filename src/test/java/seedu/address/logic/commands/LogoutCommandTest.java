package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.LoginStatus;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code LogoutCommand}.
 */
public class LogoutCommandTest {

    @Test
    public void execute_inStaffLoginStatus_success() {
        LoginStatus.setLoginStatus(LoginStatus.STAFF);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new LogoutCommand(), model, LogoutCommand.MESSAGE_ALREADY_IN_STATUS, expectedModel);
    }

    @Test
    public void execute_inManagerLoginStatus_success() {
        LoginStatus.setLoginStatus(LoginStatus.MANAGER);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new LogoutCommand(), model, LogoutCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

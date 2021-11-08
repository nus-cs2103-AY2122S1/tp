package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.status.LoginStatus;
import seedu.address.commons.util.EncryptUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.account.Password;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code LoginCommand}.
 */
public class LoginCommandTest {

    private static final String CORRECT_PLAINTEXT_PASSWORD = "123456";
    private static final String WRONG_PLAINTEXT_PASSWORD = "654321";

    @Test
    public void execute_inStaffLoginStatus_success() throws Exception {
        LoginStatus.setLoginStatus(LoginStatus.STAFF);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        Password password = new Password(EncryptUtil.hash(CORRECT_PLAINTEXT_PASSWORD));

        assertCommandSuccess(new LoginCommand(password), model, LoginCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_inStaffLoginStatus_failure() throws Exception {
        LoginStatus.setLoginStatus(LoginStatus.STAFF);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        Password password = new Password(EncryptUtil.hash(WRONG_PLAINTEXT_PASSWORD));

        assertCommandSuccess(new LoginCommand(password), model, LoginCommand.MESSAGE_FAILURE, expectedModel);
    }

    @Test
    public void execute_inManagerLoginStatus_success() throws Exception {
        LoginStatus.setLoginStatus(LoginStatus.MANAGER);
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        Password password = new Password(EncryptUtil.hash(CORRECT_PLAINTEXT_PASSWORD));

        assertCommandSuccess(new LoginCommand(password), model, LoginCommand.MESSAGE_ALREADY_IN_STATUS, expectedModel);
        LoginStatus.setLoginStatus(LoginStatus.MANAGER);
    }

}

package seedu.address.logic.commands;

import seedu.address.commons.status.LoginStatus;
import seedu.address.model.Model;
import seedu.address.model.account.Password;

/**
 * Logs in as a manager.
 */
public class LoginCommand extends Command {

    /**
     * Stands for login command.
     */
    public static final String COMMAND_WORD = "login";

    /**
     * Stands for the message of login command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Logs in as a manager by password.\n"
        + "Parameters: PASSWORD (case-sensitive and must be not empty, default by "
        + Password.DEFAULT_PLAINTEXT_PASSWORD + ")\n"
        + "Example: " + COMMAND_WORD + " " + Password.DEFAULT_PLAINTEXT_PASSWORD;

    /**
     * Stands for the message of login successfully.
     */
    public static final String MESSAGE_SUCCESS = "Logged in successfully!";

    /**
     * Stands for the message of login failed.
     */
    public static final String MESSAGE_FAILURE = "Failed to login!";

    /**
     * Stands for the message of login status.
     */
    public static final String MESSAGE_ALREADY_IN_STATUS = "You are already in MANAGER login status!";

    private final Password password;

    /**
     * Constructs LoginCommand.
     *
     * @param password the login password.
     */
    public LoginCommand(Password password) {
        this.password = password;
    }

    /**
     * Overrides and executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to login command.
     */
    @Override
    public CommandResult execute(Model model) {
        if (LoginStatus.getLoginStatus() == LoginStatus.MANAGER) {
            return new CommandResult(MESSAGE_ALREADY_IN_STATUS);
        }
        String inputPassword = password.value;
        String readPassword = model.getAccount().getPassword().value;
        if (inputPassword.equals(readPassword)) {
            LoginStatus.setLoginStatus(LoginStatus.MANAGER);
            return new CommandResult(MESSAGE_SUCCESS);
        }
        return new CommandResult(MESSAGE_FAILURE);
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof LoginCommand;
    }
}

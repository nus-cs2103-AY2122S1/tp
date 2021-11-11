package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import seedu.address.commons.util.PasswordUtil;
import seedu.address.model.Model;

public class PasswordCommand extends Command {
    public static final String COMMAND_WORD = "password";

    public static final String CORRECT_PASSWORD_FORMAT =
            "Password should have "
                    + PasswordUtil.MIN_PASSWORD_LENGTH
                    + " to "
                    + PasswordUtil.MAX_PASSWORD_LENGTH
                    + " characters (inclusive) and SHOULD NOT contain "
                    + "SPACE OR "
                    + PasswordUtil.SLASH
                    + " OR "
                    + PasswordUtil.HYPHEN;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OLD_PASSWORD + " oldPassword "
            + PREFIX_NEW_PASSWORD + " newPassword";

    public static final String MESSAGE_SUCCESS = "Password updated!";
    public static final String MESSAGE_WRONG_PASSWORD = "Wrong password. Please try again!";
    public static final String MESSAGE_FAIL = "Something went wrong. Please try again!";
    public static final String MESSAGE_INVALID_PASSWORD = "Invalid password!";

    private final String oldPassword;
    private final String newPassword;

    /**
     * Creates a Command with old and new passwords.
     *
     * @param oldPassword the old password received from user.
     * @param newPassword the new password received from user.
     */
    public PasswordCommand(String oldPassword, String newPassword) {
        // passwords should not be empty.
        assert(!oldPassword.isEmpty() && !newPassword.isEmpty());
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PasswordCommand // instanceof handles nulls
                && oldPassword.equals(((PasswordCommand) other).getOldPassword())
                && newPassword.equals(((PasswordCommand) other).getNewPassword()));
    }
}

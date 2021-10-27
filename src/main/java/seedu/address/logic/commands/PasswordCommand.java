package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import seedu.address.model.Model;

public class PasswordCommand extends Command {
    public static final String COMMAND_WORD = "password";

    public static final int MIN_PASSWORD_LENGTH = 8;

    public static final String CORRECT_PASSWORD_FORMAT =
            "Password should contain LETTERS, SPECIAL CHARACTERS and NUMBERS "
            + "at least length of "
            + MIN_PASSWORD_LENGTH;

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OLD_PASSWORD + " oldPassword "
            + PREFIX_NEW_PASSWORD + " newPassword"
            + System.lineSeparator()
            + CORRECT_PASSWORD_FORMAT;

    public static final String MESSAGE_SUCCESS = "Password updated!";
    public static final String MESSAGE_WRONG_PASSWORD = "Password updated!";
    public static final String MESSAGE_FAIL = "Something went wrong. Please try again!";

    private final String oldPassword;
    private final String newPassword;

    /**
     * Creates a Command with old and new passwords.
     *
     * @param oldPassword the old password received from user.
     * @param newPassword the new password received from user.
     */
    public PasswordCommand(String oldPassword, String newPassword) {
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

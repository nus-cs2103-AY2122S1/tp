package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class PasswordCommand extends Command {
    public static final String COMMAND_WORD = "password";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OLD_PASSWORD + " oldpassword "
            + PREFIX_NEW_PASSWORD + " newpassword";

    private String oldPassword;
    private String newPassword;

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
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Password updated!");
    }
}

package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import seedu.address.logic.commands.PasswordCommand;

public class PasswordUtil {
    /**
     * Returns a password command string for adding the {@code person}.
     */
    public static String getPasswordCommand(String oldPassword, String newPassword) {
        return PasswordCommand.COMMAND_WORD + " " + getPasswordDetails(oldPassword, newPassword);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPasswordDetails(String oldPassword, String newPassword) {
        return PREFIX_OLD_PASSWORD + " " + oldPassword + " " + PREFIX_NEW_PASSWORD + " " + newPassword + " ";
    }

    /**
     * Returns a password command argument string with some repeated flags.
     */
    public static String getRepeatedFlag(String oldPassword, String newPassword) {
        return PasswordCommand.COMMAND_WORD + " "
                + PREFIX_OLD_PASSWORD + " " + oldPassword + " "
                + PREFIX_OLD_PASSWORD + " " + oldPassword + " "
                + PREFIX_NEW_PASSWORD + " " + newPassword + " ";
    }

    /**
     * Returns a password command argument string with a missing flag.
     */
    public static String getMissingFlag(String oldPassword) {
        return PasswordCommand.COMMAND_WORD + " " + PREFIX_OLD_PASSWORD + " " + oldPassword;
    }
}

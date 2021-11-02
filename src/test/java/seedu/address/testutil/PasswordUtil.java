package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;

import seedu.address.logic.commands.PasswordCommand;

public class PasswordUtil {
    /**
     * Returns an password command string for adding the {@code person}.
     */
    public static String getPasswordCommand(String oldPassword, String newPassword) {
        return PasswordCommand.COMMAND_WORD + " " + getPasswordDetails(oldPassword, newPassword);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPasswordDetails(String oldPassword, String newPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_OLD_PASSWORD + oldPassword + " ");
        sb.append(PREFIX_NEW_PASSWORD + newPassword + " ");
        return sb.toString();
    }

    public static String getRepeatedFlag(String oldPassword, String newPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_OLD_PASSWORD + oldPassword + " ");
        sb.append(PREFIX_OLD_PASSWORD + oldPassword + " ");
        sb.append(PREFIX_NEW_PASSWORD + newPassword + " ");
        return PasswordCommand.COMMAND_WORD + " " + sb;
    }

    public static String getMissingFlag(String oldPassword) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_OLD_PASSWORD + oldPassword + " ");
        return PasswordCommand.COMMAND_WORD + " " + sb;
    }
}

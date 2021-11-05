package seedu.address.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.PasswordCommand;

/**
 * Utility class for checking password validity
 */
public class PasswordUtil {

    /**
     * Checks if the given password satisfies the requirements.
     * Returns true if the password meets the requirements, false otherwise.
     *
     * @param password the input password from user.
     * @return Boolean value of the check result.
     */
    public static boolean isValidPassword(String password) {
        Pattern slash = Pattern.compile(PasswordCommand.slash);
        Pattern hyphen = Pattern.compile(PasswordCommand.hyphen);
        Pattern space = Pattern.compile(PasswordCommand.space);

        Matcher hasSlash = slash.matcher(password);
        Matcher hasHyphen = hyphen.matcher(password);
        Matcher hasSPace = space.matcher(password);

        return password.length() >= PasswordCommand.MIN_PASSWORD_LENGTH
                && password.length() <= PasswordCommand.MAX_PASSWORD_LENGTH
                && !hasSlash.find()
                && !hasHyphen.find()
                && (PasswordCommand.allowSpace || !hasSPace.find());
    }

}

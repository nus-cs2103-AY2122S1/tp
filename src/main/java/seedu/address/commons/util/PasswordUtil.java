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
        Pattern letter = Pattern.compile("[a-zA-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Pattern slash = Pattern.compile("/");

        Matcher hasLetter = letter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);
        Matcher hasSlash = slash.matcher(password);

        return password.length() >= PasswordCommand.MIN_PASSWORD_LENGTH
                && password.length() <= PasswordCommand.MAX_PASSWORD_LENGTH
                && hasLetter.find() && hasDigit.find()
                && hasSpecial.find() && !hasSlash.find();
    }

}

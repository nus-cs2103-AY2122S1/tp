package seedu.address.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for checking password validity
 */
public class PasswordUtil {

    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 32;
    public static final String SLASH = "/";
    public static final String HYPHEN = "-";
    public static final String SPACE = " ";

    /**
     * Checks if the given password satisfies the requirements.
     * Returns true if the password meets the requirements, false otherwise.
     *
     * @param password the input password from user.
     * @return Boolean value of the check result.
     */
    public static boolean isValidPassword(String password) {
        Pattern slash = Pattern.compile(SLASH);
        Pattern hyphen = Pattern.compile(HYPHEN);
        Pattern space = Pattern.compile(SPACE);

        Matcher hasSlash = slash.matcher(password);
        Matcher hasHyphen = hyphen.matcher(password);
        Matcher hasSPace = space.matcher(password);

        Boolean isValidLength = password.length() >= MIN_PASSWORD_LENGTH
                && password.length() <= MAX_PASSWORD_LENGTH;
        Boolean isValidSyntax = !hasSlash.find()
                && !hasHyphen.find()
                && !hasSPace.find();

        return isValidLength && isValidSyntax;
    }

}

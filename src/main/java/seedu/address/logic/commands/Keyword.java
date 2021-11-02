package seedu.address.logic.commands;

/**
 * Class created solely to be used for parsing keyword validity.
 */
public class Keyword {
    // keywords should be nonempty, and consist only of alphanumeric characters
    private static final String VALIDATION_REGEX = "\\p{Alnum}+[ \\p{Alnum}]*";

    // constructor kept private to prevent use
    private Keyword() {}

    public static boolean isValidKeyword(String test) {
        return test.matches(VALIDATION_REGEX);
    }

}

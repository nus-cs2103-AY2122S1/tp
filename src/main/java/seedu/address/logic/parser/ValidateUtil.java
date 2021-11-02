package seedu.address.logic.parser;

/**
 * Contains utility methods used for validating inputs if required
 */
public class ValidateUtil {

    public static final String DAY_OF_WEEK_CONSTRAINTS = "Integer for day must be 1-7";

    /**
     * Tests if integer is valid for DayOfWeek java class
     * @param test int to test
     * @return true if int is valid
     */
    public static boolean validDayOfWeekInteger(int test) {
        return test >= 1 && test <= 7;
    }

    /**
     * Tests if a String is empty or only whitespace.
     * @param str to check
     * @return true if the String is empty or only contains whitespace.
     */
    public static boolean isEmptyOrOnlyWhitespace(String str) {
        String trimmed = str.trim();
        return trimmed.equals("");
    }
}

package seedu.address.logic.commands.util;

/**
 * Represents a utility class for the Command package
 */
public class CommandUtil {

    /**
     * Formats command words such that the last letter is capitalised
     * @param commandWord The command word to be formatted.
     * @return A string representing a formatted command word where the last letter is capitalised.
     */
    public static String formatCommandWord(String commandWord) {
        String lastLetter = commandWord.substring(commandWord.length() - 1).toUpperCase();
        return commandWord.substring(0, commandWord.length() - 1) + lastLetter;
    }
}

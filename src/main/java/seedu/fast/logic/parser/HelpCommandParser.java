package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_HELP_COMMAND_FORMAT;

import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.parser.exceptions.HelpParseException;

public class HelpCommandParser implements Parser<HelpCommand> {

    public static final String[] COMMAND_LIST = new String[]{"Quick Start", "Add", "Appointment", "Clear", "Delete",
        "Edit", "Find", "List", "Help", "Remark", "Sort", "Tag", "PriorityTag", "Misc"};

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public HelpCommand parse(String args) {
        return new HelpCommand(args); //Since none of the inputs are valid, we can return the arg.
    }

    /**
     * Extracts the arguments from a help command.
     *
     * @param commandText The input text.
     * @return The args of the help command, or "" if there is no or invalid args.
     * @throws HelpParseException if help is not followed by a valid arg
     */
    public static String getArgs (String commandText) throws HelpParseException{

        // if there are no args
        if (commandText.split(" ").length == 1) {
            return "";
        }

        String arg = commandText.substring(HelpCommand.COMMAND_WORD.length());
        String trimmedArgs = arg.trim();
        String capitalisedArg = capitaliseFirstLetters(trimmedArgs);

            if (isCommandWord(capitalisedArg)) {
                return capitalisedArg;

            } else { // if the arg does not match a given command, return ""
                throw new HelpParseException(
                        String.format(MESSAGE_INVALID_HELP_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
            }

    }

    /**
     * Checks if the arg is a command word.
     *
     * @param arg the input argument
     * @return true if it is a command word, false otherwise
     */
    public static boolean isCommandWord(String arg) {
        for (String s : COMMAND_LIST) {
            if (s.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    public static String capitaliseFirstLetters(String inputString) {
        String[] words = inputString.split(" ");
        StringBuilder capitalisedWordsBuilder = new StringBuilder();

        // Capitalise the start of each word in the args
        for (String s : words) {
            // Capitalises the first letter of the word
            capitalisedWordsBuilder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        return capitalisedWordsBuilder.toString();
    }
}

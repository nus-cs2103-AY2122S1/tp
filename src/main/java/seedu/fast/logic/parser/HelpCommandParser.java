package seedu.fast.logic.parser;

import seedu.fast.logic.commands.HelpCommand;

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
     * @return The args of the help command, or "" if there is no args.
     */
    public static String getArgs(String commandText) {

        // if there are no args
        if (commandText.split(" ").length == 1) {
            return "";
        }

        String arg = commandText.substring(HelpCommand.COMMAND_WORD.length()).trim();
        String[] args = arg.split(" ");
        StringBuilder capitalisedArgBuilder = new StringBuilder();

        // Capitalise the start of each word in the args
        for (String s : args) {
            // Capitalises the first letter of the word
            capitalisedArgBuilder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }

        String capitalisedArg = capitalisedArgBuilder.toString();
        if (isCommandWord(capitalisedArg)) {
            return capitalisedArg;

        } else { // if the arg does not match a given command, return ""
            return "";
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
}

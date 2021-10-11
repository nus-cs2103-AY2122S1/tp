package seedu.fast.logic.parser;

import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand> {

    public static final String[] COMMAND_LIST = new String[]{"Quick Start", "Add", "Appointment", "Clear", "Delete",
        "Edit", "Find", "List", "Help", "Remark", "Sort", "Tag", "PriorityTag", "Misc"};

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String capitalisedArg = trimmedArgs.substring(0, 1).toUpperCase() + trimmedArgs.substring(1);

        // Compares the args with the list of command keywords

        if (isCommandWord(capitalisedArg)) {
            return new HelpCommand(capitalisedArg);
        }


        // Returns an empty help command if none matches
        return new HelpCommand("");
    }

    /**
     * Extracts the arguments from a help command.
     *
     * @param commandText The input text.
     * @return The args of the help command, or "" if there is no args.
     */
    public static String getArgs(String commandText) {
        if (commandText.split(" ").length == 1) {
            return "";
        }
        String arg = commandText.substring(4).trim();
        String[] args = arg.split(" ");
        StringBuilder capitalisedArg = new StringBuilder();

        //String capitalisedArg = arg.substring(0, 1).toUpperCase() + arg.substring(1);
        for (String s : args) {
            capitalisedArg.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        }
        if (isCommandWord(capitalisedArg.toString())) {
            return capitalisedArg.toString();
        } else { // if the arg does not match a given command, return ""
            return "";
        }
    }

    public static boolean isCommandWord(String arg) {
        for (String s : COMMAND_LIST) {
            if (s.equals(arg)) {
                return true;
            }
        }
        return false;
    }
}

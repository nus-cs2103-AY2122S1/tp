package seedu.fast.logic.parser;

import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand>{

    public static final String[] COMMAND_LIST = new String[]{"Quick Start", "Add", "Appointment", "Clear", "Delete",
        "Edit", "Find", "List", "Help", "Remark", "Sort", "Tag", "Priority Tag", "Misc"};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        for (String s : COMMAND_LIST) {
            if (s.equals(trimmedArgs)) {
                return new HelpCommand(trimmedArgs);
            }
        }

        return new HelpCommand("");
    }
}

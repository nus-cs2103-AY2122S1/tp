package seedu.fast.logic.parser;

import seedu.fast.logic.commands.HelpCommand;

public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public HelpCommand parse(String args) {
        return new HelpCommand(args); //Since none of the inputs are valid, we can return the arg.
    }


}

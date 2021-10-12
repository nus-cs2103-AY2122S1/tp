package seedu.fast.logic.parser;

import seedu.fast.logic.commands.HelpCommand;

public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public HelpCommand parse(String args) {
        // since the execution of the help command is delayed, we can just pass the original arg here
        // and parse it later when the HelpWindow is called
        return new HelpCommand(args);
    }


}

package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_HELP_COMMAND_FORMAT;

import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.parser.exceptions.HelpParseException;
import seedu.fast.logic.parser.ParserUtil;

public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public HelpCommand parse(String args) {
        return new HelpCommand(args); //Since none of the inputs are valid, we can return the arg.
    }



}

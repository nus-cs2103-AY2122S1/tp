package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.UnenrollCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnenrollCommandParser implements Parser<UnenrollCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnenrollCommand
     * and returns a UnenrollCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnenrollCommand parse(String args) throws ParseException {
        return ParserUtil.parseUnenrollArgs(args);
    }
}

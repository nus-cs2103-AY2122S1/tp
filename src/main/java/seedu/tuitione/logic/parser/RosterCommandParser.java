package seedu.tuitione.logic.parser;

import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.commons.util.StringUtil;
import seedu.tuitione.logic.commands.RosterCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RosterCommand Object
 */
public class RosterCommandParser implements Parser<RosterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RosterCommand
     * and returns a RosterCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public RosterCommand parse(String args) throws ParseException {
        if (!StringUtil.isAStringedNumber(args)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RosterCommand.MESSAGE_USAGE));
        }
        Index index = ParserUtil.parseIndex(args);
        return new RosterCommand(index);
    }

}

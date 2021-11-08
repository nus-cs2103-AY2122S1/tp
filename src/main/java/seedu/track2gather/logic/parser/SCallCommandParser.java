package seedu.track2gather.logic.parser;

import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.logic.commands.SCallCommand;
import seedu.track2gather.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SCallCommand object
 */
public class SCallCommandParser implements Parser<SCallCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SCallCommand
     * and returns a SCallCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SCallCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SCallCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SCallCommand.MESSAGE_USAGE), pe);
        }
    }

}

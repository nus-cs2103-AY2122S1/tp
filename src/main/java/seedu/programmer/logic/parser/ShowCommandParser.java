package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.ShowCommand;
import seedu.programmer.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns a ShowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ShowCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE), pe);
        }
    }

}
package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ElistmCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ElistmCommandParser implements Parser<ElistmCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ElistmCommand
     * and returns a ElistmCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ElistmCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ElistmCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ElistmCommand.MESSAGE_USAGE), pe);
        }
    }

}

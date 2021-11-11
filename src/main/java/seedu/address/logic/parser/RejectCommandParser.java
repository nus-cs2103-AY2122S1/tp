package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RejectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RejectCommand object
 */
public class RejectCommandParser implements Parser<RejectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RejectCommand
     * and returns an RejectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RejectCommand parse(String args) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(args);
            return new RejectCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                    RejectCommand.MESSAGE_USAGE), pe);
        }

    }
}

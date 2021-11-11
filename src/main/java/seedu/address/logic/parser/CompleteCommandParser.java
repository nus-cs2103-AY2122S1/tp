package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses input arguments and creates a new CompleteCommand object
 */
public class CompleteCommandParser implements Parser<CompleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CompleteCommand
     * and returns a CompleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompleteCommand parse(String args) throws ParseException {

        try {
            Index index = ParserUtil.parseIndex(args);
            return new CompleteCommand(index);

        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX + "\n%1$s",
                            CompleteCommand.MESSAGE_USAGE), pe);
        }

    }

}

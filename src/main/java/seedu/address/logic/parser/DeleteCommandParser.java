package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_FLAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT_FLAG);
        String indexString = args;
        Boolean isEventFlag = argMultimap.getValue(PREFIX_EVENT_FLAG).isPresent();

        if (isEventFlag) {
            String eventString = argMultimap.getValue(PREFIX_EVENT_FLAG).get();
            String preamble = argMultimap.getPreamble();

            if ((!eventString.isBlank() && !preamble.isBlank()) || (eventString.isBlank() && preamble.isBlank())) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }

            if (!preamble.isBlank()) {
                indexString = preamble;
            }
            if (!eventString.isBlank()) {
                indexString = eventString;
            }
        }

        try {
            System.out.println(indexString);
            Index index = ParserUtil.parseIndex(indexString);
            return new DeleteCommand(index, isEventFlag);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}

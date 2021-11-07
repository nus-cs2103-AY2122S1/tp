package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAllMarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteLastMarkCommand object
 */
public class DeleteAllMarkCommandParser implements Parser<DeleteAllMarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteLastMarkCommand
     * and returns an DeleteAllMarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAllMarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteAllMarkCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteAllMarkCommand(index);
    }

}

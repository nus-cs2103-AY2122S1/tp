package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_ID;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.DeleteGradeCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GradeCommand object
 */
public class DeleteGradeCommandParser implements Parser<DeleteGradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGradeCommand
     * and returns a DeleteGradeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteGradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteGradeCommand.MESSAGE_USAGE), pe);
        }

        Index id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());

        return new DeleteGradeCommand(index, id);
    }
}

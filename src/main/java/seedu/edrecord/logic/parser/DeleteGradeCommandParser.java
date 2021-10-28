package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.DeleteGradeCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.name.Name;

/**
 * Parses input arguments and creates a new GradeCommand object
 */
public class DeleteGradeCommandParser implements Parser<DeleteGradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GradeCommand
     * and returns an GradeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteGradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteGradeCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteGradeCommand.MESSAGE_USAGE), pe);
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        return new DeleteGradeCommand(index, name);
    }
}

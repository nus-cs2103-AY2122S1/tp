package seedu.edrecord.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Optional;

import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.GradeCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Grade.GradeStatus;
import seedu.edrecord.model.assignment.Score;

/**
 * Parses input arguments and creates a new GradeCommand object
 */
public class GradeCommandParser implements Parser<GradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GradeCommand
     * and returns an GradeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_SCORE, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_STATUS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE), pe);
        }

        Index id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());

        Optional<Score> score;
        if (argMultimap.getValue(PREFIX_SCORE).isPresent()) {
            score = Optional.of(ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get()));
        } else {
            score = Optional.empty();
        }

        GradeStatus status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Grade grade = new Grade(score, status);
        return new GradeCommand(index, id, grade);
    }
}

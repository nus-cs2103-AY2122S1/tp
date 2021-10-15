package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_GRADE;

import java.util.stream.Stream;

import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.GradeCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class GradeCommandParser implements Parser<GradeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GradeCommand
     * and returns an GradeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSESSMENT, PREFIX_GRADE);
        if (!arePrefixesPresent(argMultimap, PREFIX_ASSESSMENT, PREFIX_GRADE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        String assessment = ParserUtil.parseAssessment(argMultimap.getValue(PREFIX_ASSESSMENT).get()).toUpperCase();
        Integer grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());

        return new GradeCommand(index, assessment, grade);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

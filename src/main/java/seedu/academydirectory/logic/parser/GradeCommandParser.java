package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_GRADE;

import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.commons.exceptions.IllegalValueException;
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

        Index index;
        String assessment;
        Integer grade;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            assessment = ParserUtil.parseAssessment(argMultimap.getValue(PREFIX_ASSESSMENT).get()).toUpperCase();
            grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GradeCommand.MESSAGE_USAGE), ive);
        }
        return new GradeCommand(index, assessment, grade);
    }

}

package seedu.edrecord.logic.parser;

import static seedu.edrecord.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.stream.Stream;

import seedu.edrecord.logic.commands.MakeAssignmentCommand;
import seedu.edrecord.logic.parser.exceptions.ParseException;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.assignment.Weightage;
import seedu.edrecord.model.name.Name;

public class MakeAssignmentCommandParser implements Parser<MakeAssignmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code MakeAssignmentCommand}
     * and returns a {@code MakeAssignmentCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MakeAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_WEIGHTAGE, PREFIX_SCORE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_WEIGHTAGE, PREFIX_SCORE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MakeAssignmentCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Weightage weightage = ParserUtil.parseWeightage(argMultimap.getValue(PREFIX_WEIGHTAGE).get());
        Score maxScore = ParserUtil.parseScore(argMultimap.getValue(PREFIX_SCORE).get());
        Assignment asg = new Assignment(name, weightage, maxScore);
        return new MakeAssignmentCommand(asg);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

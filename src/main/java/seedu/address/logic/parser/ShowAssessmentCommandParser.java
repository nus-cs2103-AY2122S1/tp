package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;
import java.util.stream.Stream;

import seedu.address.logic.commands.ShowAssessmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Assessment;

public class ShowAssessmentCommandParser implements Parser<ShowAssessmentCommand> {
    @Override
    public ShowAssessmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSESSMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_ASSESSMENT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowAssessmentCommand.MESSAGE_USAGE));
        }

        Assessment assessment = ParserUtil.parseAssessment(argMultimap.getValue(PREFIX_ASSESSMENT).get());

        return new ShowAssessmentCommand(assessment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSESSMENT;

import seedu.address.logic.commands.AddAssessmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Assessment;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddAssessmentCommandParser implements Parser<AddAssessmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAssessmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ASSESSMENT);

        if (argMultimap.getValue(PREFIX_ASSESSMENT).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssessmentCommand.MESSAGE_USAGE));
        }

        Assessment assessment = ParserUtil.parseAssessment(argMultimap.getValue(PREFIX_ASSESSMENT).get());

        return new AddAssessmentCommand(assessment);
    }

}

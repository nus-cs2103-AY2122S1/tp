package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_AND_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SoonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.application.InterviewDateAndTimePredicate;
import seedu.address.model.application.SoonDeadlinePredicate;

/**
 * Parses input arguments and creates a new SoonCommand object
 */
public class SoonCommandParser implements Parser<SoonCommand> {

    private static final int PREFIX_AND_KEYWORD_SIZE = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the SoonCommand
     * and returns a SoonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SoonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE_OF_APPLICATION,
                        PREFIX_INTERVIEW_DATE_AND_TIME);

        if (argMultimap.getSize() != PREFIX_AND_KEYWORD_SIZE || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SoonCommand.MESSAGE_USAGE));
        }

        Prefix prefix;

        if (argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).isPresent()) {
            prefix = PREFIX_DEADLINE_OF_APPLICATION;
        } else {
            assert(argMultimap.getValue(PREFIX_INTERVIEW_DATE_AND_TIME).isPresent());
            prefix = PREFIX_INTERVIEW_DATE_AND_TIME;
        }

        Index days;
        try {
            days = ParserUtil.parseDays(argMultimap
                    .getValue(prefix)
                    .get()
                    .trim());
        } catch (ParseException pe) {
            throw new ParseException(String.format(SoonCommand.MESSAGE_INVALID_DAYS + "\n%1$s",
                    SoonCommand.MESSAGE_USAGE), pe);
        }

        return prefix.equals(PREFIX_DEADLINE_OF_APPLICATION)
                ? new SoonCommand(days, new SoonDeadlinePredicate(days))
                : new SoonCommand(days, new InterviewDateAndTimePredicate(days));

    }
}

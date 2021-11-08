package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.interview.EditInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditInterviewCommandParser implements Parser<EditInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditInterviewCommand
     * and returns an EditInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditInterviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POSITION, PREFIX_DATE, PREFIX_TIME,
                        PREFIX_DURATION, PREFIX_INTERVIEW_STATUS);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, pe.getMessage())
                    + EditInterviewCommand.MESSAGE_USAGE, pe);
        }

        EditInterviewCommand.EditInterviewDescriptor editInterviewDescriptor =
                new EditInterviewCommand.EditInterviewDescriptor();

        if (argMultimap.getValue(PREFIX_POSITION).isPresent()) {
            editInterviewDescriptor.setPosition(ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editInterviewDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editInterviewDescriptor
                    .setStartTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).orElse("")));
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editInterviewDescriptor.setDuration(ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }
        if (argMultimap.getValue(PREFIX_INTERVIEW_STATUS).isPresent()) {
            editInterviewDescriptor.setStatus(ParserUtil
                    .parseInterviewStatus(argMultimap.getValue(PREFIX_INTERVIEW_STATUS).get()));
        }

        if (!editInterviewDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditInterviewCommand.MESSAGE_NOT_EDITED);
        }

        return new EditInterviewCommand(index, editInterviewDescriptor);
    }

}

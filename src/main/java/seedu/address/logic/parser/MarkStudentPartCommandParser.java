package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkStudentPartCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Participation;

/**
 * Parses input arguments and creates a new MarkStudentPartCommand object
 */
public class MarkStudentPartCommandParser implements Parser<MarkStudentPartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkStudentPartCommand
     * and returns a MarkStudentPartCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkStudentPartCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_WEEK);

        String trimmedArgs = argMultimap.getPreamble().trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkStudentPartCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_WEEK).isPresent()) {
            try {
                List<Index> studentsToMark = ParserUtil.parseMultipleIndexWithoutDuplicates(trimmedArgs);
                int zeroIndexWeek = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());
                return new MarkStudentPartCommand(studentsToMark, zeroIndexWeek);
            } catch (NumberFormatException e) {
                throw new ParseException(String.format(Participation.MESSAGE_CONSTRAINTS,
                        Participation.FIRST_WEEK_OF_SEM, Participation.LAST_WEEK_OF_SEM));
            }
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkStudentPartCommand.MESSAGE_USAGE));
    }
}

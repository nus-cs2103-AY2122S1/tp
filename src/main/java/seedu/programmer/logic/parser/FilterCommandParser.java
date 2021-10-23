package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_CLASS_ID;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import seedu.programmer.logic.commands.FilterCommand;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.QueryStudentDescriptor;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENT_ID, PREFIX_CLASS_ID);

        // Initializing all the arguments as null at the beginning.
        String trimmedNameArg = trimPredicateArg(argMultimap, PREFIX_NAME);
        String trimmedSidArg = trimPredicateArg(argMultimap, PREFIX_STUDENT_ID);
        String trimmedCidArg = trimPredicateArg(argMultimap, PREFIX_CLASS_ID);
        QueryStudentDescriptor queryStudentDescriptor = getQueryStudentDescriptor(trimmedNameArg,
                                                                                  trimmedSidArg,
                                                                                  trimmedCidArg);

        return new FilterCommand(new StudentDetailContainsQueryPredicate(queryStudentDescriptor));
    }

    private String trimPredicateArg(ArgumentMultimap argMultimap, Prefix predicate) throws ParseException {
        String trimmedPredicateArg = null;

        if (argMultimap.getValue(predicate).isPresent()) {
            trimmedPredicateArg = argMultimap.getValue(predicate).get().trim();
            if (trimmedPredicateArg.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
        }
        return trimmedPredicateArg;
    }

    private QueryStudentDescriptor getQueryStudentDescriptor(String nameArg, String sidArg, String cidArg)
            throws ParseException {
        QueryStudentDescriptor queryStudentDescriptor =
                new QueryStudentDescriptor(nameArg, sidArg, cidArg);

        if (!queryStudentDescriptor.isAnyFieldToBeQueried()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        return queryStudentDescriptor;
    }
}

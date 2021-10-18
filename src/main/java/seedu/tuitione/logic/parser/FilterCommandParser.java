package seedu.tuitione.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_SUBJECT;

import seedu.tuitione.logic.commands.AddCommand;
import seedu.tuitione.logic.commands.FilterCommand;
import seedu.tuitione.logic.parser.exceptions.ParseException;
import seedu.tuitione.model.lesson.Subject;
import seedu.tuitione.model.student.Grade;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new {@code FilterCommand} object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,PREFIX_GRADE, PREFIX_SUBJECT);
        Grade grade = null;
        Subject subject = null;

        if (!argMultimap.getPreamble().isEmpty()
            || !areAnyPrefixesPresent(argMultimap, PREFIX_GRADE, PREFIX_SUBJECT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            grade = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
        }
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            subject = ParserUtil.parseSubjectArgs(argMultimap.getValue(PREFIX_SUBJECT).get());
        }

        return new FilterCommand(grade, subject);
    }

    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

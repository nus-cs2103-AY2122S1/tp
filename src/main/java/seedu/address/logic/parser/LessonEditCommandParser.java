package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANCEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OUTSTANDING_FEES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNCANCEL;
import static seedu.address.logic.parser.ParserUtil.INDEX_ARGS_COUNT_STUDENT_LESSON;
import static seedu.address.logic.parser.ParserUtil.LESSON_INDEX_ZERO_BASED;
import static seedu.address.logic.parser.ParserUtil.STUDENT_INDEX_ZERO_BASED;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LessonEditCommand;
import seedu.address.logic.commands.LessonEditCommand.EditLessonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Homework;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class LessonEditCommandParser implements Parser<LessonEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LessonEditCommand
     * and returns a LessonEditCommand object for execution.
     *
     * @param args String argument to be parsed.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public LessonEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECURRING, PREFIX_DATE, PREFIX_TIME,
                    PREFIX_SUBJECT, PREFIX_HOMEWORK, PREFIX_RATES, PREFIX_OUTSTANDING_FEES,
                    PREFIX_CANCEL, PREFIX_UNCANCEL);

        String[] preamble = ParserUtil.parsePreamble(argMultimap.getPreamble());
        if (preamble.length != INDEX_ARGS_COUNT_STUDENT_LESSON) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonEditCommand.MESSAGE_USAGE));
        }

        EditLessonDescriptor editLessonDescriptor = new EditLessonDescriptor();

        if (argMultimap.getValue(PREFIX_RECURRING).isPresent()) {
            Date endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_RECURRING).get())
                    .orElse(Date.MAX_DATE);
            editLessonDescriptor.setEndDate(endDate);
            editLessonDescriptor.setRecurring(true);
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get())
                    .orElseThrow(() -> new ParseException(Date.MESSAGE_CONSTRAINTS));
            editLessonDescriptor.setDate(date);
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editLessonDescriptor.setTimeRange(ParserUtil.parseTimeRange(argMultimap.getValue(PREFIX_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            editLessonDescriptor.setSubject(ParserUtil.parseSubject(argMultimap.getValue(PREFIX_SUBJECT).get()));
        }
        parseHomeworkForLessonEdit(argMultimap.getAllValues(PREFIX_HOMEWORK))
                .ifPresent(editLessonDescriptor::setHomeworkSet);

        if (argMultimap.getValue(PREFIX_RATES).isPresent()) {
            editLessonDescriptor.setLessonRate(ParserUtil.parseLessonRates(argMultimap.getValue(PREFIX_RATES).get()));
        }

        if (argMultimap.getValue(PREFIX_OUTSTANDING_FEES).isPresent()) {
            editLessonDescriptor.setOutstandingFees(ParserUtil.parseOutstandingFees(argMultimap
                    .getValue(PREFIX_OUTSTANDING_FEES).get()));
        }

        parseDatesForLessonEdit(argMultimap.getAllValues(PREFIX_CANCEL))
                .ifPresent(editLessonDescriptor::setCancelDates);
        parseDatesForLessonEdit(argMultimap.getAllValues(PREFIX_UNCANCEL))
                .ifPresent(editLessonDescriptor::setUncancelDates);

        if (!editLessonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(
                    String.format(LessonEditCommand.MESSAGE_NOT_EDITED, LessonEditCommand.MESSAGE_USAGE));
        }

        // index errors should come after field errors and not edited error
        Index studentIndex = ParserUtil.parseStudentIndex(preamble[STUDENT_INDEX_ZERO_BASED]);
        Index lessonIndex = ParserUtil.parseLessonIndex(preamble[LESSON_INDEX_ZERO_BASED]);

        return new LessonEditCommand(studentIndex, lessonIndex, editLessonDescriptor);
    }

    /**
     * Parses {@code Collection<String> homework} into a {@code Set<Homework>} if {@code homework} is non-empty.
     * If {@code homework} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Homework>} containing zero homework.
     */
    private Optional<Set<Homework>> parseHomeworkForLessonEdit(Collection<String> homework) throws ParseException {
        assert homework != null;

        if (homework.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> homeworkSet = homework.size() == 1 && homework.contains("")
                ? Collections.emptySet()
                : homework;
        return Optional.of(ParserUtil.parseHomeworkList(homeworkSet));
    }

    /**
     * Parses {@code Collection<String> homework} into a {@code Set<Homework>} if {@code homework} is non-empty.
     * If {@code homework} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Homework>} containing zero homework.
     */
    private Optional<Set<Date>> parseDatesForLessonEdit(Collection<String> dates) throws ParseException {
        assert dates != null;

        if (dates.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseDates(dates));
    }
}

package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LessonCancelCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Date;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;

public class LessonCancelCommandParser implements Parser<LessonCancelCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LessonDeleteCommand
     * and returns a LessonDeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonCancelCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);
        String preamble = argMultimap.getPreamble().strip();
        if (preamble.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Missing indices")); // todo
        }

        Index studentIndex;
        Index lessonIndex;

        try {
            Index[] studentLessonIndices = ParserUtil.parseIndices(preamble);
            studentIndex = studentLessonIndices[0];
            lessonIndex = studentLessonIndices[1];
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "Error parsing indices"), // todo
                    pe);
        }

        Optional<Date> cancelledDate = Optional.empty();
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            cancelledDate = Optional.of(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }

        return new LessonCancelCommand(studentIndex, lessonIndex, cancelledDate);
    }
}

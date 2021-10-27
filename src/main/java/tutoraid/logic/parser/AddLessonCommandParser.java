package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_CAPACITY;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_PRICE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_TIMING;

import java.util.ArrayList;

import tutoraid.commons.core.Messages;
import tutoraid.logic.commands.AddLessonCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Students;
import tutoraid.model.lesson.Timing;
import tutoraid.model.student.Student;

/**
 * Parses input arguments and creates a new AddLessonCommand object
 */
public class AddLessonCommandParser implements Parser<AddLessonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddLessonCommand
     * and returns an AddLessonCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LESSON_NAME, PREFIX_LESSON_CAPACITY,
                        PREFIX_LESSON_PRICE, PREFIX_LESSON_TIMING);

        // Lesson name is a required field (Lesson capacity, price and timing are optional fields)
        if (argMultimap.getValue(PREFIX_LESSON_NAME).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddLessonCommand.MESSAGE_USAGE));
        }

        LessonName lessonName = ParserUtil.parseLessonName(
                argMultimap.getValue(PREFIX_LESSON_NAME).get());
        Capacity capacity = ParserUtil.parseCapacity(
                argMultimap.getValue(PREFIX_LESSON_CAPACITY).orElse(""));
        Price price = ParserUtil.parsePrice(
                argMultimap.getValue(PREFIX_LESSON_PRICE).orElse(""));
        Timing timing = ParserUtil.parseTiming(
                argMultimap.getValue(PREFIX_LESSON_TIMING).orElse(""));
        Students students = new Students(new ArrayList<Student>());

        Lesson lesson = new Lesson(lessonName, capacity, price, students, timing);
        return new AddLessonCommand(lesson);
    }
}

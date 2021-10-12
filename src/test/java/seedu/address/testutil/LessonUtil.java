package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.LessonAddCommand;
import seedu.address.model.lesson.Lesson;

/**
 * A utility class for Lesson.
 */
public class LessonUtil {
    /**
     * Returns an add command string for adding the {@code lesson}.
     */
    public static String getLessonAddCommand(int index, Lesson lesson) {
        return LessonAddCommand.COMMAND_WORD + " " + index + " "
            + getLessonDetails(lesson);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getLessonDetails(Lesson lesson) {
        StringBuilder sb = new StringBuilder();
        if (lesson.isRecurring()) {
            sb.append(PREFIX_RECURRING + " ");
        }
        sb.append(PREFIX_DATE + lesson.getDate().toString() + " ");
        sb.append(PREFIX_TIME + lesson.getTimeRange().value + " ");
        sb.append(PREFIX_SUBJECT + lesson.getSubject().toString() + " ");
        lesson.getHomework().stream().forEach(
            s -> sb.append(PREFIX_HOMEWORK + s.description + " ")
        );
        return sb.toString();
    }
}

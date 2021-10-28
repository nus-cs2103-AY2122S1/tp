package tutoraid.testutil;

import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_CAPACITY;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_PRICE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_TIMING;

import tutoraid.logic.commands.AddLessonCommand;
import tutoraid.logic.commands.EditLessonCommand;
import tutoraid.model.lesson.Lesson;

/**
 * A utility class for Lesson.
 */
public class LessonUtil {
    /**
     * Returns an add command string for adding the {@code lesson}.
     *
     * @param lesson The lesson to be added
     * @return The command string for adding the lesson
     */
    public static String getAddCommand(Lesson lesson) {
        return AddLessonCommand.COMMAND_FLAG + " " + getLessonDetails(lesson);
    }

    /**
     * Returns an edit command string for editing a {@code lesson}.
     *
     * @param index  The index of the lesson to be edited
     * @param target The lesson to be edited to
     * @return The command string for editing a lesson to become the same as the target
     */
    public static String getEditCommand(int index, Lesson target) {
        return EditLessonCommand.COMMAND_FLAG + " " + index + " " + getLessonDetails(target);
    }

    /**
     * Returns the part of command string for the given {@code lesson}'s details.
     */
    public static String getLessonDetails(Lesson lesson) {
        return PREFIX_LESSON_NAME + lesson.getLessonName().lessonName + " "
                + PREFIX_LESSON_CAPACITY + lesson.getCapacity().capacity + " "
                + PREFIX_LESSON_PRICE + lesson.getPrice().price + " "
                + PREFIX_LESSON_TIMING + lesson.getTiming().timing + " ";
    }
}

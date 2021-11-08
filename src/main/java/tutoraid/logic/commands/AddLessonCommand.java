package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_CAPACITY;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_PRICE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_TIMING;

import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;

/**
 * Adds a lesson to TutorAid.
 */
public class AddLessonCommand extends AddCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Adds a lesson to TutorAid. "
                    + "\nParameters: "
                    + "\n%3$sLESSON NAME"
                    + "  [%4$sLESSON CAPACITY]"
                    + "  [%5$sLESSON PRICE]"
                    + "  [%6$sLESSON TIMING]"
                    + "\nExample:"
                    + "\n%1$s %2$s %3$sMaths 1 %4$s50 %5$s100 %6$sMon 1000-1200",
            COMMAND_WORD, COMMAND_FLAG, PREFIX_LESSON_NAME, PREFIX_LESSON_CAPACITY, PREFIX_LESSON_PRICE,
            PREFIX_LESSON_TIMING);

    public static final String MESSAGE_SUCCESS = "Successfully added %s to TutorAid.";
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists in TutorAid.";

    private final Lesson lesson;

    /**
     * Creates an AddLessonCommand to add the specified {@code Lesson}
     */
    public AddLessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        this.lesson = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasLesson(lesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        model.addLesson(lesson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, lesson.toNameString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLessonCommand // instanceof handles nulls
                && lesson.equals(((AddLessonCommand) other).lesson));
    }
}

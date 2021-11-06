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

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Adds a lesson to TutorAid. "
            + "Parameters: "
            + PREFIX_LESSON_NAME + "LESSON NAME "
            + PREFIX_LESSON_CAPACITY + "LESSON CAPACITY "
            + PREFIX_LESSON_PRICE + "LESSON PRICE "
            + PREFIX_LESSON_TIMING + "LESSON TIMING "
            + "Example: " + COMMAND_FLAG + " "
            + PREFIX_LESSON_NAME + "Maths 1 "
            + PREFIX_LESSON_CAPACITY + "50 "
            + PREFIX_LESSON_PRICE + "100 "
            + PREFIX_LESSON_TIMING + "Mon 1000-1200 ";

    public static final String MESSAGE_SUCCESS = "New lesson added: %1$s";
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists in TutorAid";

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
        return new CommandResult(String.format(MESSAGE_SUCCESS, lesson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLessonCommand // instanceof handles nulls
                && lesson.equals(((AddLessonCommand) other).lesson));
    }
}

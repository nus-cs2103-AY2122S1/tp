package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_ALERT;
import static seedu.tuitione.commons.core.Messages.HEADER_SUCCESS;
import static seedu.tuitione.commons.core.Messages.MESSAGE_DUPLICATE_LESSON_FOUND;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.tuitione.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;

/**
 * Adds a lesson to TuitiONE
 */
public class AddLessonCommand extends Command {

    public static final String COMMAND_WORD = "add-l";

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD + "\nAdds a lesson to TuitiONE.\n\n"
            + "Parameters: "
            + PREFIX_SUBJECT + "SUBJECT "
            + PREFIX_GRADE + "GRADE "
            + PREFIX_DAY + "DAY "
            + PREFIX_TIME + "START_TIME "
            + PREFIX_COST + "COST\n"
            + "Example: " + "add-l s/Science g/P5 d/Wed t/1200 c/10.50";

    public static final String MESSAGE_SUCCESS = HEADER_SUCCESS + "New lesson created:\n%1$s";
    public static final String MESSAGE_DUPLICATE_LESSON = HEADER_ALERT + MESSAGE_DUPLICATE_LESSON_FOUND;

    private final Lesson toAdd;

    /**
     * Creates an AddLessonCommand to add the specified {@code Lesson}
     */
    public AddLessonCommand(Lesson lesson) {
        requireNonNull(lesson);
        toAdd = lesson;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasLesson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        model.addLesson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLessonCommand // instanceof handles nulls
                && toAdd.equals(((AddLessonCommand) other).toAdd));
    }
}

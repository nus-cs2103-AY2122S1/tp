package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;

/**
 * Deletes a lesson identified using its displayed index from the TutorAid.
 */
public class DeleteLessonCommand extends DeleteCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Deletes a lesson from TutorAid."
                    + "\nParameters:"
                    + "\nINDEX (must be a positive integer)"
                    + "\nExample:"
                    + "\n%1$s %2$s 1",
            COMMAND_WORD, COMMAND_FLAG);

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Successfully deleted %s.";

    private final Index targetIndex;

    public DeleteLessonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteLesson(lessonToDelete);
        model.deleteLessonFromStudents(lessonToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete.toNameString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLessonCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteLessonCommand) other).targetIndex)); // state check
    }
}

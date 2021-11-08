package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;

/**
 * List all details for a lesson in TutorAid to the user.
 */
public class ViewLessonCommand extends ViewCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_USAGE = String.format("%1$s %2$s: Shows the lesson identified by the index "
                    + "number as shown in the Lesson Panel."
                    + "\nParameters:"
                    + "\nINDEX (must be a positive integer)"
                    + "\nExample:"
                    + "\n%1$s %2$s 1",
            COMMAND_WORD, COMMAND_FLAG);

    public static final String MESSAGE_VIEW_LESSON_SUCCESS = "Showing %s and the students in this class.";

    private final Index targetIndex;

    public ViewLessonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Lesson> lastShownList = model.getFilteredLessonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToView = lastShownList.get(targetIndex.getZeroBased());
        model.viewLesson(lessonToView);
        return new CommandResult(String.format(MESSAGE_VIEW_LESSON_SUCCESS, lessonToView.toNameString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewLessonCommand // instanceof handles nulls
                && targetIndex.equals(((ViewLessonCommand) other).targetIndex)); // state check
    }
}

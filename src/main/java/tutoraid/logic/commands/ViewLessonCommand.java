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

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_FLAG
            + ": Shows the lesson identified by the index number used in the displayed lesson list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_FLAG + " 1";

    public static final String MESSAGE_VIEW_LESSON_SUCCESS = "Viewing requested lesson";

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
        return new CommandResult(MESSAGE_VIEW_LESSON_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewLessonCommand // instanceof handles nulls
                && targetIndex.equals(((ViewLessonCommand) other).targetIndex)); // state check
    }
}

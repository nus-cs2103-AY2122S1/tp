package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_LESSONS;
import static seedu.tuitione.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.tuitione.commons.core.Messages;
import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;

/**
 * Deletes a lesson identified using it's displayed index from the lesson panel in tuitione book.
 */
public class DeleteLessonCommand extends Command {

    public static final String COMMAND_WORD = "delete-l";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the lesson identified by the index number used in the displayed lesson list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted Lesson: %1$s";

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
        lessonToDelete.removeAll();
        model.deleteLesson(lessonToDelete);
        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLessonCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteLessonCommand) other).targetIndex)); // state check
    }
}

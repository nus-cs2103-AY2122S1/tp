package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_SUCCESS;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;

import java.util.List;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

/**
 * Deletes a lesson identified using it's displayed index from the lesson panel in tuitione book.
 */
public class DeleteLessonCommand extends Command {

    public static final String COMMAND_WORD = "delete-l";

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD
            + "\nDeletes the lesson identified by the index number used in the displayed lesson list.\n\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LESSON_SUCCESS = HEADER_SUCCESS + "Deleted Lesson:\n%1$s";

    private final Index targetIndex;

    public DeleteLessonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Lesson> lastShownList = model.getFilteredLessonList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }
        Lesson lessonToDelete = lastShownList.get(targetIndex.getZeroBased());

        List<Student> studentsToUnenroll = lessonToDelete.getStudents();
        while (!studentsToUnenroll.isEmpty()) {
            Student s = studentsToUnenroll.get(0);
            s.unenrollFromLesson(lessonToDelete);
            model.setStudent(s, s); // self update in model
        }
        model.deleteLesson(lessonToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, lessonToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLessonCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteLessonCommand) other).targetIndex)); // state check
    }
}

package seedu.tuitione.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tuitione.commons.core.Messages.HEADER_SUCCESS;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;

import java.util.List;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.logic.commands.exceptions.CommandException;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.student.Student;

/**
 * Deletes a student identified using it's displayed index from the tuitione book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "Command: "
            + COMMAND_WORD
            + "\nDeletes the student identified by the index number used in the displayed student list.\n\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = HEADER_SUCCESS + "Deleted Student:\n%1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredStudentList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());

        List<Lesson> lessonsToUnenroll = studentToDelete.getLessons();
        while (!lessonsToUnenroll.isEmpty()) {
            Lesson l = lessonsToUnenroll.get(0);
            l.unenrollStudent(studentToDelete);
            model.setLesson(l, l); // self update in model
        }
        model.deleteStudent(studentToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}

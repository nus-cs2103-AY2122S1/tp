package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Student;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class UnenrollCommand extends Command {

    public static final String COMMAND_WORD = "unenroll";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unenrolls the student identified by the index number used in the displayed person list"
            + " from the specified lesson.\n"
            + "Parameters: INDEX (must be a positive integer), LESSONCODE (must represent a valid lesson)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNENROLL_STUDENT_SUCCESS = "Unenrolled Student: %1$s from Lesson: %2$s";

    private final Index targetIndex;

    private final String lessonCode;

    public UnenrollCommand(Index targetIndex, String lessonCode) {
        this.targetIndex = targetIndex;
        this.lessonCode = lessonCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();
        Lesson lesson = model.searchLessons(lessonCode);

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (lesson == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_CODE);
        }

        Student studentToUnenroll = lastShownList.get(targetIndex.getZeroBased());
        lesson.removeStudent(studentToUnenroll);
        return new CommandResult(String.format(MESSAGE_UNENROLL_STUDENT_SUCCESS, studentToUnenroll, lesson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnenrollCommand // instanceof handles nulls
                && targetIndex.equals(((UnenrollCommand) other).targetIndex))
                && lessonCode.equals(((UnenrollCommand) other).lessonCode); // state check
    }

}

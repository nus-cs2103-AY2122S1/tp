package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Marks the attendance (present/absent) of an existing student in the student list.
 */
public class MarkStudentAttCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attendance of the student identified "
            + "by the index number used in the displayed student list.\n"
            + "Marking a student who is absent will change his attendance to present.\n"
            + "Marking a student who is present will change his attendance to absent.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + " [" + PREFIX_WEEK + "WEEK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_WEEK + "1";

    public static final String MESSAGE_MARK_STUDENT_SUCCESS = "Student: %1$s is marked as %2$s for week %3$s!";

    private final Index index;
    private final int week;

    /**
     * @param index of the student in the filtered student list to mark attendance
     */
    public MarkStudentAttCommand(Index index, int week) {
        requireNonNull(index);
        requireNonNull(week);

        this.index = index;
        this.week = week;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToUpdate = lastShownList.get(index.getZeroBased());

        model.markStudentAttendance(studentToUpdate, week);
        String type = model.getStudentAttendance(studentToUpdate, week);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_MARK_STUDENT_SUCCESS,
                studentToUpdate.getName(), type, week + 1));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkStudentAttCommand)) {
            return false;
        }

        // state check
        MarkStudentAttCommand e = (MarkStudentAttCommand) other;
        return index.equals(e.index);
    }
}

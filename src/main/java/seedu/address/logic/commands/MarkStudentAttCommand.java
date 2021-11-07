package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Student;

/**
 * Marks the attendance (present/absent) of an existing student in the student list.
 */
public class MarkStudentAttCommand extends Command {

    public static final String COMMAND_WORD = "marka";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attendance of the student(s) identified "
            + "by the index number used in the displayed student list.\n"
            + "Marking a student who is absent will change his attendance to present.\n"
            + "Marking a student who is present will change his attendance to absent.\n"
            + "Parameters: INDEX [MORE_INDEXES] (must be positive integers)"
            + " [" + PREFIX_WEEK + "WEEK]\n"
            + "Example: " + COMMAND_WORD + " 1 2 3 "
            + PREFIX_WEEK + "3";

    public static final String MESSAGE_MARK_STUDENT_SUCCESS = "Student: %1$s is marked as %2$s for week %3$s!\n";

    private final List<Index> targetIndexList;
    private final int zeroIndexWeek;

    /**
     * Marks student(s) attendance (present/absent)
     * @param targetIndexList The list of indexes of students' attendances to be marked.
     */
    public MarkStudentAttCommand(List<Index> targetIndexList, int zeroIndexWeek) {
        requireNonNull(targetIndexList);
        requireNonNull(zeroIndexWeek);

        this.targetIndexList = targetIndexList;
        this.zeroIndexWeek = zeroIndexWeek;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        StringBuilder result = new StringBuilder();

        for (Index targetIndex : targetIndexList) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
        }

        for (Index targetIndex : targetIndexList) {
            Student studentToUpdate = lastShownList.get(targetIndex.getZeroBased());

            model.markStudentAttendance(studentToUpdate, zeroIndexWeek);
            String type = model.getStudentAttendance(studentToUpdate, zeroIndexWeek);
            result.append(String.format(MESSAGE_MARK_STUDENT_SUCCESS,
                    studentToUpdate.getName(), type, zeroIndexWeek + Attendance.FIRST_WEEK_OF_SEM));
        }
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(result.toString());
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
        return targetIndexList.equals(e.targetIndexList);
    }
}

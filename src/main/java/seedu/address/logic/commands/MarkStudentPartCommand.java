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
 * Marks the participation (present/absent) of an existing student in the student list.
 */
public class MarkStudentPartCommand extends Command {

    public static final String COMMAND_WORD = "markp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the participation of the student identified "
            + "by the index number used in the displayed student list.\n"
            + "Marking a student will change his participation status to 1.\n"
            + "Marking a student again will change his participation status to 0.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + " [" + PREFIX_WEEK + "WEEK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_WEEK + "1";

    public static final String MESSAGE_MARK_STUDENT_SUCCESS = "Student: %1$s is marked as %2$s for week %3$s!";

    private final Index index;
    private final int week;

    /**
     * @param index of the student in the filtered student list to mark participation
     */
    public MarkStudentPartCommand(Index index, int week) {
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

        model.markStudentParticipation(studentToUpdate, week);
        String type = model.getStudentParticipation(studentToUpdate, week);
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
        if (!(other instanceof MarkStudentPartCommand)) {
            return false;
        }

        // state check
        MarkStudentPartCommand e = (MarkStudentPartCommand) other;
        return index.equals(e.index);
    }
}

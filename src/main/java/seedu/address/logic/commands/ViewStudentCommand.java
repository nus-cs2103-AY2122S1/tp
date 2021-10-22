package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.IsStudentPredicate;
import seedu.address.model.student.Student;

public class ViewStudentCommand extends Command {

    public static final String COMMAND_WORD = "viewstudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds and displays details of the student who matches the specified group name (case-sensitive) \n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example:" + COMMAND_WORD + " 2";

    private final Index index;
    private final String MESSAGE_VIEW_STUDENT_SUCCESS = "Viewing details of %1$s";

    public ViewStudentCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToView = lastShownList.get(this.index.getZeroBased());
        IsStudentPredicate predicate = new IsStudentPredicate(studentToView);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(String.format(MESSAGE_VIEW_STUDENT_SUCCESS, studentToView.getName()),
            false, false, true);
    }
}

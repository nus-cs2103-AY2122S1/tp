package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.ContainsStudentNamePredicate;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

public class ViewStudentCommand extends Command {

    public static final String COMMAND_WORD = "viewstudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds and displays details of the student identified by the specified name (case-sensitive) \n"
        + "Parameters: NAME (case-sensitive)\n"
        + "Example: " + COMMAND_WORD + " David Li";

    private static final String MESSAGE_VIEW_STUDENT_SUCCESS = "Viewing details of %1$s";

    private final Name name;

    public ViewStudentCommand(Name name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ContainsStudentNamePredicate predicate = new ContainsStudentNamePredicate(name);
        model.updateFilteredStudentList(predicate);

        if (model.getFilteredStudentList().size() == 0) {
            model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
            return new CommandResult(String.format(Messages.MESSAGE_STUDENT_NOT_FOUND, name));
        }

        Student studentToView = model.getFilteredStudentList().get(0);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_VIEW_STUDENT_SUCCESS, name),
            false, false, studentToView);
    }
}

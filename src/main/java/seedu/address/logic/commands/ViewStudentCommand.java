package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.ContainsStudentNamePredicate;
import seedu.address.model.student.Name;

public class ViewStudentCommand extends Command {

    public static final String COMMAND_WORD = "viewstudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds and displays details of the student identified by the specified name (case-sensitive) \n"
        + "Parameters: NAME (case-sensitive)\n"
        + "Example:" + COMMAND_WORD + " 2";

    private final Name name;
    private final String MESSAGE_VIEW_STUDENT_SUCCESS = "Viewing details of %1$s";

    public ViewStudentCommand(Name name) {
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ContainsStudentNamePredicate predicate = new ContainsStudentNamePredicate(name);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(String.format(MESSAGE_VIEW_STUDENT_SUCCESS, name),
            false, false, true);
    }
}

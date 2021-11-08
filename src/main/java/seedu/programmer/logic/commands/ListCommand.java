package seedu.programmer.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.programmer.model.Model;

/**
 * Lists all students in ProgrammerError to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students";
    public static final String MESSAGE_NO_STUDENTS = "No students to list!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.hasNoStudents()) {
            return new CommandResult(MESSAGE_NO_STUDENTS);
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

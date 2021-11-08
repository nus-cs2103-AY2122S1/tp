package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.student.Student;

/**
 * Clears all students in the currently displayed student list.
 */
public class ClearStudentCommand extends Command {

    public static final String COMMAND_WORD = "clearStudent";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted %d students";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student in existing view.\n"
            + "Example: " + COMMAND_WORD;

    public static final String NOTHING_TO_DELETE = "There are no students to clear!";

    private final List<Student> studentsToEliminate = new ArrayList<>();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (lastShownList.size() < 1) {
            throw new CommandException(NOTHING_TO_DELETE);
        }

        for (Student studentInList : lastShownList) {
            studentsToEliminate.add(studentInList);
        }

        model.massDeleteStudent(studentsToEliminate);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentsToEliminate.size()));
    }
}

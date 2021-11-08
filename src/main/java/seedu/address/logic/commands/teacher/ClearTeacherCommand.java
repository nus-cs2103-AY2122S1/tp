package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.teacher.Teacher;

/**
 * Clears all teachers in the currently displayed teacher list.
 */
public class ClearTeacherCommand extends Command {

    public static final String COMMAND_WORD = "clearTeacher";

    public static final String MESSAGE_DELETE_TEACHER_SUCCESS = "Deleted %d Teachers";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Teachers in existing view.\n"
            + "Example: " + COMMAND_WORD;

    public static final String NOTHING_TO_DELETE = "There are no teachers to clear!";

    private final List<Teacher> teachersToEliminate = new ArrayList<>();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Teacher> lastShownList = model.getFilteredTeacherList();

        if (lastShownList.size() < 1) {
            throw new CommandException(NOTHING_TO_DELETE);
        }

        for (Teacher teacherInList : lastShownList) {
            teachersToEliminate.add(teacherInList);
        }
        model.massDeleteTeacher(teachersToEliminate);
        return new CommandResult(String.format(MESSAGE_DELETE_TEACHER_SUCCESS, teachersToEliminate.size()));
    }
}

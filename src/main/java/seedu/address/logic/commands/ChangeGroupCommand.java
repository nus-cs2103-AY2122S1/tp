package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

public class ChangeGroupCommand extends Command {

    public static final String COMMAND_WORD = "changegroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the group which the student belongs to"
            + "by the students name to the new group name given.\n"
            + "Parameters: " + PREFIX_NAME + "NAME " + PREFIX_GROUP_NAME + "GROUP NAME "
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "Brian Cheong "
            + PREFIX_GROUP_NAME + "CS1231S";


    public static final String MESSAGE_CHANGE_GROUP_SUCCESS = "Changed Student: %1$s to Group: %2$s";
    public static final String MESSAGE_CHANGE_TO_SAME_GROUP = "The student is already in this group.";
    public static final String MESSAGE_STUDENT_NONEXISTENT =
            "The student does not exist. Please choose a student that exists.";
    public static final String MESSAGE_GROUP_NONEXISTENT =
            "The group indicated does not exist. Please create it first.";

    private final Name studentName;
    private final GroupName newGroupName;

    /**
     * @param studentName     name of student to have its group changed
     * @param newGroupName group name of the group to change the student to
     */
    public ChangeGroupCommand(Name studentName, GroupName newGroupName) {
        requireNonNull(studentName);
        requireNonNull(newGroupName);

        this.studentName = studentName;
        this.newGroupName = newGroupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Student student = model.getStudentByName(studentName);
        if (student == null) {
            throw new CommandException(MESSAGE_STUDENT_NONEXISTENT);
        }
        Group group = model.getGroupByGroupName(newGroupName);
        if (group == null) {
            throw new CommandException(MESSAGE_GROUP_NONEXISTENT);
        }

        // check that the new group is not the same as the students current group
        if (student.getGroupName().equals(group.getGroupName())) {
            throw new CommandException(MESSAGE_CHANGE_TO_SAME_GROUP);
        }

        model.changeStudentGroup(student, group);
        model.updateGroupStudent(group, student);
        return new CommandResult(String.format(MESSAGE_CHANGE_GROUP_SUCCESS, studentName, newGroupName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChangeGroupCommand // instanceof handles nulls
                && studentName.equals(((ChangeGroupCommand) other).studentName))
                && newGroupName.equals(((ChangeGroupCommand) other).newGroupName);
    }
}

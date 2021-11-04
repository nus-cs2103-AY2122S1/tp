package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Student;

/**
 * Adds an existing student in the student list to an existing group in the group list.
 */
public class AddMemberCommand extends Command {

    public static final String COMMAND_WORD = "addMember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an existing student to an existing group "
            + "by the index number used in the displayed group list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_GROUP + "GROUP\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_GROUP + "W14-4";

    public static final String MESSAGE_ADD_STUDENT_SUCCESS = "Student: %1$s has been added to group %2$s!";

    private final Index index;
    private final GroupName group;

    /**
     * @param index of the student in the filtered student list to add to the group
     */
    public AddMemberCommand(Index index, GroupName group) {
        requireAllNonNull(index, group);

        this.index = index;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Group> lastShownGroupList = model.getFilteredGroupList();

        if (index.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToAdd = lastShownStudentList.get(index.getZeroBased());

        if (!(studentToAdd.getGroupName().isNull())) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_MEMBER_INDEX);
        }

        Group groupToUpdate = lastShownGroupList.stream()
                                                .filter(g -> group.equals(g.getName()))
                                                .findAny()
                                                .orElse(null);
        if (groupToUpdate == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_NAME);
        }
        Student existingStudent = groupToUpdate.getMembers().studentList.stream()
                                                                        .filter(s -> s.isSameStudent(studentToAdd))
                                                                        .findAny()
                                                                        .orElse(null);
        if (existingStudent != null) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_GROUP_MEMBER_INDEX);
        }

        model.addMember(studentToAdd, groupToUpdate);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult(String.format(MESSAGE_ADD_STUDENT_SUCCESS,
                studentToAdd.getName().fullName, groupToUpdate.getName().name));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMemberCommand)) {
            return false;
        }

        // state check
        AddMemberCommand e = (AddMemberCommand) other;
        return index.equals(e.index);
    }
}

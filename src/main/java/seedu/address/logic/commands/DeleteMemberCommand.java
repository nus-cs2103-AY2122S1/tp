package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.student.Student;

/**
 * Adds an existing student in the student list to an existing group in the group list.
 */
public class DeleteMemberCommand extends Command {

    public static final String COMMAND_WORD = "deleteMember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a member from a group "
            + "by the index number used in the displayed group list and member list.\n"
            + "Parameters: GROUP INDEX & MEMBER INDEX (both must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "1 ";

    public static final String MESSAGE_DELETE_MEMBER_SUCCESS = "Member: %1$s has been removed from group %2$s!";

    private final Index groupIndex;
    private final Index memberIndex;

    /**
     * @param groupIndex of the group in the filtered group list to add to the group
     */
    public DeleteMemberCommand(Index groupIndex, Index memberIndex) {
        requireAllNonNull(groupIndex, memberIndex);

        this.groupIndex = groupIndex;
        this.memberIndex = memberIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownGroupList = model.getFilteredGroupList();

        if (groupIndex.getZeroBased() >= lastShownGroupList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
        }

        Group groupToUpdate = lastShownGroupList.get(groupIndex.getZeroBased());

        List<Student> lastShownMemberList = groupToUpdate.getMembersList();

        if (memberIndex.getZeroBased() >= lastShownMemberList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_MEMBER_REMOVAL_INDEX);
        }

        Student memberToRemove = lastShownMemberList.get(memberIndex.getZeroBased());

        model.deleteMember(memberToRemove, groupToUpdate);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult(String.format(MESSAGE_DELETE_MEMBER_SUCCESS,
                memberToRemove.getName().fullName, groupToUpdate.getName().name));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteMemberCommand)) {
            return false;
        }

        // state check
        DeleteMemberCommand e = (DeleteMemberCommand) other;
        return groupIndex.equals(e.groupIndex)
                && memberIndex.equals(e.memberIndex);
    }
}

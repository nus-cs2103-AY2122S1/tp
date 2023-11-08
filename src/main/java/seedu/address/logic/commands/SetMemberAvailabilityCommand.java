package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Availability;
import seedu.address.model.member.Member;

/**
 * Sets the availability of an existing member in the member list.
 */
public class SetMemberAvailabilityCommand extends Command {

    public static final String COMMAND_WORD = "setm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the availability of the members identified "
            + "by the indices used in the currently displayed member list. "
            + "Existing availability will be overwritten by the input.\n"
            + "Parameters: INDEX [MORE_INDICES]... (must be positive integers) "
            + "d/DAY(S) (where 1 represents Monday, 2 represents Tuesday ... and 7 represents Sunday\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/1 2 3 7";

    public static final String MESSAGE_SET_AVAILABILITY_SUCCESS = "Successfully set availability of %s to %s";

    private final List<Index> indices;
    private final Availability availability;

    /**
     * @param indices of the member in the filtered member list to edit the availability
     * @param availability of the member to be updated to
     */
    public SetMemberAvailabilityCommand(List<Index> indices, Availability availability) {
        requireAllNonNull(indices, availability);

        this.indices = indices;
        this.availability = availability;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Member> lastShownList = model.getFilteredMemberList();
        if (lastShownList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_LIST, Messages.MESSAGE_MEMBER));
        }

        for (Index i : indices) {
            if (i.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDICES);
            }
        }

        List<Member> membersToEdit = new ArrayList<>();

        for (Index i : indices) {
            Member memberToEdit = lastShownList.get(i.getZeroBased());
            Member editedMember = new Member(
                    memberToEdit.getName(), memberToEdit.getPhone(), availability, memberToEdit.getTodayAttendance(),
                    memberToEdit.getTotalAttendance(), memberToEdit.getTags());

            model.setMember(memberToEdit, editedMember);
            membersToEdit.add(lastShownList.get(i.getZeroBased()));
            model.removeMemberFromAllocations(memberToEdit);
        }
        model.updateFilteredMemberList(Model.PREDICATE_SHOW_ALL_MEMBERS);

        String names = membersToEdit.stream().map(member -> member.getName().toString())
                .collect(Collectors.joining(", "));

        return new CommandResult(String.format(MESSAGE_SET_AVAILABILITY_SUCCESS, names, availability),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SetMemberAvailabilityCommand)) {
            return false;
        }

        // state check
        SetMemberAvailabilityCommand e = (SetMemberAvailabilityCommand) other;
        return indices.equals(e.indices)
                && availability.equals(e.availability);
    }
}

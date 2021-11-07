package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_LIST;
import static seedu.address.commons.core.Messages.MESSAGE_FACILITY;
import static seedu.address.commons.core.Messages.MESSAGE_MEMBER;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facility.AllocationMap;
import seedu.address.model.facility.Facility;
import seedu.address.model.member.Member;

/**
 * Allocates a member available on a particular day to a Facility.
 */
public class AllocateMemberCommand extends Command {
    public static final String COMMAND_WORD = "allocate";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Allocates a member to facility.\n"
            + "Parameters: " + "MEMBER_INDEX FACILITY_INDEX DAY\n"
            + "DAY must be an integer from 1 to 7\n"
            + "where 1 represents Monday, 2 represents Tuesday ... and 7 represents Sunday\n"
            + "Example: " + COMMAND_WORD + " 1 1 1";
    public static final String MESSAGE_SUCCESS = "Allocated %s to %s for %s";

    private final Index memberIndex;
    private final Index facilityIndex;
    private final DayOfWeek day;

    /**
     * @param memberIndex Index of the member to be allocated to a facility.
     * @param facilityIndex Index of the facility to allocate the member to.
     * @param day to allocate the member to the facility.
     */
    public AllocateMemberCommand(Index memberIndex, Index facilityIndex, DayOfWeek day) {
        requireAllNonNull(memberIndex, facilityIndex, day);
        this.memberIndex = memberIndex;
        this.facilityIndex = facilityIndex;
        this.day = day;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Member> lastShownMemberList = model.getFilteredMemberList();
        List<Facility> lastShownFacilityList = model.getFilteredFacilityList();
        if (lastShownMemberList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_LIST, MESSAGE_MEMBER));
        }
        if (lastShownFacilityList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_LIST, MESSAGE_FACILITY));
        }
        if (memberIndex.getOneBased() > lastShownMemberList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }
        if (facilityIndex.getOneBased() > lastShownFacilityList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FACILITY_DISPLAYED_INDEX);
        }
        Member toBeAllocated = lastShownMemberList.get(memberIndex.getZeroBased());
        Facility toAllocateTo = lastShownFacilityList.get(facilityIndex.getZeroBased());

        handleAllocation(toBeAllocated, toAllocateTo, model);

        String dayName = day.getDisplayName(TextStyle.FULL, Locale.getDefault());
        return new CommandResult(String.format(MESSAGE_SUCCESS, toBeAllocated.getName(), toAllocateTo.getName(), dayName));
    }

    /**
     * Handles the allocation of the member to the facility
     * @param toBeAllocated Member to be allocated
     * @param toAllocateTo Facility to allocate member to
     * @throws CommandException if allocation of the member to the facility is not feasible
     */
    private void handleAllocation(Member toBeAllocated, Facility toAllocateTo, Model model) throws CommandException {
        if (toAllocateTo.isMemberAllocatedOnDay(toBeAllocated, day)) {
            throw new CommandException(Messages.MESSAGE_MEMBER_ALREADY_ALLOCATED);
        } else if (toAllocateTo.isMaxCapacityOnDay(day)) {
            throw new CommandException(Messages.MESSAGE_FACILITY_AT_MAX_CAPACITY);
        } else if (!toBeAllocated.isAvailableOnDay(day.getValue())) {
            throw new CommandException(Messages.MESSAGE_MEMBER_NOT_AVAILABLE);
        }
        AllocationMap updatedAllocationMap = toAllocateTo.getAllocationMap();
        updatedAllocationMap.addMemberOnDay(toBeAllocated, day);
        Facility afterAllocated = new Facility(
                toAllocateTo.getName(), toAllocateTo.getLocation(), toAllocateTo.getTime(), toAllocateTo.getCapacity(),
                updatedAllocationMap);
        model.setFacility(toAllocateTo, afterAllocated);
        model.updateFilteredFacilityList(Model.PREDICATE_SHOW_ALL_FACILITIES);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AllocateMemberCommand)) {
            return false;
        }

        AllocateMemberCommand e = (AllocateMemberCommand) other;
        return memberIndex.equals(e.memberIndex) && facilityIndex.equals(e.facilityIndex) && day.equals(e.day);
    }
}

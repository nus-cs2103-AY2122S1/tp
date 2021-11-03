package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
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
import seedu.address.model.person.Person;

/**
 * Deallocates a member available on a particular day from a Facility.
 */
public class DeallocateMemberCommand extends Command {
    public static final String COMMAND_WORD = "deallocate";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deallocates a member from a facility.\n"
            + "Parameters: " + "MEMBER_INDEX FACILITY_INDEX DAY\n"
            + "DAY must be an integer from 1 to 7\n"
            + "where 1 represents Monday, 2 represents Tuesday ... and 7 represents Sunday\n"
            + "Example: " + COMMAND_WORD + " 1 1 1";
    public static final String MESSAGE_SUCCESS = "Deallocated %s from %s for %s";

    private final Index memberIndex;
    private final Index facilityIndex;
    private final DayOfWeek day;

    /**
     * @param memberIndex of the member to be allocated to a facility.
     * @param facilityIndex of the facility to allocate the member to.
     */
    public DeallocateMemberCommand(Index memberIndex, Index facilityIndex, DayOfWeek day) {
        requireAllNonNull(memberIndex, facilityIndex);
        this.memberIndex = memberIndex;
        this.facilityIndex = facilityIndex;
        this.day = day;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Facility> lastShownFacilityList = model.getFilteredFacilityList();
        if (memberIndex.getOneBased() > lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEMBER_DISPLAYED_INDEX);
        }
        if (facilityIndex.getOneBased() > lastShownFacilityList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FACILITY_DISPLAYED_INDEX);
        }
        Person toBeDeallocated = lastShownPersonList.get(memberIndex.getZeroBased());
        Facility toDeallocate = lastShownFacilityList.get(facilityIndex.getZeroBased());

        if (!toDeallocate.isPersonAllocatedOnDay(toBeDeallocated, day)) {
            throw new CommandException(Messages.MESSAGE_MEMBER_NOT_ALLOCATED);
        } else {
            AllocationMap updatedAllocationMap = toDeallocate.getAllocationMap();
            updatedAllocationMap.removePersonOnDay(toBeDeallocated, day);
            Facility afterDeallocated = new Facility(
                    toDeallocate.getName(), toDeallocate.getLocation(), toDeallocate.getTime(),
                    toDeallocate.getCapacity(), updatedAllocationMap);
            model.setFacility(toDeallocate, afterDeallocated);
            model.updateFilteredFacilityList(Model.PREDICATE_SHOW_ALL_FACILITIES);
        }

        String dayName = day.getDisplayName(TextStyle.FULL, Locale.getDefault());
        return new CommandResult(String.format(MESSAGE_SUCCESS, toBeDeallocated.getName(),
                toDeallocate.getName(), dayName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeallocateMemberCommand)) {
            return false;
        }

        DeallocateMemberCommand e = (DeallocateMemberCommand) other;
        return memberIndex.equals(e.memberIndex) && facilityIndex.equals(e.facilityIndex) && day.equals(e.day);
    }
}

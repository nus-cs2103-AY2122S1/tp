package seedu.address.model.facility;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import seedu.address.model.member.Member;

/**
 * Represents the mapping of member allocations to the day of the week in a Facility.
 */
public class AllocationMap {
    private Map<DayOfWeek, List<Member>> membersAllocatedMap;

    /**
     * Creates an AllocationMap object with a specified allocation map.
     *
     * @param membersAllocatedMap A valid allocation map.
     */
    public AllocationMap(Map<DayOfWeek, List<Member>> membersAllocatedMap) {
        this.membersAllocatedMap = membersAllocatedMap;
    }

    public Map<DayOfWeek, List<Member>> getMembersAllocatedMap() {
        return membersAllocatedMap;
    }

    /**
     * Gets the current capacity on the given day.
     *
     * @param day The day to get the current capacity.
     * @return The current capacity on the given day.
     */
    public int getCapacityOnDay(DayOfWeek day) {
        return membersAllocatedMap.get(day).size();
    }

    public void clearAllocationOnDay(DayOfWeek day) {
        membersAllocatedMap.get(day).clear();
    }

    /**
     * Checks whether a given member is allocated on the given day.
     *
     * @param member Member to check.
     * @param day Day to check.
     * @return true if and only if the member is allocated on the given day.
     */
    public boolean isMemberAllocatedOnDay(Member member, DayOfWeek day) {
        return membersAllocatedMap.get(day).contains(member);
    }

    /**
     * Adds a member to the allocation on the given day.
     *
     * @param member Member to add to allocation on the given day.
     * @param day The day to add the member.
     */
    public void addMemberOnDay(Member member, DayOfWeek day) {
        membersAllocatedMap.get(day).add(member);
    }

    /**
     * Removes a member from the allocation on the given day.
     *
     * @param member Member to be removed from allocations on the given day.
     * @param day The day to remove the member.
     */
    public void removeMemberOnDay(Member member, DayOfWeek day) {
        membersAllocatedMap.get(day).remove(member);
    }

    /**
     * Removes a member from the allocation for all days.
     *
     * @param member Member to be removed from allocations on all days.
     */
    public void removeMemberOnAllDays(Member member) {
        for (DayOfWeek day : DayOfWeek.values()) {
            membersAllocatedMap.get(day).remove(member);
        }
    }

    /**
     * Returns the string representation of the given list of members.
     *
     * @param memberList The list of members to get the string representation of.
     * @return String representation of all members in the list of persons.
     */
    public String getMembersAsString(List<Member> memberList) {
        return memberList.stream().map(member -> member.getName().toString()).collect(Collectors.joining(", "));
    }

    /**
     * Gets the list of members allocated on the given day.
     *
     * @param day The day to get the list of members allocated.
     * @return The list of members allocated on the given day.
     */
    public List<Member> getMembersAllocatedOnDay(DayOfWeek day) {
        return membersAllocatedMap.get(day);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj == this)
                || (obj instanceof AllocationMap
                && membersAllocatedMap.equals(((AllocationMap) obj).membersAllocatedMap));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (DayOfWeek day : membersAllocatedMap.keySet()) {
            builder.append(day.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
            builder.append(": ");
            builder.append(getMembersAsString(membersAllocatedMap.get(day)));
            builder.append("\n");
        }

        return builder.toString();
    }
}

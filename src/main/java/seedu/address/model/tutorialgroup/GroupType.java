package seedu.address.model.tutorialgroup;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a TutorialGroup's type in the ClassMATE.
 */
public class GroupType implements Comparable<GroupType> {

    public static final String MESSAGE_CONSTRAINTS = "GroupType can only either be OP1 or OP2";
    public static final String VALIDATION_REGEX = "[O|o][P|p][1/2]";

    public final String value;
    /**
     * Constructs an {@code GroupType}.
     *
     * @param groupType A valid groupType.
     */
    public GroupType (String groupType) {
        requireNonNull(groupType);
        checkArgument(isValidGroupType(groupType), MESSAGE_CONSTRAINTS);
        value = groupType;
    }

    /**
     * Returns true if a given string is a valid groupType.
     */
    public static boolean isValidGroupType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Gets the group type in terms of its number, 1 or 2.
     *
     * @return The group type in terms of its number.
     */
    public Integer parseGroupType() {
        assert value.length() == 3;
        return parseInt(value.substring(2));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupType // instanceof handles nulls
                && value.equals(((GroupType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(GroupType groupType) {
        return parseGroupType().compareTo(groupType.parseGroupType());
    }

}

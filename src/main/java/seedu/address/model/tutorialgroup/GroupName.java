package seedu.address.model.tutorialgroup;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class GroupName implements Comparable<GroupName> {
    public static final String MESSAGE_CONSTRAINTS = "GroupName must be a single digit, and it should not be blank";

    private static final String GROUPNAME_REGEX = "\\d";

    public final String value;

    /**
     * Constructs an {@code GroupName}.
     *
     * @param groupName A valid groupName.
     */
    public GroupName (String groupName) {
        requireNonNull(groupName);
        checkArgument(isValidGroupName(groupName), MESSAGE_CONSTRAINTS);
        value = groupName;
    }

    /**
     * Checks validity of input string.
     *
     * @param test Test string.
     * @return Validity of input GroupName string.
     */
    public static boolean isValidGroupName(String test) {
        return test.matches(GROUPNAME_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupName // instanceof handles nulls
                && value.equals(((GroupName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(GroupName groupName) {
        if (parseInt(this.value) > parseInt(groupName.value)) {
            return 1;
        } else if (parseInt(this.value) < parseInt(groupName.value)) {
            return -1;
        } else {
            return 0;
        }
    }

}


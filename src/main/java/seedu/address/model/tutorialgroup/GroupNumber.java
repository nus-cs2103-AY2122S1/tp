package seedu.address.model.tutorialgroup;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class GroupNumber implements Comparable<GroupNumber> {
    public static final String MESSAGE_CONSTRAINTS = "GroupNumber must be a single digit, and it should not be blank";

    private static final String GROUPNUMBER_REGEX = "\\d";

    public final String value;

    /**
     * Constructs an {@code GroupName}.
     *
     * @param groupName A valid groupName.
     */
    public GroupNumber(String groupName) {
        requireNonNull(groupName);
        checkArgument(isValidGroupNumber(groupName), MESSAGE_CONSTRAINTS);
        value = groupName;
    }

    /**
     * Checks validity of input string.
     *
     * @param test Test string.
     * @return Validity of input GroupNumber string.
     */
    public static boolean isValidGroupNumber(String test) {
        return test.matches(GROUPNUMBER_REGEX);
    }

    private Integer parseGroupName(String groupName) {
        return parseInt(groupName);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupNumber // instanceof handles nulls
                && value.equals(((GroupNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(GroupNumber groupNumber) {
        return parseGroupName(this.value).compareTo(parseGroupName(groupNumber.value));
    }

}


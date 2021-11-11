package seedu.address.model.tutorialgroup;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a TutorialGroup's number in the ClassMATE.
 */
public class GroupNumber implements Comparable<GroupNumber> {
    public static final String MESSAGE_CONSTRAINTS = "Group number must be a single digit between 1 and 4, "
            + "and it should not be blank";

    private static final String GROUP_NUMBER_REGEX = "[1-4]";

    public final String value;

    /**
     * Constructs an {@code GroupNumber}.
     *
     * @param groupNumber A valid groupNumber.
     */
    public GroupNumber(String groupNumber) {
        requireNonNull(groupNumber);
        checkArgument(isValidGroupNumber(groupNumber), MESSAGE_CONSTRAINTS);
        value = groupNumber;
    }

    /**
     * Checks validity of input string.
     *
     * @param test Test string.
     * @return Validity of input GroupNumber string.
     */
    public static boolean isValidGroupNumber(String test) {
        return test.matches(GROUP_NUMBER_REGEX);
    }

    /**
     * Transforms the group number from a string to its numerical value.
     *
     * @return Integer that represents the group.
     */
    public Integer parseGroupNumber() {
        return parseInt(value);
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
        return parseGroupNumber().compareTo(groupNumber.parseGroupNumber());
    }

}


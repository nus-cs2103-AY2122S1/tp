package seedu.address.model.tutorialgroup;

import static java.util.Objects.requireNonNull;

public class GroupName {
    public static final String MESSAGE_CONSTRAINTS = "GroupName can take any values, and it should not be blank";


    public final String value;

    /**
     * Constructs an {@code GroupName}.
     *
     * @param groupName A valid groupName.
     */
    public GroupName (String groupName) {
        requireNonNull(groupName);
        value = groupName;
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

}


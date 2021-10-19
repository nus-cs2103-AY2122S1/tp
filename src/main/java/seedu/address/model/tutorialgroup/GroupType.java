package seedu.address.model.tutorialgroup;

import static java.util.Objects.requireNonNull;

public enum GroupType {
    OP1("OP1"), OP2("OP2");

    public static final String MESSAGE_CONSTRAINTS = "GroupType can either be OP1 or OP2";

    public final String value;
    /**
     * Constructs an {@code GroupName}.
     *
     * @param groupType A valid groupName.
     */
    GroupType (String groupType) {
        requireNonNull(groupType);
        value = groupType;
    }
}

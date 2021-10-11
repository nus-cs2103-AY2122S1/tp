package seedu.address.model.student;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class ParentName extends Name {
    /**
     * Constructs a {@code ParentName}.
     *
     * @param name A valid name.
     */
    public ParentName(String name) {
        super(name);
        if (!name.equals("")) {
            checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        }
    }
}

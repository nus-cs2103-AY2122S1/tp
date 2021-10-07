package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class StudentName extends Name {
    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public StudentName(String name) {
        super(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
    }
}

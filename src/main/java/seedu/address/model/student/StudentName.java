package seedu.address.model.student;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class StudentName extends Name {
    /**
     * Constructs a {@code StudentName}.
     *
     * @param name A valid name.
     */
    public StudentName(String name) {
        super(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
    }
}

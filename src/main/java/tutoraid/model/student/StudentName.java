package tutoraid.model.student;

import tutoraid.commons.util.AppUtil;

public class StudentName extends Name {

    /**
     * Constructs a {@code StudentName}.
     *
     * @param name A valid name.
     */
    public StudentName(String name) {
        super(name);
        AppUtil.checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
    }
}

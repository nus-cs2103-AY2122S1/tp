package tutoraid.model.student;

import tutoraid.commons.util.AppUtil;

import static tutoraid.commons.util.AppUtil.checkArgument;

public class ParentName extends Name {
    /**
     * Constructs a {@code ParentName}.
     *
     * @param name A valid name.
     */
    public ParentName(String name) {
        super(name);
        if (!name.equals("")) {
            AppUtil.checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        }
    }
}

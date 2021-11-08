package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Abstract class which Event and Member inherits from.
 */
public abstract class Module {
    private final Name name;

    /**
     * Constructor for data to store name.
     *
     * @param name to be stored as field
     */
    public Module(Name name) {
        requireAllNonNull(name);
        this.name = name;
    }

    /**
     * Retrieve the name of the data.
     *
     * @return name of the data
     */
    public Name getName() {
        return name;
    }

    /**
     * Checks if two data are of the same type.
     *
     * @param module to be compared to
     * @return boolean representing if they are equal
     */
    public abstract boolean isSameType(Module module);
}

package seedu.address.model.data;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Abstract class which Event and Member inherits from.
 */
public abstract class Data {
    private final Name name;

    /**
     * Constructor for data to store name.
     *
     * @param name to be stored as field
     */
    public Data(Name name) {
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
     * @param data to be compared to
     * @return boolean representing if they are equal
     */
    public abstract boolean isSameType(Data data);
}

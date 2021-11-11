package seedu.address.commons.util;

/**
 * Represents a Class that is able to duplicate its own instances.
 */
public interface Copyable<T> {
    /**
     * Returns a duplicate copy of the instance of an object.
     *
     * @return The duplicate copy.
     */
    T copy();
}

package seedu.notor.model.util;

/**
 * An interface for objects for which duplicates should not be created
 *
 * @param <T> The type of the object
 */
public interface Unique<T> {
    /**
     * Used to define a possibly weaker version of equality where two objects are equivalent
     * even if they may not be equal
     *
     * @param other
     * @return
     */
    boolean isSame(T other);
}

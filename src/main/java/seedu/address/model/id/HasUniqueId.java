package seedu.address.model.id;

/**
 * An interface such that all classes having unique id should implement from it.
 */
public interface HasUniqueId {
    /**
     * Gets the unique id.
     *
     * @return The id.
     */
    UniqueId getId();
}

package seedu.address.model.id;

import java.util.Set;

import seedu.address.model.id.exceptions.IdNotFoundException;

public interface UniqueIdMapper<T extends HasUniqueId> {

    /**
     * Converts a Set of UniqueId into a set of objects represented by the UniqueId
     *
     * @param ids to convert
     * @return Set of objects represented by the UniqueId
     */
    Set<T> getFromUniqueIds(Set<UniqueId> ids) throws IdNotFoundException;
}

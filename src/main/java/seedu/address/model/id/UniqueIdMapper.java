package seedu.address.model.id;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.id.exceptions.IdNotFoundException;

public interface UniqueIdMapper<T extends HasUniqueId> {

    /**
     * Util method to get the set of objects from a given list represented by a set of ids.
     * A static implementation that can be reused by most implementations of this interface.
     */
    static <T extends HasUniqueId> Set<T> getFromUniqueIdsAndItemList(Set<UniqueId> ids,
            List<T> list) throws IdNotFoundException {
        Set<T> toReturn = new HashSet<>();
        for (UniqueId id : ids) {
            toReturn.add(list.stream().filter(generic -> id.equals(generic.getId()))
                    .findFirst()
                    .orElseThrow(() -> new IdNotFoundException(id)));
        }
        return toReturn;
    }

    /**
     * Converts a Set of UniqueId into a set of objects represented by the UniqueId
     *
     * @param ids to convert
     * @return Set of objects represented by the UniqueId
     */
    Set<T> getFromUniqueIds(Set<UniqueId> ids) throws IdNotFoundException;
}

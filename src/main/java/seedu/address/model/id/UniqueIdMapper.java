package seedu.address.model.id;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.id.exceptions.IdNotFoundException;

public interface UniqueIdMapper<T extends HasUniqueId> {

    static <T extends HasUniqueId> Set<T> getUniqueIdsFromList(Set<UniqueId> ids,
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

package seedu.address.model.person;

import java.util.Set;

/**
 * Represents a field in a staff.
 */
public interface Field {

    /**
     * Add input fields to field set.
     * @param fieldSet The field set to add to.
     * @param fields The fields to add.
     */
    static void addToFieldSet(Set<Field> fieldSet, Field... fields) {
        for (Field field: fields) {
            fieldSet.add(field);
        }
    }
}

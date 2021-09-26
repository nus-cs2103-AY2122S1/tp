package seedu.plannermd.model;

import javafx.collections.ObservableList;
import seedu.plannermd.model.person.Person;

/**
 * Unmodifiable view of a PlannerMD
 */
public interface ReadOnlyPlannerMd {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}

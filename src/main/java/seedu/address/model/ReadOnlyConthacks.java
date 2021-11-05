package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of ContHACKS.
 */
public interface ReadOnlyConthacks {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the moduleClass list.
     * This list will not contain any duplicate classes.
     */
    ObservableList<ModuleLesson> getModuleLessonList();

}

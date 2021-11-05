package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<ModuleLesson> PREDICATE_SHOW_ALL_LESSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' contHACKS file path.
     */
    Path getConthacksFilePath();

    /**
     * Sets the user prefs' contHACKS file path.
     */
    void setConthacksFilePath(Path conthacksFilePath);

    /**
     * Replaces contHACKS data with the data in {@code conthacks}.
     */
    void setConthacks(ReadOnlyConthacks conthacks);

    /**
     * Sorts the contacts in contHACKS by alphabetical order.
     */
    void sortConthacks();

    /** Returns the Conthacks */
    ReadOnlyConthacks getConthacks();

    /**
     * Returns true if a person with the same identity as {@code person} exists in contHACKS.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a module lesson with the same information as {@code moduleLesson} exists in contHACKS.
     */
    boolean hasModuleLesson(ModuleLesson moduleLesson);

    /**
     * Return true if a module lesson that clashes with {@code moduleLesson} exists in contHACKS.
     */
    boolean hasModuleLessonClashingWith(ModuleLesson moduleLesson);
    /**
     * Deletes the given person.
     * The person must exist in contHACKS.
     */
    void deletePerson(Person target);

    /**
     * Deletes the given lesson.
     * The lesson must exist in contHACKS.
     */
    void deleteLesson(ModuleLesson target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in contHACKS.
     */
    void addPerson(Person person);

    /**
     * Adds the given lesson.
     * {@code moduleLesson} must not already exist in contHACKS.
     */
    void addLesson(ModuleLesson moduleLesson);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in contHACKS.
     * The person identity of {@code editedPerson} must not be the same as another existing person in contHACKS.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given lesson {@code target} with {@code editedLesson}.
     * {@code target} must exist in contHACKS.
     * The person identity of {@code editedLesson} must not be the same as another existing lesson in contHACKS.
     */
    void setModuleLesson(ModuleLesson target, ModuleLesson editedLesson);

    /**
     * Remove all contact saved in contHACKS.
     */
    void clearPersons();

    /**
     * Remove all module lesson saved in contHACKS.
     */
    void clearLessons();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered moduleLesson list */
    ObservableList<ModuleLesson> getFilteredModuleLessonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered moduleLesson list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleLessonList(Predicate<ModuleLesson> predicate);
}

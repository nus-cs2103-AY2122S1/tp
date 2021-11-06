package seedu.edrecord.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSet;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.PartOfModulePredicate;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.ui.PersonListPanel;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns the user prefs' edrecord file path.
     */
    Path getEdRecordFilePath();

    /**
     * Sets the user prefs' edrecord file path.
     */
    void setEdRecordFilePath(Path edRecordFilePath);

    /**
     * Replaces edrecord data with the data in {@code edRecord}.
     */
    void setEdRecord(ReadOnlyEdRecord edRecord);

    /** Returns EdRecord */
    ReadOnlyEdRecord getEdRecord();

    /**
     * Returns true if a person with the same identity as {@code person} exists in edrecord.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in edrecord.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in edrecord.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in edrecord.
     * The person identity of {@code editedPerson} must not be the same as another existing person in edrecord.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns the user prefs' module system file path.
     */
    Path getModuleSystemFilePath();

    /**
     * Sets the user prefs' module system file path.
     */
    void setModuleSystemFilePath(Path moduleSystemFilePath);

    /**
     * Replaces module system data with the data in {@code moduleSystem}.
     */
    void setModuleSystem(ReadOnlyModuleSystem moduleSystem);

    /**
     * Returns the ModuleSystem
     */
    ReadOnlyModuleSystem getModuleSystem();

    /**
     * Returns true if a module with the same code as {@code mod} exists in the module system.
     */
    boolean hasModule(Module mod);

    /**
     * Returns true if all the modules and groups with the same code as {@code mods} exists in the module system.
     */
    boolean hasModulesAndGroups(ModuleSet mods);

    /**
     * Deletes the given module.
     * The module must exist in the module system.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the module system.
     */
    void addModule(Module mod);

    /**
     * Returns the saved module equivalent of given {@code mod}.
     * {@code mod} must already exist in the module system.
     */
    Module getModule(Module mod);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the module filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void setModuleFilter(PartOfModulePredicate predicate);

    /**
     * Returns the current selected module.
     */
    ObservableValue<Module> getSelectedModule();

    /**
     * Returns true if there is a currently selected module.
     */
    boolean hasSelectedModule();

    /**
     * Returns true if the currently selected module contains the given assignment.
     */
    boolean hasAssignmentInCurrentModule(Assignment assignment);

    /**
     * Returns true if adding the assignment {@code toAdd} will bring the total weightage
     * of all assignments under the currently selected module to above 100%.
     */
    boolean isTotalWeightageExceeded(Assignment toAdd);

    /**
     * Returns true if any existing grade of the original assignment {@code current} is
     * higher than the maximum score of {@code editedAssignment}. Both assignments
     * must be under the currently selected module.
     */
    boolean hasHigherGradeInCurrentModule(Assignment current, Assignment editedAssignment);

    /**
     * Returns an unmodifiable view of the assignment list under the currently selected module.
     */
    List<Assignment> getAssignmentList();

    /**
     * Returns the assignment that matches the given name.
     */
    Optional<Assignment> searchAssignment(Name name);

    /**
     * Adds the given assignment to the currently selected module.
     * {@code assignment} must not already exist under the currently selected module.
     */
    void addAssignment(Assignment assignment);

    /**
     * Deletes the given assignment.
     * The assignment must exist in the currently selected module.
     */
    void deleteAssignment(Assignment target);

    /**
     * Replaces the given assignment {@code target} with {@code editedAssignment}.
     * {@code target} must exist under the currently selected module in EdRecord.
     * The identity of {@code editedAssignment} must not be the same as another existing assignment.
     */
    void setAssignment(Assignment target, Assignment editedAssignment);

    /**
     * Updates the search filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void setSearchFilter(Predicate<Person> predicate);

    /**
     * Returns the currently selected view.
     */
    ObservableValue<PersonListPanel.View> getSelectedView();

    /**
     * Updates the currently selected view to the specified value.
     * @param newView The new view
     */
    void setSelectedView(PersonListPanel.View newView);
}

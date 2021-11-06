package seedu.edrecord.model;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.edrecord.model.person.PartOfModulePredicate.PREDICATE_SHOW_ALL_MODULES;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.commons.core.LogsCenter;
import seedu.edrecord.logic.commands.DeleteGradeCommand;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleSet;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.PartOfModulePredicate;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.ui.PersonListPanel;

/**
 * Represents the in-memory model of edrecord data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EdRecord edRecord;
    private final ModuleSystem moduleSystem = Module.MODULE_SYSTEM;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private PartOfModulePredicate selectedModulePredicate;
    private final ObjectProperty<Module> selectedModule = new SimpleObjectProperty<>();
    private final ObjectProperty<PersonListPanel.View> selectedView =
            new SimpleObjectProperty<>(PersonListPanel.View.CONTACTS);

    /**
     * Initializes a ModelManager with the given edRecord, moduleSystem and userPrefs.
     */
    public ModelManager(ReadOnlyEdRecord edRecord, ReadOnlyModuleSystem moduleSystem,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(edRecord, moduleSystem, userPrefs);

        logger.fine("Initializing with address book: " + edRecord + " , module system " + moduleSystem
                + " and user prefs " + userPrefs);

        this.edRecord = new EdRecord(edRecord);
        this.moduleSystem.resetData(moduleSystem);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.edRecord.getPersonList());
    }

    public ModelManager() {
        this(new EdRecord(), new ModuleSystem(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEdRecordFilePath() {
        return userPrefs.getEdRecordFilePath();
    }

    @Override
    public void setEdRecordFilePath(Path edRecordFilePath) {
        requireNonNull(edRecordFilePath);
        userPrefs.setEdRecordFilePath(edRecordFilePath);
    }

    @Override
    public Path getModuleSystemFilePath() {
        return userPrefs.getEdRecordFilePath();
    }

    @Override
    public void setModuleSystemFilePath(Path moduleSystemFilePath) {
        requireNonNull(moduleSystemFilePath);
        userPrefs.setModuleSystemFilePath(moduleSystemFilePath);
    }

    //=========== EdRecord ================================================================================

    @Override
    public void setEdRecord(ReadOnlyEdRecord edRecord) {
        this.edRecord.resetData(edRecord);
    }

    @Override
    public ReadOnlyEdRecord getEdRecord() {
        return edRecord;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return edRecord.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        edRecord.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        edRecord.addPerson(person);
        setSearchFilter(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        edRecord.setPerson(target, editedPerson);
    }

    //=========== ModuleSystem ===============================================================================

    @Override
    public void setModuleSystem(ReadOnlyModuleSystem moduleSystem) {
        this.moduleSystem.resetData(moduleSystem);
    }

    @Override
    public ReadOnlyModuleSystem getModuleSystem() {
        return moduleSystem;
    }

    @Override
    public boolean hasModule(Module mod) {
        requireNonNull(mod);
        return moduleSystem.hasModule(mod);
    }

    @Override
    public boolean hasModulesAndGroups(ModuleSet mods) {
        requireNonNull(mods);
        for (Module module : mods.getModules()) {
            if (!hasModule(module)) {
                return false;
            }

            Module modelModule = getModule(module);
            for (Group group : module.getGroupList()) {
                if (!modelModule.hasGroup(group)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void deleteModule(Module target) {
        moduleSystem.removeModule(target);
    }

    @Override
    public void addModule(Module mod) {
        moduleSystem.addModule(mod);
    }

    @Override
    public Module getModule(Module mod) {
        return moduleSystem.getModule(mod);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedEdRecord}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void setModuleFilter(PartOfModulePredicate modulePredicate) {
        requireNonNull(modulePredicate);
        this.selectedModulePredicate = modulePredicate;

        String currentModuleCode = modulePredicate.getModuleCode();
        if (!modulePredicate.equals(PREDICATE_SHOW_ALL_MODULES)) {
            this.selectedModule.set(moduleSystem.getModule(currentModuleCode));
        } else {
            this.selectedModule.set(null);
        }

        filteredPersons.setPredicate(modulePredicate);

        logger.fine(String.format("Module %s selected", currentModuleCode));
    }

    @Override
    public void setSearchFilter(Predicate<Person> searchPredicate) {
        requireNonNull(searchPredicate);
        Predicate<Person> modulePredicate =
                Optional.ofNullable(this.selectedModulePredicate).orElse(PREDICATE_SHOW_ALL_MODULES);
        filteredPersons.setPredicate(modulePredicate.and(searchPredicate));
    }

    //=========== Current Module =============================================================================

    @Override
    public ObservableValue<Module> getSelectedModule() {
        return this.selectedModule;
    }

    @Override
    public boolean hasSelectedModule() {
        return selectedModule.get() != null;
    }

    @Override
    public boolean hasAssignmentInCurrentModule(Assignment assignment) {
        return hasSelectedModule() && selectedModule.get().hasAssignment(assignment);
    }

    @Override
    public boolean isTotalWeightageExceeded(Assignment toAdd) {
        if (!hasSelectedModule()) {
            return false;
        }
        return selectedModule.get().isTotalWeightageExceeded(toAdd);
    }

    @Override
    public boolean hasHigherGradeInCurrentModule(Assignment current, Assignment editedAssignment) {
        if (!hasSelectedModule()) {
            return false;
        }
        // Score editedMaxScore = editedAssignment.getMaxScore();
        List<Person> allPersons = new ArrayList<>(edRecord.getPersonList());
        return allPersons.stream()
                .filter(selectedModulePredicate)
                .anyMatch(person -> person.hasHigherScoreThanMaxScore(current, editedAssignment));
    }

    @Override
    public List<Assignment> getAssignmentList() {
        return selectedModule.get().getAssignmentList();
    }

    @Override
    public Optional<Assignment> searchAssignment(Name name) {
        return selectedModule.get().searchAssignment(name);
    }

    @Override
    public void addAssignment(Assignment assignment) {
        selectedModule.get().addAssignment(assignment);
        setSearchFilter(PREDICATE_SHOW_ALL_PERSONS);
    }

    //=========== Current View =============================================================================
    @Override
    public ObservableValue<PersonListPanel.View> getSelectedView() {
        return selectedView;
    }

    @Override
    public void setSelectedView(PersonListPanel.View newView) {
        this.selectedView.set(newView);
    }

    @Override
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        requireAllNonNull(target, editedAssignment);

        selectedModule.get().setAssignment(target, editedAssignment);
    }

    @Override
    public void deleteAssignment(Assignment target) {
        // Delete from the currently selected module
        selectedModule.get().deleteAssignment(target);

        // Delete grades from all persons under the module
        List<Person> allPersons = new ArrayList<>(edRecord.getPersonList());
        allPersons.stream()
                .filter(selectedModulePredicate)
                .forEach(person -> {
                    Person editedPerson = DeleteGradeCommand.createEditedPerson(person, target);
                    this.setPerson(person, editedPerson);
                });
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return edRecord.equals(other.edRecord)
                && moduleSystem.equals(other.moduleSystem)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}

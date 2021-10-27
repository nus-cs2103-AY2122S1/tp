package seedu.edrecord.model;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.edrecord.model.person.PartOfModulePredicate.PREDICATE_SHOW_ALL_MODULES;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.edrecord.commons.core.GuiSettings;
import seedu.edrecord.commons.core.LogsCenter;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleGroupMap;
import seedu.edrecord.model.module.ModuleSystem;
import seedu.edrecord.model.module.ReadOnlyModuleSystem;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.PartOfModulePredicate;
import seedu.edrecord.model.person.Person;

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
    private Module selectedModule;

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
        selectedModule = null;
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
    public boolean hasModulesAndGroups(ModuleGroupMap mods) {
        requireNonNull(mods);
        for (Map.Entry<Module, Group> modClassPair : mods.getMapping().entrySet()) {
            Module mod = modClassPair.getKey();
            if (!hasModule(mod)) {
                return false;
            }

            Module modelModule = getModule(mod);
            Group group = modClassPair.getValue();
            if (!modelModule.hasGroup(group)) {
                return false;
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
        this.selectedModule = moduleSystem.getModule(currentModuleCode);

        filteredPersons.setPredicate(modulePredicate);

        logger.fine(String.format("Module %s selected", currentModuleCode));
    }

    @Override
    public void setSearchFilter(Predicate<Person> searchPredicate) {
        requireNonNull(searchPredicate);
        Predicate<Person> modulePredicate =
                Optional.ofNullable(this.selectedModulePredicate).orElse(PREDICATE_SHOW_ALL_MODULES);
        filteredPersons.setPredicate(modulePredicate.and(searchPredicate));
        for (Person p: filteredPersons) {
            System.out.println(p);
        }
    }

    //=========== Current Module =============================================================================

    @Override
    public Module getSelectedModule() {
        return this.selectedModule;
    }

    @Override
    public boolean hasSelectedModule() {
        return selectedModule != null;
    }

    @Override
    public boolean hasAssignmentInCurrentModule(Assignment assignment) {
        return hasSelectedModule() && selectedModule.hasAssignment(assignment);
    }

    @Override
    public Optional<Assignment> searchAssignment(Name name) {
        return selectedModule.searchAssignment(name);
    }

    @Override
    public void addAssignment(Assignment assignment) {
        selectedModule.addAssignment(assignment);
        setSearchFilter(PREDICATE_SHOW_ALL_PERSONS);
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

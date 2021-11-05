package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of contHACKS data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Conthacks conthacks;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<ModuleLesson> filteredModuleLessons;

    /**
     * Initializes a ModelManager with the given conthacks and userPrefs.
     */
    public ModelManager(ReadOnlyConthacks addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.conthacks = new Conthacks(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.conthacks.getPersonList());
        filteredModuleLessons = new FilteredList<>(this.conthacks.getModuleLessonList());
    }

    public ModelManager() {
        this(new Conthacks(), new UserPrefs());
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
    public Path getConthacksFilePath() {
        return userPrefs.getConthacksFilePath();
    }

    @Override
    public void setConthacksFilePath(Path conthacksFilePath) {
        requireNonNull(conthacksFilePath);
        userPrefs.setConthacksFilePath(conthacksFilePath);
    }

    //=========== Conthacks ================================================================================

    @Override
    public void setConthacks(ReadOnlyConthacks conthacks) {
        this.conthacks.resetData(conthacks);
    }

    /**
     * Sorts the address book data in alphabetical order.
     * For {@code Person}, it is sorted by their name.
     * For {@code ModuleLesson}, it is sorted by their module code.
     */
    @Override
    public void sortConthacks() {
        conthacks.sortConthacks();
    }

    @Override
    public ReadOnlyConthacks getConthacks() {
        return conthacks;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return conthacks.hasPerson(person);
    }

    @Override
    public boolean hasModuleLesson(ModuleLesson moduleLesson) {
        requireNonNull(moduleLesson);
        return conthacks.hasLesson(moduleLesson);
    }

    @Override
    public boolean hasModuleLessonClashingWith(ModuleLesson moduleLesson) {
        requireNonNull(moduleLesson);
        return conthacks.hasLessonClashingWith(moduleLesson);
    }

    @Override
    public void deletePerson(Person target) {
        conthacks.removePerson(target);
    }

    @Override
    public void deleteLesson(ModuleLesson moduleLesson) {
        conthacks.removeLesson(moduleLesson);
    }

    @Override
    public void addPerson(Person person) {
        conthacks.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addLesson(ModuleLesson moduleLesson) {
        conthacks.addLesson(moduleLesson);
        updateFilteredModuleLessonList(PREDICATE_SHOW_ALL_LESSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        conthacks.setPerson(target, editedPerson);
    }

    @Override
    public void setModuleLesson(ModuleLesson target, ModuleLesson editedLesson) {
        requireAllNonNull(target, editedLesson);

        conthacks.setModuleLesson(target, editedLesson);
    }

    @Override
    public void clearPersons() {
        conthacks.clearPersonList();
    }

    @Override
    public void clearLessons() {
        conthacks.clearLessonList();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<ModuleLesson> getFilteredModuleLessonList() {
        return filteredModuleLessons;
    }

    @Override
    public void updateFilteredModuleLessonList(Predicate<ModuleLesson> predicate) {
        requireNonNull(predicate);
        filteredModuleLessons.setPredicate(predicate);
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
        return conthacks.equals(other.conthacks)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredModuleLessons.equals(other.filteredModuleLessons);
    }

}

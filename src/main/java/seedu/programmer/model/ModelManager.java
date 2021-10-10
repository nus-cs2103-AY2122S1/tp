package seedu.programmer.model;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.programmer.commons.core.GuiSettings;
import seedu.programmer.commons.core.LogsCenter;
import seedu.programmer.model.person.Person;

/**
 * Represents the in-memory model of ProgrammerError data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ProgrammerError programmerError;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given ProgrammerError and userPrefs.
     */
    public ModelManager(ReadOnlyProgrammerError programmerError, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(programmerError, userPrefs);

        logger.fine("Initializing with address book: " + programmerError + " and user prefs " + userPrefs);

        this.programmerError = new ProgrammerError(programmerError);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.programmerError.getPersonList());
    }

    public ModelManager() {
        this(new ProgrammerError(), new UserPrefs());
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
    public Path getProgrammerErrorFilePath() {
        return userPrefs.getProgrammerErrorFilePath();
    }

    @Override
    public void setProgrammerErrorFilePath(Path programmerErrorFilePath) {
        requireNonNull(programmerErrorFilePath);
        userPrefs.setProgrammerErrorFilePath(programmerErrorFilePath);
    }

    //=========== ProgrammerError ============================================================================

    @Override
    public void setAddressBook(ReadOnlyProgrammerError programmerError) {
        this.programmerError.resetData(programmerError);
    }

    @Override
    public ReadOnlyProgrammerError getProgrammerError() {
        return programmerError;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return programmerError.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        programmerError.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        programmerError.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        programmerError.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedProgrammerError}
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
        return programmerError.equals(other.programmerError)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}

package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.exceptions.OperationException;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    private final OperationManager operations;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.operations = new OperationManager(this);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    @Override
    public void restoreState(ModelManagerState state) {
        this.addressBook.resetData(state.getAddressBook());
        this.userPrefs.resetData(state.getUserPrefs());
        filteredPersons.setPredicate(state.getFilterPredicate());
    }

    @Override
    public ModelManagerState getState() {
        @SuppressWarnings("unchecked")
        Predicate<Person> filterPredicate = (Predicate<Person>) filteredPersons.getPredicate();
        return new ModelManagerState(
                new AddressBook(this.addressBook),
                new UserPrefs(this.userPrefs),
                filterPredicate
        );
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        runOperation(() -> this.userPrefs.resetData(userPrefs));
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
        runOperation(() -> userPrefs.setGuiSettings(guiSettings));
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        runOperation(() -> userPrefs.setAddressBookFilePath(addressBookFilePath));
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        runOperation(() -> this.addressBook.resetData(addressBook));
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        runOperation(() -> addressBook.removePerson(target));
    }

    @Override
    public void deletePersons(List<Person> targets) {
        runOperation(() -> targets.forEach(addressBook::removePerson));
    }

    @Override
    public void addPerson(Person person) {
        runOperation(() -> {
            addressBook.addPerson(person);
            updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        });
    }

    @Override
    public void setPerson(Person target, Person editedPerson, boolean removeFilter) {
        requireAllNonNull(target, editedPerson);
        runOperation(() -> {
            addressBook.setPerson(target, editedPerson);
            if (removeFilter) {
                this.filteredPersons.setPredicate(PREDICATE_SHOW_ALL_PERSONS);
            }
        });
    }

    @Override
    public void sortAddressBook(Prefix prefix, boolean reverse) {
        requireNonNull(prefix);
        runOperation(() -> addressBook.sortList(prefix, reverse));
    }

    @Override
    public void importFile(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        ModelManagerState beforeState = this.getState();

        addressBook.mergeFile(filePath);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        ModelManagerState afterState = this.getState();
        registerOperation(beforeState, afterState);
    }

    @Override
    public int undo() throws OperationException {
        return operations.undo();
    }

    @Override
    public int redo() throws OperationException {
        return operations.redo();
    }

    /**
     * Convenience method that runs operations through the OperationManager
     * to allow for undoing and redoing.
     * @param op operation to be executed
     */
    private void runOperation(Runnable op) {
        operations.run(op);
    }

    /**
     * Convenience method that registers already-executed through the
     * OperationManager to allow for undoing and redoing
     * @param beforeState model state before operation was executed
     * @param afterState model state after operation was executed
     */
    private void registerOperation(ModelManagerState beforeState, ModelManagerState afterState) {
        operations.registerOperation(() -> this.restoreState(afterState), beforeState);
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
        runOperation(() -> filteredPersons.setPredicate(predicate));
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }
}

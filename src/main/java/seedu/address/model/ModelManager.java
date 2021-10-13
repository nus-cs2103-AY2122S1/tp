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
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.person.Person;
import seedu.address.model.tuition.TuitionClass;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private final FilteredList<TuitionClass> filteredTuition;

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
        filteredTuition = new FilteredList<>(this.addressBook.getTuitionList());

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
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
        addressBook.removePerson(target);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public Person getStudent(Index index) {
        requireNonNull(index);
        return addressBook.getPerson(index);
    }

    @Override
    public Person getSameNamePerson(Person otherPerson) {
        return this.addressBook.getSameNamePerson(otherPerson);
    }

    @Override
    public TuitionClass getClassById(Integer id) {
        requireAllNonNull(id);
        return addressBook.getClassById(id);
    }

    @Override
    public void sort(SortCommandParser.Order order) {
        requireAllNonNull(order);
        addressBook.sort(order);
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
        logger.info("filter" + filteredPersons.toString());
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


    //=========== Filtered Tuition List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<TuitionClass> getFilteredTuitionList() {
        return filteredTuition;
    }

    @Override
    public void updateFilteredTuitionList(Predicate<TuitionClass> predicate) {
        requireNonNull(predicate);
        filteredTuition.setPredicate(predicate);
        logger.info(filteredPersons.toString());
    }

    /*

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
    */

    @Override
    public boolean hasTuition(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        return addressBook.hasTuition(tuitionClass);
    }

    @Override
    public void setTuition(TuitionClass target, TuitionClass editedTuition) {
        requireAllNonNull(target, editedTuition);
        addressBook.setTuition(target, editedTuition);
    }

    @Override
    public TuitionClass getTuitionClass(Index index) {
        requireNonNull(index);
        return addressBook.getTuition(index);
    }

    @Override
    public TuitionClass addToClass(TuitionClass tuitionClass, Person person) {
        requireNonNull(tuitionClass);
        requireNonNull(person);
        TuitionClass c = addressBook.addToClass(tuitionClass, person);
        updateFilteredTuitionList(PREDICATE_SHOW_ALL_TUITIONS);
        return c;
    }

    @Override
    public void addTuition(TuitionClass tuitionClass) {
        addressBook.addTuition(tuitionClass);
        updateFilteredTuitionList(PREDICATE_SHOW_ALL_TUITIONS);
    }

    @Override
    public void deleteTuition(TuitionClass target) {
        addressBook.removeTuition(target);
        this.updateFilteredTuitionList(PREDICATE_SHOW_ALL_TUITIONS);
        this.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
}

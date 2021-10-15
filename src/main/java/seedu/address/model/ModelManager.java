package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.DayOfWeek;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Slot;
import seedu.address.model.person.exceptions.DuplicateShiftException;
import seedu.address.model.person.exceptions.NoShiftException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

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
    }

    @Override
    public void addPerson(Person staff) {
        addressBook.addPerson(staff);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedStaff) {
        requireAllNonNull(target, editedStaff);

        addressBook.setPerson(target, editedStaff);
    }

    @Override
    public Person findPersonByName(Name name) {
        List<Person> results = filteredPersons.stream().filter(person -> person.getName().equals(name))
                .collect(Collectors.toList());
        if (results.size() == 0) {
            return null;
        }
        return results.get(0);
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

    /**
     * Returns the Person from the filtered list with the corresponding index.
     *
     * @return the Person from the filtered list with the corresponding index.
     */
    @Override
    public Person getFilteredPersonListByIndex(int index) {
        if (filteredPersons.size() == 0) {
            return new FilteredList<>(this.addressBook.getPersonList()).get(index);
        } else {
            return filteredPersons.get(index);
        }
    }


    /**
     * Returns the unfiltered person list.
     *
     * @return Unfiltered Person list.
     */
    @Override
    public ObservableList<Person> getUnFilteredPersonList() {
        return new FilteredList<>(this.addressBook.getPersonList());
    }

    /**
     * Updates the filtered person list based on the predicate.
     *
     * @param predicate This filters the filtered person list.
     */
    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }


    @Override
    public void addShift(Person target, DayOfWeek dayOfWeek, Slot slot) throws DuplicateShiftException {
        requireAllNonNull(target, dayOfWeek, slot);
        target.addShift(dayOfWeek, slot);

    }

    @Override
    public void deleteShift(Person target, DayOfWeek dayOfWeek, Slot slot) throws NoShiftException {
        requireAllNonNull(target, dayOfWeek, slot);
        target.removeShift(dayOfWeek, slot);
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

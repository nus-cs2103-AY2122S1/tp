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
import seedu.address.model.organisation.Organisation;
import seedu.address.model.organisation.UniqueOrganisationList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Predicate<Organisation> PREDICATE_SHOW_ALL_ORGANISATIONS = unused -> true;
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final UniqueOrganisationList organisationList;
    private FilteredList<Person> filteredPersons;
    private FilteredList<Person> viewedPerson;
    private FilteredList<Organisation> filteredOrganisations;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        UniqueOrganisationList organisationList) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);
        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.organisationList = new UniqueOrganisationList(organisationList);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        viewedPerson = new FilteredList<>(this.addressBook.getPersonList());
        filteredOrganisations = new FilteredList<>(this.addressBook.getOrganisationList());
        resetViewedPerson();
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new UniqueOrganisationList());
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
    public boolean hasOrganisation(Organisation organisation) {
        requireNonNull(organisation);
        return addressBook.hasOrganisation(organisation);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        updateFilteredOrganisationList(PREDICATE_SHOW_ALL_ORGANISATIONS);
    }

    @Override
    public void deleteOrganisation(Organisation organisation) {
        addressBook.deleteOrganisation(organisation);
        updateFilteredOrganisationList(PREDICATE_SHOW_ALL_ORGANISATIONS);
    }

    @Override
    public Organisation getOrganisationByName(Name name) {
        Organisation organisation = addressBook.getOrganisationByName(name);
        return organisation;
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }
    @Override
    public void addOrganisation(Organisation organisation) {
        addressBook.addOrganisation(organisation);
        updateFilteredOrganisationList(PREDICATE_SHOW_ALL_ORGANISATIONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setOrganisation(Organisation target, Organisation editedOrganisation) {
        requireAllNonNull(target, editedOrganisation);
        addressBook.setOrganisation(target, editedOrganisation);
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
    public ObservableList<Organisation> getFilteredOrganisationList() {
        return filteredOrganisations;
    }

    @Override
    public ObservableList<Person> getViewedPerson() {
        return viewedPerson;
    }

    @Override
    public void resetViewedPerson() {
        viewedPerson.setPredicate(new Predicate<Person>() {
            @Override
            public boolean test(Person p) {
                return false;
            }
        });
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredOrganisationList(Predicate<Organisation> predicate) {
        requireNonNull(predicate);
        filteredOrganisations.setPredicate(predicate);
    }

    @Override
    public void updateViewedPerson(Predicate<Person> predicate) {
        requireNonNull(predicate);
        viewedPerson.setPredicate(predicate);
    }

    @Override
    public void sortPersonList() {
        addressBook.sortPersons();
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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

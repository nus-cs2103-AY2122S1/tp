package safeforhall.model;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import safeforhall.commons.core.GuiSettings;
import safeforhall.commons.core.LogsCenter;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.event.Event;
import safeforhall.model.event.EventName;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private static final String EMPTY_STRING = "";

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final SortedList<Person> sortedPersons;
    private final FilteredList<Event> filteredEvents;
    private final SortedList<Event> sortedEvents;
    private FilteredList<Person> singlePerson;
    private FilteredList<Event> singleEvent;

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
        filteredEvents = new FilteredList<>(this.addressBook.getEventList());
        sortedPersons = new SortedList<>(filteredPersons);
        sortedEvents = new SortedList<>(filteredEvents);
        singlePerson = new FilteredList<>(this.addressBook.getPersonList());
        singleEvent = new FilteredList<>(this.addressBook.getEventList());
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

    /**
     * Converts a {@code ResidentList} that can have a String of Room or Name to an Arraylist of
     * {@code Person}
     */
    @Override
    public ArrayList<Person> toPersonList(ResidentList residentList) throws CommandException {
        requireNonNull(residentList);
        ArrayList<String> residentInformation = residentList.getStringResidentList();
        ArrayList<Person> personList = new ArrayList<>();

        if (residentList.isEmpty()) {
            return personList;
        }

        for (String information : residentInformation) {
            Optional<Person> personFound;
            personFound = addressBook.findPerson(information);

            if (personFound.isEmpty()) {
                throw new CommandException("No resident with this information '" + information + "' could be found");
            } else if (!personList.contains(personFound.get())) {
                personList.add(personFound.get());
            }
        }
        return personList;
    }

    /**
     * Reads a string of name from {@code ResidentList} and return an Arraylist of {@code Person}
     */
    @Override
    public ArrayList<Person> getCurrentEventResidents(ResidentList residentList) throws CommandException {
        requireNonNull(residentList);
        ArrayList<Person> personList = new ArrayList<>();
        if (residentList.isEmpty()) {
            return personList;
        }
        String[] residentInformation = residentList.getResidentsStorage().split("\\s*,\\s*");

        for (String information : residentInformation) {
            String[] informationList = information.split("\\s*;\\s*");
            Optional<Person> personFound;
            personFound = addressBook.findPerson(informationList[0]);

            if (personFound.isEmpty()) {
                throw new CommandException("No event with this information '" + information + "' could be found");
            } else {
                personList.add(personFound.get());
            }
        }
        return personList;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    /**
     * Returns a String of information if {@code Person} does not exist in the address book, return
     * an empty String otherwise.
     */
    @Override
    public String getInvalidResident(Event event) throws CommandException {
        requireNonNull(event);
        ArrayList<String> residentInformation = event.getStringResidentList();

        for (String information : residentInformation) {
            Optional<Person> personFound;
            personFound = addressBook.findPerson(information);

            if (personFound.isEmpty()) {
                return information;
            }
        }
        return EMPTY_STRING;
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return addressBook.hasEvent(event);
    }

    @Override
    public Event getEvent(EventName eventName) throws CommandException {
        requireNonNull(eventName);
        Optional<Event> eventOptional = addressBook.findEvent(eventName);
        if (eventOptional.isEmpty()) {
            throw new CommandException(eventName + " not found");
        } else {
            return eventOptional.get();
        }
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void deleteEvent(Event target) {
        addressBook.removeEvent(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addEvent(Event event) {
        addressBook.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        addressBook.setEvent(target, editedEvent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return sortedPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return sortedEvents;
    }

    @Override
    public void setSinglePerson(Person person) {
        singlePerson.setPredicate(person::equals);
    }

    @Override
    public void setNoPerson() {
        singlePerson.setPredicate(resident -> false);
    }

    public ObservableList<Person> getSinglePerson() {
        return singlePerson;
    }

    @Override
    public void setSingleEvent(Event event) {
        singleEvent.setPredicate(event::equals);
    }

    @Override
    public void setNoEvent() {
        singleEvent.setPredicate(event -> false);
    }

    public ObservableList<Event> getSingleEvent() {
        return singleEvent;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public ArrayList<Event> getPersonEvents(Person person, Predicate<Event> predicate) {
        ArrayList<Event> events = new ArrayList<>();
        for (Event e: filteredEvents.filtered(predicate)) {
            if (e.getResidentList().getResidents().contains(person)) {
                if (!events.contains(e)) {
                    events.add(e);
                }
            }
        }
        return events;
    }

    //=========== Sorted List =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getSortedPersonList() {
        return sortedPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Event> getSortedEventList() {
        return sortedEvents;
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        sortedPersons.setComparator(comparator);
    }

    @Override
    public void updateSortedEventList(Comparator<Event> comparator) {
        sortedEvents.setComparator(comparator);
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
                && filteredPersons.equals(other.filteredPersons)
                && filteredEvents.equals(other.filteredEvents);
    }

}

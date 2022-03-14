package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.participant.Participant;

/**
 * Represents the in-memory model of the Managera data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Participant> filteredParticipants;
    //Add-ons for Managera
    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredParticipants = new FilteredList<>(this.addressBook.getParticipantList());
        //Add-ons for Managera
        filteredEvents = new FilteredList<>(this.addressBook.getEventList());
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
    public boolean hasParticipant(Participant participant) {
        requireNonNull(participant);
        return addressBook.hasParticipant(participant);
    }

    @Override
    public void deleteParticipant(Participant target) {
        requireNonNull(target);
        target.deleteFromEvents();
        addressBook.removeParticipant(target);
    }

    @Override
    public void addParticipant(Participant participant) {
        addressBook.addParticipant(participant);
        logger.info("Participant " + participant.getParticipantIdValue()
                + " was successfully added to AddressBook");
        updateFilteredParticipantList(PREDICATE_SHOW_ALL_PARTICIPANTS);
    }

    @Override
    public void setParticipant(Participant target, Participant editedParticipant) {
        requireAllNonNull(target, editedParticipant);
        target.shiftEvents(editedParticipant);
        addressBook.setParticipant(target, editedParticipant);
    }

    //=========== Filtered Participant List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Participant> getFilteredParticipantList() {
        return filteredParticipants;
    }

    @Override
    public Optional<Participant> findParticipant(Predicate<Participant> predicate) {
        requireNonNull(predicate);
        return filteredParticipants.stream().filter(predicate).findFirst();
    }

    @Override
    public void updateFilteredParticipantList(Predicate<Participant> predicate) {
        requireNonNull(predicate);
        filteredParticipants.setPredicate(predicate);
    }

    //=========== Event List Accessor =========================================================================

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        logger.info("AddressBook's FilteredList of Participants accessed.");
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    /**
     * Sorts the events in the addressBook.
     */
    @Override
    public void sortEvents() {
        addressBook.sortEvents();
    }

    /**
     * Returns a boolean if Managera already contain this event.
     *
     * @param event An Event instance.
     * @return A boolean indicating if the event already exists.
     */
    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return addressBook.hasEvent(event);
    }

    /**
     * Add the event to Managera.
     */
    @Override
    public void addEvent(Event event) {
        requireNonNull(event);
        addressBook.addEvent(event);
    }

    /**
     * Remove the event from Managera.
     */
    @Override
    public void deleteEvent(Event target) {
        requireNonNull(target);
        target.deleteFromParticipants();
        addressBook.deleteEvent(target);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(target);
        target.shiftParticipants(editedEvent);
        addressBook.setEvent(target, editedEvent);
    }

    @Override
    public void markEventAsDone(Event target) {
        target.markAsDone();
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
                && filteredParticipants.equals(other.filteredParticipants)
                && filteredEvents.equals(other.filteredEvents);
    }

}

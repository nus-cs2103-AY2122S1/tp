package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.residency.Residency;
import seedu.address.model.residency.exceptions.DuplicatePersonRegException;
import seedu.address.model.residency.exceptions.DuplicateRoomRegException;
import seedu.address.model.residency.exceptions.ResidencyNotFoundException;
import seedu.address.model.room.Room;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Room> PREDICATE_SHOW_ALL_ROOMS = unused -> true;
    Predicate<Residency> PREDICATE_SHOW_ALL_RECORDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a room with the same room number as {@code room} exists in the address book.
     */
    boolean hasRoom(Room room);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the given room {@code target} with {@code editedRoom}.
     * {@code target} must exist in the address book.
     * The room identity of {@code editedRoom} must not be the same as another existing room in the address book.
     */
    void setRoom(Room target, Room editedRoom);

    /**
     * Registers the residency of a set of guests in a room.
     *
     * @param room The {@code Room} to stay in
     * @param guests The {@code Set} of {@code Person}s to stay in the room
     * @throws DuplicateRoomRegException if the {@code Room} is already registered.
     * @throws DuplicatePersonRegException if any {@code Person} is already registered.
     */
    void register(Room room, Set<Person> guests);

    /**
     * Removes and de-registers a {@code Residency}, making the room and guests
     * available for new registrations.
     *
     * @throws ResidencyNotFoundException if the given residency is not registered.
     */
    void removeResidency(Residency residency);

    /**
     * Gets the {@code Residency} containing this {@code Room}, if it exists.
     *
     * @param room The room to get the residency of
     * @return An {@code Optional} with the {@code Residency} present if it exists,
     *         otherwise an empty Optional
     */
    Optional<Residency> getResidency(Room room);

    /**
     * Gets the {@code Residency} containing this {@code Person}, if it exists.
     *
     * @param guest The {@code Person} to get the residency of
     * @return An {@code Optional} with the {@code Residency} present if it exists,
     *         otherwise an empty Optional
     */
    Optional<Residency> getResidency(Person guest);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered room list */
    ObservableList<Room> getFilteredRoomList();

    /** Returns the number of rooms in the filtered room list */
    Integer getNumberOfRooms();

    /** Returns an unmodifiable view of the filtered record list */
    ObservableList<Residency> getFilteredRecordList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered room list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoomList(Predicate<Room> predicate);

    /**
     * Updates the filter of the filtered record list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRecordList(Predicate<Residency> predicate);

    /**
     * Adds the given room.
     * {@code room} must not already exist in the address book.
     */
    void addRoom(Room room);

    /**
     * Records the past residency of a set of guests in a room.
     *
     * @param residency The residency to be recorded.
     */
    void record(Residency residency);

}

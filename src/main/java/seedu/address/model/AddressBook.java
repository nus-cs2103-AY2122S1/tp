package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.residency.ReadOnlyResidencyBook;
import seedu.address.model.residency.Residency;
import seedu.address.model.residency.ResidencyBook;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    public static final String MESSAGE_INVALID_ADDRESS_BOOK =
            "The address book has a mismatch between a room's vacancy status and it's residency.";

    private final UniquePersonList persons;
    private final RoomList rooms;
    private final ResidencyBook residencyBook;
    private final ResidencyBook recordsBook;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        rooms = new RoomList();
        residencyBook = new ResidencyBook(false);
        recordsBook = new ResidencyBook(true);
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms.
     */
    public void setRooms(List<Room> rooms) {
        this.rooms.setRooms(rooms);
    }

    /**
     * Replaces the contents of the residency list with {@code residencies}.
     * {@code residencies} must not contain duplicate residencies.
     */
    public void setResidencies(List<Residency> residencies) {
        this.residencyBook.setResidencies(residencies);
    }

    /**
     * Replaces the contents of the residency records with {@code records}.
     * {@code records} must not contain duplicate residencies.
     */
    public void setRecords(List<Residency> records) {
        this.recordsBook.setResidencies(records);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setRooms(newData.getRoomList());
        setResidencies(newData.getResidencyList());
        setRecords(newData.getRecordsList());
    }

    //// (person / room)-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a room with the same identity as {@code room} exists in the address book.
     */
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return rooms.contains(room);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a room to the address book.
     * The room must not already exist in the address book.
     */
    public void addRoom(Room r) {
        rooms.add(r);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
        residencyBook.edit(target, editedPerson);
        recordsBook.edit(target, editedPerson);
    }

    /**
     * Replaces the given room {@code target} with {@code editedRoom}.
     * {@code target} must exist in the address book.
     * The room identity of {@code editedRoom} must not be the same as another existing room in the address book.
     */
    public void setRoom(Room target, Room editedRoom) {
        requireNonNull(editedRoom);

        rooms.setRoom(target, editedRoom);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// residency-level operations

    /**
     * Registers a residency using a Room object and guests as a Set of person objects.
     *
     * @param room Room object.
     * @param guests Set of Person objects residing in the room.
     */
    public void register(Room room, Set<Person> guests) {
        requireNonNull(room);
        requireNonNull(guests);
        residencyBook.register(room, guests);
    }

    /**
     * Registers a residency using a Residency object.
     *
     * @param residency Residency object that contains a Room object and guests as a Set of person objects.
     */
    public void register(Residency residency) {
        requireNonNull(residency);
        residencyBook.register(residency);
    }

    /**
     * Registers a residency into the recordsBook using a Residency object.
     *
     * @param residency Residency object that contains a Room object and guests as a Set of person objects.
     */
    public void record(Residency residency) {
        requireNonNull(residency);
        recordsBook.register(residency);
    }

    public void removeResidency(Residency residency) {
        residencyBook.remove(residency);
    }

    public Optional<Residency> getResidency(Room room) {
        return residencyBook.getResidency(room);
    }

    public Optional<Residency> getResidency(Person person) {
        return residencyBook.getResidency(person);
    }

    public Optional<Residency> getRecord(Room room) {
        return recordsBook.getResidency(room);
    }

    public Optional<Residency> getRecord(Person person) {
        return recordsBook.getResidency(person);
    }

    //// util methods

    /**
     * Returns true if the vacancy status of all rooms match their recorded statuses in the residency book.
     */
    public boolean isValid() {
        for (Room room : rooms) {
            if (residencyBook.getResidency(room).isEmpty() != room.isVacant()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Room> getRoomList() {
        return rooms.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Residency> getResidencyList() {
        return residencyBook.asUnmodifiableObservableList();
    }

    @Override
    public ReadOnlyResidencyBook getResidencyBook() {
        return residencyBook;
    }

    @Override
    public ObservableList<Residency> getRecordsList() {
        return recordsBook.asUnmodifiableObservableList();
    }

    @Override
    public ReadOnlyResidencyBook getRecordsBook() {
        return recordsBook;
    }

    @SuppressWarnings("checkstyle:CommentsIndentation")
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && rooms.equals(((AddressBook) other).rooms)
                && residencyBook.equals(((AddressBook) other).residencyBook)
                && recordsBook.equals(((AddressBook) other).recordsBook));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

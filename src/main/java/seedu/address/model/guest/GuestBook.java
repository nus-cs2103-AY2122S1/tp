package seedu.address.model.guest;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;

public class GuestBook implements ReadOnlyGuestBook {

    private final UniqueGuestList guests;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        guests = new UniqueGuestList();
    }

    public GuestBook() {

    }

    /**
     * Creates an GuestBook using the entities in the {@code toBeCopied}
     */
    public GuestBook(ReadOnlyGuestBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the guest list with {@code guests}.
     * {@code guests} must not contain duplicate guests.
     */
    public void setGuests(List<Guest> guests) {
        this.guests.setItems(guests);
    }

    /**
     * Resets the existing data of this {@code GuestBook} with {@code newData}.
     */
    public void resetData(ReadOnlyGuestBook newData) {
        requireNonNull(newData);
        setGuests(newData.getGuestList());
    }

    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the address book.
     */
    public boolean hasGuest(Guest guest) {
        requireNonNull(guest);
        return guests.contains(guest);
    }

    /**
     * Adds a guest to the address book.
     * The guest must not already exist in the address book.
     */
    public void addGuest(Guest p) {
        guests.add(p);
    }

    /**
     * Replaces the given guest {@code target} in the list with {@code editedGuest}.
     * {@code target} must exist in the address book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the address book.
     */
    public void setGuest(Guest target, Guest editedGuest) {
        requireNonNull(editedGuest);
        guests.setItem(target, editedGuest);
    }

    /**
     * Gets the given guest in the list with the given passportNumber.
     * If Guest does not exist in the guest book, then Optional is empty.
     */
    public Optional<Guest> getGuest(PassportNumber passportNumber) {
        requireNonNull(passportNumber);
        return guests.get(passportNumber);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeGuest(Guest key) {
        guests.remove(key);
    }

    @Override
    public String toString() {
        return guests.asUnmodifiableObservableList().size() + " guests";
    }

    @Override
    public ObservableList<Guest> getGuestList() {
        return guests.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GuestBook // instanceof handles nulls
                && guests.equals(((GuestBook) other).guests));
    }

    @Override
    public int hashCode() {
        return guests.hashCode();
    }

}

package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.UniqueSupplierList;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.ReservationList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueSupplierList suppliers;
    private final ReservationList reservations;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        suppliers = new UniqueSupplierList();
        reservations = new ReservationList();
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

    public void setReservations(List<Reservation> reservations) {
        this.reservations.setReservations(reservations);
    }

    /**
     * Replaces the contents of the supplier list with {@code suppliers}.
     * {@code persons} must not contain duplicate suppliers.
     */
    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers.setSuppliers(suppliers);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setSuppliers(newData.getSupplierList());
        setReservations(newData.getReservationList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /// supplier level operations
    /**
     * Returns true if a supplier with the same identity as {@code supplier} exists in the address book.
     */
    public boolean hasSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return suppliers.contains(supplier);
    }

    /**
     * Adds a supplier to the address book.
     * The supplier must not already exist in the address book.
     */
    public void addSupplier(Supplier s) {
        suppliers.add(s);
    }

    /**
     * Replaces the given supplier {@code target} in the list with {@code editedSupplier}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedSupplier} must not be the same as another existing supplier in the address
     * book.
     */
    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireNonNull(editedSupplier);

        suppliers.setSupplier(target, editedSupplier);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeSupplier(Supplier key) {
        suppliers.remove(key);
    }

    //// reservation-level operations

    /**
     * Check if {@code reservation} exists in the database
     */
    public boolean hasReservation(Reservation reservation) {
        requireNonNull(reservation);
        return reservations.contains(reservation);
    }

    /**
     * Adds a new reservation to the list
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * Replaces the reservation {@code target} in the list with {@code editedReservation}
     */
    public void setReservation(Reservation target, Reservation editedReservation) {
        requireNonNull(editedReservation);

        reservations.setReservation(target, editedReservation);
    }

    /**
     * Removes {@code key} from the database
     * {@code key} must exist in the list
     */
    public void removeReservation(Reservation key) {
        reservations.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return String.format(
                "%d persons\n%d suppliers\n%d reservations\n",
                persons.asUnmodifiableObservableList().size(),
                suppliers.asUnmodifiableObservableList().size(),
                reservations.asUnmodifiableObservableList().size());
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Supplier> getSupplierList() {
        return suppliers.asUnmodifiableObservableList();
    }
  
    public ObservableList<Reservation> getReservationList() {
        return reservations.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && suppliers.equals(((AddressBook) other).suppliers));
                && reservations.equals(((AddressBook) other).reservations));
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, suppliers, reservations);
    }
}

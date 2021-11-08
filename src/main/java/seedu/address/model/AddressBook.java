package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.organisation.Organisation;
import seedu.address.model.organisation.UniqueOrganisationList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueOrganisationList organisations;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        organisations = new UniqueOrganisationList();
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
    public void setOrganisations(List<Organisation> organisations) {
        this.organisations.setOrganisations(organisations);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setOrganisations(newData.getOrganisationList());
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
     * Returns true if a organisation with the same identity as {@code organisation} exists in the address book.
     */
    public boolean hasOrganisation(Organisation organisation) {
        requireNonNull(organisation);
        return organisations.contains(organisation);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds an organisation to the address book.
     * The organisation must not already exist in the address book.
     */
    public void addOrganisation(Organisation o) {
        organisations.add(o);
    }

    /**
     * Deletes an organisation from the address book.
     * The organisation must exist in the address book.
     */
    public void deleteOrganisation(Organisation organisation) {
        organisations.remove(organisation);
    }

    /**
     * Gets organisation by name.
     * The organisation must exist in the organisation list.
     */
    public Organisation getOrganisationByName(Name name) {
        Organisation organisation = organisations.getByName(name);
        return organisation;
    }

    /**
     * Sort address book alphabetically.
     */
    public void sortPersons() {
        persons.sortPersons();
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
     * Replaces the given organisation {@code target} with {@code editedOrganisation}.
     * {@code target} must exist in the address book.
     * The organisation identity of {@code editedOrganisation} must not be the same as another
     * existing organisation in the address book.
     */
    public void setOrganisation(Organisation target, Organisation editedOrganisation) {
        requireNonNull(editedOrganisation);

        organisations.setOrganisation(target, editedOrganisation);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons "
                + organisations.asUnmodifiableObservableList().size() + " organisations.";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }
    @Override
    public ObservableList<Organisation> getOrganisationList() {
        return organisations.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && organisations.equals(((AddressBook) other).organisations));
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + organisations.hashCode();
    }
}

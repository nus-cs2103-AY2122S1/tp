package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.group.SubGroup;
import seedu.address.model.group.SuperGroup;
import seedu.address.model.person.Person;
import seedu.address.model.util.UniqueList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueList<Person> persons;

    private HashMap<String, SuperGroup> superGroups;

    private HashMap<String, SubGroup> subGroups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueList<>();
        superGroups = new HashMap<>();
        subGroups = new HashMap<>();
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
        this.persons.setItems(persons);
    }

    /**
     * Replaces the contents of the person list with {@code SuperGroups}.
     * {@code SuperGroups} must not contain duplicate SuperGroups.
     */
    public void setSuperGroups(HashMap<String, SuperGroup> superGroups) {
        this.superGroups = superGroups;
    }

    /**
     * Replaces the contents of the person list with {@code SuperGroups}.
     * {@code SuperGroups} must not contain duplicate SuperGroups.
     */
    public void setSubGroups(HashMap<String, SubGroup> subGroups) {
        this.subGroups = subGroups;
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setSuperGroups(newData.getSuperGroups());
        setSubGroups(newData.getSubGroups());
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
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Gets Person based on name.
     */
    public Person findPerson(String name) {
        for (Person person: persons) {
            if (person.getName().fullName.equals(name)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setItem(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// Group-level operations

    /**
     * Adds superGroup into the AddressBook.
     * @param sg the SuperGroup to be added into AddressBook.
     */
    public void addSuperGroup(SuperGroup sg) {
        if (!superGroups.containsKey(sg.getName())) {
            superGroups.put(sg.getName(), sg);
        }
    }

    public void deleteSuperGroup(SuperGroup sg) {
        superGroups.remove(sg.getName());
    }

    /**
     * Gets SuperGroup based on group name.
     */
    public SuperGroup findSuperGroup(String name) {
        return superGroups.get(name);
    }

    /**
     * Returns true if SuperGroup exists.
     */
    public boolean hasSubGroup(SubGroup subGroup) {
        requireNonNull(subGroup);
        return subGroups.containsKey(subGroup.toString());
    }

    /**
     * Adds SubGroup into the AddressBook.
     * @param subGroup the SuperGroup to be added into AddressBook.
     */
    public void addSubGroup(SubGroup subGroup) {
        requireNonNull(subGroup);
        subGroups.put(subGroup.toString(), subGroup);
    }

    public void deleteSubGroup(SubGroup subGroup) {
        subGroups.remove(subGroup.toString());
    }

    /**
     * Gets SuperGroup based on group name.
     */
    public SubGroup findSubGroup(String name) {
        return subGroups.get(name);
    }

    /**
     * Returns true if SuperGroup exists.
     */
    public boolean hasSuperGroup(SuperGroup superGroup) {
        requireNonNull(superGroup);
        return superGroups.containsKey(superGroup.getName());
    }


    //// util methods

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
    public HashMap<String, SuperGroup> getSuperGroups() {
        return superGroups;
    }

    @Override
    public HashMap<String, SubGroup> getSubGroups() {
        return subGroups;
    }

    @Override
    public boolean equals(Object other) {
        // TODO: Add Group
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

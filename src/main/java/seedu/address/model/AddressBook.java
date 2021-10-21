package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagIsUnreferenced;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    private final UniquePersonList persons;
    private final UniqueTagList tags;

    private String clientCounter;

    {
        /*
         * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
         * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
         *
         * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
         *   among constructors.
         */
        persons = new UniquePersonList();
        tags = new UniqueTagList();
    }

    public AddressBook() {
    }

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
        removeUnreferencedTags();
    }

    /**
     * Replaces the clientCounter of the address book with {@code clientCounter}.
     */
    @Override
    public void setClientCounter(String clientCounter) {
        this.clientCounter = clientCounter;
    }

    /**
     * Increments the clientCounter of the address book by 1 {@code clientCounter}.
     */
    @Override
    public void incrementClientCounter() {
        try {
            int clientCounterInt = Integer.parseInt(this.clientCounter) + 1;
            this.clientCounter = String.valueOf(clientCounterInt);
        } catch (NumberFormatException e) {
            this.clientCounter = "1";
        }
    }

    /**
     * Gets the clientCounter of the address book.
     */
    @Override
    public String getClientCounter() {
        return this.clientCounter;
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setClientCounter(newData.getClientCounter());
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
     * Returns true if a client with the given clientId exists.
     *
     * @param clientId of client
     * @return true if a client with the given clientId exists
     */
    public boolean hasClientId(ClientId clientId) {
        return persons.hasClientId(clientId);
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
     *
     * @return
     */
    public List<Person> setPersonByClientIds(List<ClientId> clientIds, EditPersonDescriptor editedPersonDescriptor) {
        requireNonNull(clientIds);
        requireNonNull(editedPersonDescriptor);
        return persons.setPersonByClientIds(clientIds, editedPersonDescriptor);
    }

    /**
     * Retrieve the NextMeeting from the given LocalDate by the user {@code date}.
     *
     * @return a list of NextMeetings that's on the same date as {@code date}
     */
    public List<Person> retrieveLastMeetings (LocalDate date) {
        requireNonNull(date);
        return persons.retrieveNextMeetings(date);
    }

    /**
     * Returns person with corresponding clientId.
     *
     * @param clientId clientId of client
     * @return client with given clientId
     */
    public Person getPerson(ClientId clientId) {
        requireNonNull(clientId);
        return persons.getPerson(clientId);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        removeUnreferencedTags();
    }

    //// tag-level operations

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    /**
     * Removes tags that are unreferenced from the list.
     */
    public void removeUnreferencedTags() {
        logger.info("Cleaning unreferenced tags...");
        ArrayList<Predicate<Tag>> predicatesToDelete = new ArrayList<>();
        predicatesToDelete.add(new TagIsUnreferenced());
        try {
            FilteredList<Tag> removedTags = removeTagByFields(predicatesToDelete);
            logger.info(removedTags.size() + " unreferenced tags are cleared.");
        } catch (TagNotFoundException ignored) {
            logger.info("0 unreferenced tags are cleared.");
        }
    }

    /**
     * Returns true if a tag with the given {@code tagName} exists.
     *
     * @param tagName name of the tag
     * @return true if a tag with the given tagName exists
     */
    public boolean hasTagName(String tagName) {
        return tags.hasTagName(tagName);
    }

    /**
     * Returns tag with the corresponding {@code tagName}.
     *
     * @param tagName name of the tag
     * @return tag with given tagName
     */
    public Tag getTag(String tagName) {
        requireNonNull(tagName);
        return tags.getTag(tagName);
    }


    //// util methods

    /**
     * Removes person with matching {@code clientId} and {@code email} from this {@code AddressBook}.
     * Person with {@code clientId} and {@code email} must exist in the address book.
     */
    public List<Person> deletePersonByClientIds(List<ClientId> clientIds) {
        return persons.deletePersonByClientIds(clientIds);
    }

    /**
     * Removes person with matching {@code clientId} and {@code email} from this {@code AddressBook}.
     * Person with {@code clientId} and {@code email} must exist in the address book.
     */
    public FilteredList<Tag> removeTagByFields(List<Predicate<Tag>> predicates) {
        return tags.removeByFields(predicates);
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    public ObservableList<Tag> getTagList() {
        return tags.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddressBook // instanceof handles nulls
            && persons.equals(((AddressBook) other).persons));
    }


    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

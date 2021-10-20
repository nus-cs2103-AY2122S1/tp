package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.EditCommand.createEditedPerson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList implements Iterable<Person> {

    private final ObservableList<Person> internalList = FXCollections.observableArrayList();
    private final ObservableList<Person> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public List<Person> setPersonByClientIds(List<ClientId> clientIds, EditPersonDescriptor editPersonDescriptor) {
        requireAllNonNull(clientIds, editPersonDescriptor);
        List<Person> duplicateList = new ArrayList<>(internalList);
        // Check for duplicate
        List<Person> editedPersonList = clientIds.stream().map(clientId -> {
            Person person = getPerson(clientId);
            Person editedPerson = createEditedPerson(person, editPersonDescriptor);
            int index = duplicateList.indexOf(person);
            duplicateList.set(index, editedPerson);
            return editedPerson;
        }).collect(Collectors.toList());

        if (!personsAreUnique(duplicateList)) {
            throw new DuplicatePersonException();
        }

        // set if no duplicate
        clientIds.forEach(clientId -> {
            Person person = getPerson(clientId);
            Person editedPerson = createEditedPerson(person, editPersonDescriptor);
            int index = internalList.indexOf(person);
            internalList.set(index, editedPerson);
        });

        return editedPersonList;
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Person toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns the person with the corresponding {@code clientId}.
     */
    public Person getPerson(ClientId clientId) {
        ObservableList<Person> personInQuestion = internalList
                .filtered(person -> person.getClientId().equals(clientId));
        if (personInQuestion.isEmpty()) {
            throw new PersonNotFoundException();
        }
        return personInQuestion.get(0);
    }

    /**
     * Returns true if a client with the given {@code clientId} exists.
     *
     * @param clientId Id of the client
     * @return true if a client with the clientId exists
     */
    public boolean hasClientId(ClientId clientId) {
        ObservableList<Person> personInQuestion = internalList
                .filtered(person -> person.getClientId().equals(clientId));
        return !personInQuestion.isEmpty();
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    // XXX: when is this used?
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        } else {
            toRemove.delete();
        }
    }

    /**
     * Removes the equivalent person with matching client id and/or email from the list.
     * The person must exist in the list.
     */
    public List<Person> deletePersonByClientIds(List<ClientId> clientIds) {
        requireAllNonNull(clientIds);
        List<Person> personFound = new ArrayList<>();
        List<ClientId> clientIdNotFound = new ArrayList<>();
        for (ClientId clientId: clientIds) {
            ObservableList<Person> personInQuestion = internalList
                    .filtered(person -> person.getClientId().equals(clientId));
            if (personInQuestion.isEmpty()) {
                clientIdNotFound.add(clientId);
            } else {
                personFound.add(personInQuestion.get(0));
            }
        }

        if (!clientIdNotFound.isEmpty()) {
            throw new PersonNotFoundException(clientIdNotFound);
        }

        personFound.forEach(internalList::remove);
        return personFound;
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Finds Persons with meetings on the given {@code date}.
     *
     * @param date inputted by user for the "schedule" command.
     * @return list of persons with meetings on the given {@code date}.
     */

    public List<Person> retrieveNextMeetings(LocalDate date) {
        Predicate<Person> personsWithSameDateNextMeeting = (person) -> {
            LocalDate meetingDate = person.getNextMeetingDate();
            if (meetingDate != null) {
                return meetingDate.equals(date);
            } else {
                return false;
            }
        };
        return internalList.filtered(personsWithSameDateNextMeeting);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Person> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Person> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniquePersonList // instanceof handles nulls
            && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Person> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSamePerson(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

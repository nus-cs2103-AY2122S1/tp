package seedu.notor.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.logic.parser.ParserUtil;
import seedu.notor.logic.parser.exceptions.ParseException;
import seedu.notor.model.common.Note;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.person.Person;
import seedu.notor.model.util.UniqueList;

/**
 * Wraps all data at the 'Notor' level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Notor implements ReadOnlyNotor {
    private static final String INTIAL_NOTE = "Welcome to NOTOR v1.4! This application is a personal\n"
            + " CLI designed for mentors to keep tabs on their mentees.\n"
            + "If you need help, you can refer to the help page!\n\n"
            + "Some things this application can do are: \n"
            + "person /list : Lists all contacts.\n"
            + "person 3 /delete : Deletes the 3rd contact listed.\n"
            + "note: Edit this note here to whatever you wish!"
            + "\n\n\n ...and much more!";

    private final UniqueList<Person> persons;
    private final UniqueList<SuperGroup> superGroups;
    private final UniqueList<Person> personArchive;

    private Note note;

    {
        persons = new UniqueList<>();
        superGroups = new UniqueList<>();
        personArchive = new UniqueList<>();
    }

    public Notor() {
        this.note = Note.of(INTIAL_NOTE, LocalDate.now().toString());
    }

    public Notor(Note note) {
        this.note = note;
    }

    /**
     * Creates an Notor using the Persons in the {@code toBeCopied}
     */
    public Notor(ReadOnlyNotor toBeCopied) {
        this(toBeCopied.getNote());
        toBeCopied.getPersonList().forEach(persons::add);
        toBeCopied.getPersonArchiveList().forEach(personArchive::add);
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
    public void setSuperGroups(List<SuperGroup> superGroups) {
        this.superGroups.setItems(superGroups);
    }

    public void setPersonArchive(List<Person> persons) {
        this.personArchive.setItems(persons);
    }

    /**
     * Resets the existing data of this {@code Notor} with {@code newData}.
     */
    public void resetData(ReadOnlyNotor newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setSuperGroups(newData.getSuperGroups());
        setPersonArchive(newData.getPersonArchiveList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in Notor.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in Notor archive.
     */
    public boolean hasArchive(Person person) {
        requireNonNull(person);
        return personArchive.contains(person);
    }

    /**
     * Adds a person to Notor.
     * The person must not already exist in Notor.
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Adds a person to the Notor archive.
     * The person must exist in Notor.
     * The person must not already exist in Notor archive.
     */
    public void archivePerson(Person person) {
        requireNonNull(person);
        persons.remove(person);
        personArchive.add(person);
    }

    /**
     * Removes a person from the Notor archive.
     * The person must exist in Notor archive.
     * The person must not already exist in Notor.
     */
    public void unarchivePerson(Person person) {
        requireNonNull(person);
        persons.add(person);
        personArchive.remove(person);
    }

    /**
     * Adds a person to the Notor archive.
     * The person must not already exist in Notor archive.
     */
    public void addArchivePerson(Person person) {
        requireNonNull(person);
        personArchive.add(person);
    }

    /**
     * Gets Person based on name.
     */
    public Person findPerson(String name) {
        for (Person person : persons) {
            if (person.getName().fullName.equals(name)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Adds mutliple Person to SuperGroup, and SuperGroup to multiple Person.
     */
    public void addPersonToSuperGroup(SuperGroup superGroup, String... personNames) {
        for (String personName: personNames) {
            Person person = findPerson(personName);
            person.addSuperGroup(superGroup);
            superGroup.addPerson(person);
        }
    }

    /**
     * Adds multiple persons to subGroup, and subGroup to multiple Person.
     */
    public void addPersonToSubGroup(SubGroup subGroup, String... personNames) throws ExecuteException {
        for (String personName: personNames) {
            Person person = findPerson(personName);
            person.addSubGroup(subGroup);
            subGroup.addPerson(person);
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in Notor.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setItem(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code Notor}.
     * {@code key} must exist in Notor.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// Group-level operations

    /**
     * Adds superGroup into the Notor.
     *
     * @param sg the SuperGroup to be added into Notor.
     */
    public void addSuperGroup(SuperGroup sg) {
        superGroups.add(sg);
    }

    /**
     * Adds superGroup into the Notor.
     *
     * @param sg the name of the SuperGroup to be added into Notor.
     */
    public void addSuperGroup(String sg) throws ParseException {
        superGroups.add(ParserUtil.parseSuperGroup(sg));
    }

    /**
     * Removes superGroup from Notor.
     *
     * @param sg the SuperGroup to removed.
     */
    public void deleteSuperGroup(SuperGroup sg) {
        for (Person person : sg.getPeople().values()) {
            person.removeSuperGroup(sg.toString());
        }
        superGroups.remove(sg);
    }

    /**
     * Finds a Group based on display name.
     *
     * @param name Display name of the Group.
     * @return Group with the specified display name.
     */
    public Group findGroup(String name) {
        if (name.contains("_")) {
            String[] splitName = name.split("_");
            return findSuperGroup(splitName[0]).findSubGroup(splitName[1]);
        }
        return findSuperGroup(name);
    }

    /**
     * Gets SuperGroup based on group name.
     */
    public SuperGroup findSuperGroup(String name) {
        // TODO: Change this method when UniqueList is updated.
        for (SuperGroup superGroup : superGroups) {
            if (superGroup.getName().equals(name)) {
                return superGroup;
            }
        }
        return null;
    }

    /**
     * Returns true if SuperGroup exists.
     */
    public boolean hasSuperGroup(SuperGroup superGroup) {
        // TODO: Change this method when UniqueList is updated.
        requireNonNull(superGroup);
        return superGroups.contains(superGroup);
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons, "
                + superGroups.asUnmodifiableObservableList().size() + "groups";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonArchiveList() {
        return personArchive.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<SuperGroup> getSuperGroups() {
        return superGroups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        // TODO: Add Group
        return other == this // short circuit if same object
                || (other instanceof Notor // instanceof handles nulls
                && persons.equals(((Notor) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    @Override
    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}

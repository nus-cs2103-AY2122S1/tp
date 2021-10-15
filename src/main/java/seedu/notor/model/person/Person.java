package seedu.notor.model.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.notor.model.exceptions.DuplicateItemException;
import seedu.notor.model.exceptions.ItemNotFoundException;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.tag.Tag;
import seedu.notor.model.util.Unique;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Unique<Person> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Note note;
    private final Set<Tag> tags = new HashSet<>();
    private HashSet<String> superGroups = new HashSet<>();
    private HashSet<String> subGroups = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Note note, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.tags.addAll(tags);
    }

    /**
     * Creates a person with groups and subgroups.
     */
    public Person(Name name, Phone phone, Email email, Note note, Set<Tag> tags,
        HashSet<String> superGroups, HashSet<String> subGroups) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.tags.addAll(tags);
        this.superGroups = superGroups;
        this.subGroups = subGroups;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Note getNote() {
        return note;
    }
    public String getNoteSavedDate() {
        return note.getSavedDate();
    }

    /**
     * Adds a SuperGroup to person.
     * @param superGroup the SuperGroup to be added to that person.
     * @throws DuplicateItemException if person is already in the group.
     */
    public void addSuperGroup(SuperGroup superGroup) throws DuplicateItemException {
        if (superGroups.contains(superGroup.toString())) {
            throw new DuplicateItemException();
        }
        superGroups.add(superGroup.toString());
    }

    /**
     * Adds a SuperGroup to person.
     * @param superGroup the name of the SuperGroup to be added to that person.
     * @throws DuplicateItemException if person is already in the group.
     */
    public void addSuperGroup(String superGroup) throws DuplicateItemException {
        if (superGroups.contains(superGroup)) {
            throw new DuplicateItemException();
        }
        superGroups.add(superGroup);
    }

    /**
     * Adds a SubGroup to person.
     * @param subGroup the SubGroup to be added to that person.
     * @throws DuplicateItemException if person is already in the group.
     */
    public void addSubGroup(SubGroup subGroup) {
        if (subGroups.contains(subGroup.toString())) {
            throw new DuplicateItemException();
        }
        subGroups.add(subGroup.toString());
    }

    /**
     * Removes a SuperGroup from the person.
     * @param superGroup the name of the SuperGroup to be removed to that person.
     * @throws ItemNotFoundException if person is not in in the group.
     */
    public void removeSuperGroup(String superGroup) throws ItemNotFoundException {
        if (!superGroups.contains(superGroup)) {
            throw new ItemNotFoundException();
        }
        subGroups.removeIf(subGroup -> subGroup.split("_")[0].equals(superGroup));
        superGroups.remove(superGroup);
    }

    /**
     * Removes a subgroup from the person.
     * @param subGroup the subgroup to be removed.
     * @throws ItemNotFoundException if SubGroup is not found.
     */
    public void removeSuperGroup(SubGroup subGroup) throws ItemNotFoundException {
        if (!subGroups.contains(subGroup.toString())) {
            throw new ItemNotFoundException();
        }
        subGroups.remove(subGroup);
    }

    public HashSet<String> getSuperGroups() {
        return superGroups;
    }

    public HashSet<String> getSubGroups() {
        return subGroups;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSame(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());

        String noteSavedDate = note.getSavedDate();
        if (!noteSavedDate.isEmpty()) {
            builder.append("; Last Edited: ")
                    .append(getNoteSavedDate());
        }
        Set<Tag> tags = getTags();

        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

}

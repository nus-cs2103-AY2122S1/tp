package seedu.notor.model.person;

import static seedu.notor.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

import seedu.notor.logic.executors.exceptions.ExecuteException;
import seedu.notor.model.Notable;
import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.exceptions.DuplicateItemException;
import seedu.notor.model.exceptions.ItemNotFoundException;
import seedu.notor.model.group.Group;
import seedu.notor.model.group.SubGroup;
import seedu.notor.model.group.SuperGroup;
import seedu.notor.model.tag.Tag;
import seedu.notor.model.util.Unique;

/**
 * Represents a Person in Notor.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Unique<Person>, Notable {

    // Identity fields
    @CsvBindByName(column = "Name")
    private final Name name;
    @CsvBindByName(column = "Phone")
    private final Phone phone;
    @CsvBindByName(column = "Email")
    private final Email email;

    // Data fields
    @CsvBindAndSplitByName(column = "Tags", elementType = Tag.class, collectionType = HashSet.class,
            writeDelimiter = ", ")
    private final Set<Tag> tags = new HashSet<>();

    @CsvBindByName(column = "Note")
    private Note note = Note.EMPTY_NOTE;

    @CsvBindAndSplitByName(column = "SuperGroups", elementType = String.class, collectionType = HashSet.class,
                            writeDelimiter = ", ")
    private HashSet<String> displaySuperGroups = new HashSet<>();
    @CsvBindAndSplitByName(column = "SubGroups", elementType = String.class, collectionType = HashSet.class,
                            writeDelimiter = ", ")
    private HashSet<String> displaySubGroups = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Note note) {
        requireAllNonNull(name, phone, email);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }

    /**
     * May create a person with tags
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
        this.displaySuperGroups = superGroups;
        this.displaySubGroups = subGroups;
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

    @Override
    public Note getNote() {
        return note;
    }

    public String getNoteSavedDate() {
        return note.getSavedDate();
    }

    /**
     * Adds a group to the current Person instance.
     *
     * @param group Group to be added to the current Person instance.
     * @throws DuplicateItemException If group has already been added to the current Person instance.
     */
    public void addGroup(Group group) throws DuplicateItemException, ExecuteException {
        if (group instanceof SuperGroup) {
            addSuperGroup((SuperGroup) group);
        }
        if (group instanceof SubGroup) {
            addSubGroup((SubGroup) group);
        }
    }

    /**
     * Adds a SuperGroup to person.
     *
     * @param superGroup the SuperGroup to be added to that person.
     * @throws DuplicateItemException if person is already in the group.
     */
    public void addSuperGroup(SuperGroup superGroup) throws DuplicateItemException {
        if (displaySuperGroups.contains(superGroup.toString())) {
            throw new DuplicateItemException();
        }
        displaySuperGroups.add(superGroup.toString());
    }

    /**
     * Adds a SuperGroup to person.
     *
     * @param superGroup the name of the SuperGroup to be added to that person.
     * @throws DuplicateItemException if person is already in the group.
     */
    public void addSuperGroup(String superGroup) throws DuplicateItemException {
        if (displaySuperGroups.contains(superGroup)) {
            throw new DuplicateItemException();
        }
        displaySuperGroups.add(superGroup);
    }

    /**
     * Adds a SubGroup to person.
     *
     * @param subGroup the SubGroup to be added to that person.
     * @throws DuplicateItemException if person is already in the group.
     */
    public void addSubGroup(SubGroup subGroup) throws ExecuteException {
        if (displaySubGroups.contains(subGroup.toString())) {
            throw new DuplicateItemException();
        }
        if (!displaySuperGroups.contains(subGroup.getParent())) {
            String errorMsg = String.format("%1$s does not belong in %2$s. Please add him to %3$s first.",
                this.name, subGroup.getParent(), subGroup.getParent());
            throw new ExecuteException(errorMsg);
        }
        displaySubGroups.add(subGroup.toString());
    }

    /**
     * Removes a SuperGroup from the person.
     *
     * @param superGroup the name of the SuperGroup to be removed to that person.
     * @throws ItemNotFoundException if person is not in in the group.
     */
    public void removeSuperGroup(String superGroup) throws ItemNotFoundException {
        if (!displaySuperGroups.contains(superGroup)) {
            throw new ItemNotFoundException();
        }
        displaySubGroups.removeIf(subGroup -> subGroup.split("_")[0].equals(superGroup));
        displaySuperGroups.remove(superGroup);
    }

    /**
     * Removes a subgroup from the person.
     *
     * @param subGroup the subgroup to be removed.
     * @throws ItemNotFoundException if SubGroup is not found.
     */
    public void removeSubGroup(SubGroup subGroup) throws ItemNotFoundException {
        if (!displaySubGroups.contains(subGroup.toString())) {
            throw new ItemNotFoundException();
        }
        displaySubGroups.remove(subGroup.toString());
    }

    public HashSet<String> getDisplaySuperGroups() {
        return displaySuperGroups;
    }

    public HashSet<String> getDisplaySubGroups() {
        return displaySubGroups;
    }

    /**
     * Tests if it is a valid display subgroup.
     * @param test The string to test.
     * @return The result of the test.
     */
    public static boolean isValidDisplaySubGroup(String test) {
        String validationRegex = "[a-zA-Z][\\p{Alnum}-._ ]*";
        return test.matches(validationRegex);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public String getTagsString() {
        AtomicReference<String> tagString = new AtomicReference<>(new String());
        tags.forEach(str -> tagString.set(tagString.get() + str + ", "));
        return tagString.get();
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
                && otherPerson.getNote().equals(getNote())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getDisplaySuperGroups().equals(getDisplaySuperGroups())
                && otherPerson.getDisplaySubGroups().equals(getDisplaySubGroups());
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
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}

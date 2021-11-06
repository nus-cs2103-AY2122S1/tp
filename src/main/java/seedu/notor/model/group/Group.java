package seedu.notor.model.group;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import seedu.notor.model.Notable;
import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.exceptions.ItemNotFoundException;
import seedu.notor.model.person.Person;
import seedu.notor.model.tag.Tag;
import seedu.notor.model.util.UniqueList;

/**
 * Represents a group which a person can belong to.
 */
public abstract class Group implements Notable {

    public static final String MESSAGE_CONSTRAINTS = "Group should not contain colon or slash";

    protected Name name;
    protected Set<Tag> tags;
    protected Note note = Note.EMPTY_NOTE;
    protected HashMap<String, Person> people;

    // TODO: RequireAllNonNull assertions/checks
    /**
     * Constructor for a Group instance.
     *
     * @param name the name of the group.
     */
    public Group(Name name, Set<Tag> tags) {
        this.name = name;
        this.tags = tags;
        people = new HashMap<>();
    }

    /**
     * Constructor for a Group instance.
     *
     * @param name the name of the group.
     */
    public Group(Name name, Set<Tag> tags, Note note) {
        this.name = name;
        this.tags = tags;
        this.note = note;
        people = new HashMap<>();
    }
    @Override
    public Note getNote() {
        return note;
    }

    public String getNoteSavedDate() {
        return note.getSavedDate();
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

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public abstract String getName();

    public HashMap<String, Person> getPeople() {
        return people;
    }

    public UniqueList<Person> getPersons() {
        UniqueList<Person> persons = new UniqueList<>();
        for (int i = 0; i < people.size(); i++) {
            persons.add(people.get(i));
        }
        return persons;
    }

    /**
     * Returns true if a given string is a valid group name.
     */
    public static boolean isValidGroupName(String test) {
        // TODO: Check if this is the only condition.
        return !test.matches(".*[:/].*") && !test.isEmpty();
    }

    public void addPerson(Person p) {
        people.put(p.getName().toString(), p);
    }

    /**
     * Removes the person from the group.
     *
     * @param p The person object to be removed.
     * @throws ItemNotFoundException if Person is not found.
     */
    public void removePerson(Person p) throws ItemNotFoundException {
        if (!people.containsKey(p.getName().toString())) {
            throw new ItemNotFoundException();
        }
        people.remove(p.getName().toString());
    }
}

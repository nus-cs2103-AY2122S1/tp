package seedu.notor.model.group;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

import seedu.notor.model.Notable;
import seedu.notor.model.common.Name;
import seedu.notor.model.common.Note;
import seedu.notor.model.exceptions.ItemNotFoundException;
import seedu.notor.model.person.Person;
import seedu.notor.model.tag.Tag;

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

    /**
     * Returns true if a given string is a valid group name.
     */
    public static boolean isValidGroupName(String test) {
        // TODO: Check if this is the only condition.
        return !test.matches(".*[:/].*");
    }

    public void addPerson(Person p) {
        people.put(p.getName().toString(), p);
    }

    /**
     * Remvoes the person from the group.
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

package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.ObservableList;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    // TODO: tag color + FXML
    private final String tagName;
    private final Set<Person> persons = new HashSet<>();

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    /**
     * Returns a list of persons that have this tag.
     *
     * @return A list of persons that have this tag.
     */
    public Set<Person> getPersons() {
        return Collections.unmodifiableSet(persons);
    }

    /**
     * Returns the name of this tag.
     *
     * @return The name of this tag.
     */
    public String getName() {
        return tagName;
    }

    /**
     * Adds {@code Person} to the list of persons this tag is assigned to.
     *
     * @param person The person to be added to the list.
     */
    public void addPerson(Person person) {
        persons.add(person);
    }

    /**
     * Removes {@code Person} from the list of persons this tag is assigned to.
     *
     * @param person The person to be removed from the list.
     */
    public void removePerson(Person person) {
        persons.remove(person);
    }

    /**
     * Returns true if both tags have the same name.
     * This defines a weaker notion of equality between two tags.
     */
    public boolean isSameTag(Tag otherTag) {
        if (otherTag == this) {
            return true;
        }

        return otherTag != null && otherTag.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Tag // instanceof handles nulls
            && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + tagName + ']';
    }
}

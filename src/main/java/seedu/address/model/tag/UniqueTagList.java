package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * A list of tags that enforces uniqueness between its elements in terms of tag names, and does not allow nulls.
 */
public class UniqueTagList {
    private static final HashMap<Tag, Integer> tagCounter = new HashMap<>();

    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the internal tag list contains an equivalent tag as the given argument.
     *
     * @param toCheck Tag to be checked if it is contained in the internal tag list.
     * @return True if the internal tag list contains an equivalent tag as the given argument.
     */
    public boolean containsTag(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tag to the list.
     * If the tag already exists in the list, update number of students labelled under the specified tag in tag
     * counter.
     *
     * @param toAdd Tag to be added to the list.
     */
    public void addTag(Tag toAdd) {
        requireNonNull(toAdd);
        if (containsTag(toAdd)) {
            int newNumStudents = tagCounter.get(toAdd) + 1;
            tagCounter.put(toAdd, newNumStudents);
        } else {
            tagCounter.put(toAdd, 1);
            internalList.add(toAdd);
        }
    }

    /**
     * Adds tags from the specified person to the internal tag list.
     * Tags will be sorted alphabetically.
     *
     * @param person Person whose tags are to be added to the tag list.
     */
    public void addTagFromPerson(Person person) {
        requireNonNull(person);
        person.addTagsToTagList(this);
        sortTags();
    }

    /**
     * Adds tags from the specified list of persons to the internal tag list.
     *
     * @param persons List of persons whose tags are to be added to the tag list.
     */
    public void addTagFromPersonList(List<Person> persons) {
        requireAllNonNull(persons);
        for (Person person : persons) {
            addTagFromPerson(person);
        }
    }

    /**
     * Removes the specified tag from the internal tag list.
     *
     * @param toRemove Tag to be removed from the list.
     */
    public void removeTag(Tag toRemove) {
        requireNonNull(toRemove);

        if (!containsTag(toRemove)) {
            throw new TagNotFoundException();
        }

        Integer numStudents = tagCounter.get(toRemove);
        assert numStudents != null && numStudents > 0;

        if (numStudents == 1) {
            tagCounter.remove(toRemove);
            internalList.remove(toRemove);
        } else {
            int newNumStudents = numStudents - 1;
            tagCounter.put(toRemove, newNumStudents);
        }
    }

    /**
     * Removes the tags belonging to the specified person.
     *
     * @param person Person whose tags have to be removed.
     */
    public void removeTagFromPerson(Person person) {
        person.removeTagsFromTagList(this);
        sortTags();
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     * Tags will be sorted alphabetically.
     *
     * @param tags The Tags to be set.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }
        tagCounter.clear();
        internalList.clear();
        for (Tag tag : tags) {
            addTag(tag);
        }
        sortTags();
    }

    /**
     * Removes the tags from {@code target} and adds the tags from {@code editedPerson} to the tag list.
     * Tags will be sorted alphabetically.
     *
     * @param target Person whose tags are to be removed.
     * @param editedPerson Person whose tags are to be added.
     */
    public void editTagFromPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        target.removeTagsFromTagList(this);
        editedPerson.addTagsToTagList(this);
        sortTags();
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     *
     * @param tags List of tags to check.
     * @return True if the list of tags argument contains only unique tags.
     */
    private boolean tagsAreUnique(List<Tag> tags) {
        for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).equals(tags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the number of students labelled under the specified tag.
     *
     * @param tag Tag to check for number of students labelled under it.
     * @return Number of students labelled under the specified tag.
     */
    public static int getNumStudentsForTag(Tag tag) {
        return tagCounter.get(tag);
    }

    /**
     * Sorts the tag list alphabetically.
     */
    private void sortTags() {
        List<Tag> sortedList = internalList.stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .collect(Collectors.toList());
        internalList.setAll(sortedList);
    }

    /**
     * Returns an unmodifiable distinct tag list.
     *
     * @return An unmodifiable distinct tag observable list.
     */
    public ObservableList<Tag> asUnmodifiableTagList() {
        return internalUnmodifiableList;
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instanceof handles nulls
                        && internalList.equals(((UniqueTagList) other).internalList));
    }
}

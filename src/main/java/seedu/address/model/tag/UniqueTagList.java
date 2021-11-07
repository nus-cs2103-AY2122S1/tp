package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.model.person.Person;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * A list of tags that enforces uniqueness between its elements in terms of tag names, does not allow nulls,
 * and keeps track of the number of persons labelled with each existing tag.
 */
public class UniqueTagList {
    private final ObservableMap<Tag, Integer> tagCounter = FXCollections.observableHashMap();

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
            Integer count = tagCounter.get(toAdd);
            assert count != null && count > 0;
            tagCounter.put(toAdd, count + 1);
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
        tagCounter.clear();
        internalList.clear();
        internalUnmodifiableList.clear();
        for (Person person : persons) {
            addTagFromPerson(person);
        }
    }

    /**
     * Removes the specified tag from the internal tag list.
     * The specified tag should exist in the tag list with positive number of students labelled with it.
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
            int count = numStudents - 1;
            tagCounter.put(toRemove, count);
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
     * Returns the number of students labelled under the specified tag.
     *
     * @param tag Tag to check for number of students labelled under it.
     * @return Number of students labelled under the specified tag.
     */
    public int getNumStudentsForTag(Tag tag) {
        return tagCounter.get(tag);
    }

    /**
     * Sorts the tag list alphabetically.
     */
    private void sortTags() {
        List<Tag> sortedList = internalList.stream()
                .sorted(Comparator.comparing(Tag::toString))
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

    public ObservableMap<Tag, Integer> asUnmodifiableMap() {
        return FXCollections.unmodifiableObservableMap(tagCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalList, tagCounter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instanceof handles nulls
                && internalList.equals(((UniqueTagList) other).internalList)
                && tagCounter.equals(((UniqueTagList) other).tagCounter));
    }
}

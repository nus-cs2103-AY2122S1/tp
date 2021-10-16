package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
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
     * If the tag already exists in the list, replaces the tag with a new tag with
     * the increased number of students labelled under that tag.
     *
     * @param toAdd Tag to be added to the list.
     */
    public void addTag(Tag toAdd) {
        requireNonNull(toAdd);
        if (containsTag(toAdd)) {
            int index = internalList.indexOf(toAdd);
            Tag tag = internalList.get(index);
            int newNumStudents = tag.getNumStudents() + 1;
            internalList.set(index, toAdd.createTagWithNum(newNumStudents));
        } else {
            internalList.add(toAdd);
        }
    }

    /**
     * Adds tags from the specified person to the internal tag list.
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
        assert toRemove.getNumStudents() > 0 : Tag.ASSERTION_ERROR_NON_POSITIVE_DUPLICATES;
        requireNonNull(toRemove);
        int index = internalList.indexOf(toRemove);

        if (index < 0) {
            throw new TagNotFoundException();
        } else if (internalList.get(index).getNumStudents() == 1) {
            internalList.remove(toRemove);
        } else {
            int newNumStudents = internalList.get(index).getNumStudents() - 1;
            internalList.set(index, toRemove.createTagWithNum(newNumStudents));
        }
    }

    /**
     * Removes the tags belonging to the specified person.
     *
     * @param person Person whose tags has to be removed.
     */
    public void removeTagFromPerson(Person person) {
        person.removeTagsFromTagList(this);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     *
     * @param tags The Tags to be set.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }

        internalList.setAll(tags.stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .collect(Collectors.toList()));
    }

    /**
     * Removes the tags from {@code target} and adds the tags from {@code editedPerson} to the tag list.
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
     * Sorts the tag list alphabetically.
     */
    private void sortTags() {
        setTags(internalList.stream().sorted(Comparator.comparing(Tag::getTagName)).collect(Collectors.toList()));
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

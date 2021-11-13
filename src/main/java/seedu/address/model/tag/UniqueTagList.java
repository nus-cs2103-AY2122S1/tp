package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

public class UniqueTagList implements Iterable<Tag> {

    // TODO: fix bug where update event not fired
    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList = FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if a tag with the given tagName exists.
     *
     * @param tagName name of the tag
     * @return true if a tag with the tagName exists
     */
    public boolean hasTagName(String tagName) {
        ObservableList<Tag> tagInQuestion = internalList.filtered(tag -> tag.getName().equals(tagName));
        return !tagInQuestion.isEmpty();
    }

    /**
     * Adds {@code toAdd} to the list.
     *
     * @param toAdd Tag to be added.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }

        internalList.add(toAdd);
    }

    /**
     * Returns true if the list contains {@code toCheck}.
     *
     * @param toCheck Tag to be checked.
     * @return true if the list contains {@code toCheck}.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTag);
    }

    /**
     * Removes the tags based on predicates from the list.
     * The tags must exist in the list.
     */
    public FilteredList<Tag> removeByFields(Predicate<Tag> predicate) {
        requireAllNonNull(predicate);
        FilteredList<Tag> filteredList = internalList.filtered(predicate);
        if (filteredList.size() < 1) {
            throw new TagNotFoundException();
        } else {
            internalList.removeAll(filteredList);
            return filteredList;
        }
    }

    /**
     * Returns the tag with the corresponding {@code tagName}
     */
    public Tag getTag(String tagName) {
        ObservableList<Tag> tagInQuestion = internalList.filtered(tag -> tag.getName().equals(tagName));
        if (tagInQuestion.isEmpty()) {
            throw new TagNotFoundException();
        }
        return tagInQuestion.get(0);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }

        internalList.setAll(tags);
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean tagsAreUnique(List<Tag> tags) {
        for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).isSameTag(tags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
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

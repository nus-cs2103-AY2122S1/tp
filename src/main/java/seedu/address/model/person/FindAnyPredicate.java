package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} OR {@code Tag} matches ANY of the keywords given.
 */
public class FindAnyPredicate implements Predicate<Person> {

    private final List<Name> nameList;
    private final List<Tag> tagList;
    private final boolean isCaseSensitive;

    /**
     * Creates a FindAnyPredicate.
     *
     * @param nameList refers to the list of Names to be searched for.
     * @param tagList refers to the list of Tags to be searched for.
     */
    public FindAnyPredicate(List<Name> nameList, List<Tag> tagList, boolean isCaseSensitive) {
        this.nameList = nameList;
        this.tagList = tagList;
        this.isCaseSensitive = isCaseSensitive;
    }

    /**
     * Tests if {@code person} fulfills the criteria.
     *
     * @param person person that is to be test.
     * @return if person fulfills the criteria.
     */
    @Override
    public boolean test(Person person) {
        Tag[] arrayTags = new Tag[person.getTags().toArray().length];
        if (!nameList.isEmpty()) {
            return nameList.stream()
                    .anyMatch(name -> StringUtil.isContainsWordIgnoreCase(person.getName().fullName, name.fullName))
                            || tagList.stream()
                            .anyMatch(tag -> Arrays.stream(person.getTags().toArray(arrayTags))
                                    .anyMatch(personTag -> personTag.compareTag(tag, isCaseSensitive)));
        } else {
            return tagList.stream()
                            .anyMatch(tag -> Arrays.stream(person.getTags().toArray(arrayTags))
                                    .anyMatch(personTag -> personTag.compareTag(tag, isCaseSensitive)));
        }
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAnyPredicate) // instanceof handles nulls
                && (nameList.equals(((FindAnyPredicate) other).nameList)
                && tagList.equals(((FindAnyPredicate) other).tagList)); // state check
    }
}

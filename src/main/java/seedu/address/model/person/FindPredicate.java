package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Person}'s {@code Name} AND {@code Tag} matches ALL of the keywords given.
 */
public class FindPredicate implements Predicate<Person> {

    private final List<Name> nameList;
    private final List<Tag> tagList;
    private final boolean isCaseSensitive;

    /**
     * Creates a FindPredicate.
     *
     * @param nameList refers to the list of Names to be searched for.
     * @param tagList refers to the list of Tags to be searched for.
     */
    public FindPredicate(List<Name> nameList, List<Tag> tagList, boolean isCaseSensitive) {
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
        return nameList.stream()
                .allMatch(name -> StringUtil.isContainsWordIgnoreCase(person.getName().fullName, name.fullName))
                && tagList.stream()
                .allMatch(tag -> Arrays.stream(person.getTags().toArray(arrayTags))
                        .anyMatch(personTag-> personTag.compareTag(tag, isCaseSensitive)));
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
                || (other instanceof FindPredicate) // instanceof handles nulls
                && (nameList.equals(((FindPredicate) other).nameList)
                && tagList.equals(((FindPredicate) other).tagList)); // state check
    }
}

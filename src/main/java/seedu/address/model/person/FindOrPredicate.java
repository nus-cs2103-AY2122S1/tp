package seedu.address.model.person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} OR {@code Tag} matches ANY of the keywords given.
 */
public class FindOrPredicate implements Predicate<Person> {

    private final List<Name> nameList;
    private final List<Tag> tagList;

    /**
     * Creates a FindOrPredicate
     *
     * @param nameList refers to the list of Names to be searched for
     * @param tagList refers to the list of Tags to be searched for
     */
    public FindOrPredicate(List<Name> nameList, List<Tag> tagList) {
        this.nameList = nameList;
        this.tagList = tagList;
    }

    @Override
    public boolean test(Person person) {
        Tag[] arrayTags = new Tag[person.getTags().toArray().length];
        if (!nameList.isEmpty()) {
            return nameList.stream()
                    .anyMatch(name -> StringUtil.containsWordIgnoreCase(person.getName().fullName, name.fullName))
                            || tagList.stream()
                            .anyMatch(tag -> Arrays.stream(person.getTags().toArray(arrayTags))
                                    .anyMatch(personTag -> personTag.compareTag(tag, false)));
        } else {
            return tagList.stream()
                            .anyMatch(tag -> Arrays.stream(person.getTags().toArray(arrayTags))
                                    .anyMatch(personTag -> personTag.compareTag(tag, false)));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindOrPredicate) // instanceof handles nulls
                && (nameList.equals(((FindOrPredicate) other).nameList)
                && tagList.equals(((FindOrPredicate) other).tagList)); // state check
    }
}

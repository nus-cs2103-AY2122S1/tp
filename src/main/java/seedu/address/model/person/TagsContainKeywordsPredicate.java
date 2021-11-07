package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code List<Tag>} matches any of the keywords given.
 */
public class TagsContainKeywordsPredicate extends AttributeContainsKeywordsPredicate {

    /**
     * Constructor for a TagsContainKeywordsPredicate.
     *
     * @param keywords The {@code List<String>} that contains keywords used to find a person.
     */
    public TagsContainKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return tagsContainWords(person, keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagsContainKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagsContainKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Checks if a person's tags contains the specified keywords.
     *
     * @param person A {@code Person} whose tags might match the keywords.
     * @param keywords A {@code List<String>} that might contain words that match the tags of the person.
     * @throws java.lang.NullPointerException if person or keywords is null
     */
    public boolean tagsContainWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);
        checkArgument(!keywords.stream().allMatch(String::isEmpty));

        Set<Tag> tags = person.getTags();
        for (String keyword: keywords) {
            if (!tagsMatchKeyword(tags, keyword)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a keyword matches any tag in a set of tags.
     *
     * @param tags A {@code Set<Tag>} that might contain a tag which has a name that matches the keyword.
     * @param keyword A {@code String} that might match any tag in the tags given.
     */
    public boolean tagsMatchKeyword(Set<Tag> tags, String keyword) {
        List<String> lowercaseTags = new ArrayList<>();
        for (Tag tag : tags) {
            lowercaseTags.add(tag.tagName.toLowerCase());
        }
        return lowercaseTags.stream().anyMatch(s -> s.startsWith(keyword.toLowerCase()));
    }
}

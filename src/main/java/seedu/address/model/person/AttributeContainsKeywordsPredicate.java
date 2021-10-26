package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AttributeContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final String type;

    /**
     * Constructor for the class.
     * @param keywords keywords to be searched.
     * @param type type to be searched.
     */
    public AttributeContainsKeywordsPredicate(List<String> keywords, String type) {
        this.keywords = keywords;
        this.type = type;
    }

    @Override
    public boolean test(Person person) {
        switch(type) {
        case "T/": return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getTutorialId().value, keyword));
        case "r/": return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getType().value, keyword));
        case "t/": return testTags(person, keywords.get(1));
        default: return testByType(person, type);
        }
    }

    /**
     * Checks if a searched tag is part of the person profile.
     * @param person Person to be checked.
     * @param tag Tag to be searched.
     * @return boolean
     */
    public boolean testTags(Person person, String tag) {
        Set<Tag> tags = person.getTags();
        return tags.contains(new Tag(tag));
    }

    /**
     * Checks if attributes matches or partially matches a name (by prefix) of the person profile.
     * Attributes that allow for partial search are Name, Student ID, NUSNet ID, Github ID, Phone and Address.
     * @param person Person to be checked.
     * @return boolean
     */
    public boolean testByType(Person person, String type) {
        String attributeValue;
        switch (type) {
        case "n/":
            attributeValue = person.getName().fullName;
            break;
        case "s/":
            attributeValue = person.getStudentId().value;
            break;
        case "N/":
            attributeValue = person.getNusNetworkId().value;
            break;
        case "e/":
            attributeValue = person.getEmail().value;
            break;
        case "g/":
            attributeValue = person.getGitHubId().value;
            break;
        case "p/":
            attributeValue = person.getPhone().value;
            break;
        case "a/":
            attributeValue = person.getAddress().value;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(allPrefixes(attributeValue), keyword));
    }

    /**
     * Creates a String of all prefixes for each attribute value, concatenated with a space between each prefix.
     * @param attributeValue Attribute to be used.
     * @return String of all possible prefixes.
     */
    public String allPrefixes(String attributeValue) {
        String[] valueList = attributeValue.split("\\s");
        StringBuilder allKeysValueList = new StringBuilder();
        for (String s : valueList) {
            for (int j = 1; j <= s.length(); j++) {
                allKeysValueList.append(s, 0, j).append(" ");
            }
        }
        return allKeysValueList.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttributeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AttributeContainsKeywordsPredicate) other).keywords)); // state check
    }
}

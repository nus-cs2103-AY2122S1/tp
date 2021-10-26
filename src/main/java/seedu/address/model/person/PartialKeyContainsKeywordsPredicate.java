package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;

public class PartialKeyContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {
    public static final String NAME_TYPE = "n/";
    public static final String STUDENT_ID_TYPE = "s/";
    public static final String NUSNET_ID_TYPE = "N/";
    public static final String EMAIL_TYPE = "e/";
    public static final String GITHUB_ID_TYPE = "g/";
    public static final String PHONE_TYPE = "p/";
    public static final String ADDRESS_TYPE = "a/";
    private final List<String> keywords;
    private final String type;

    /**
     * Constructor for the class.
     *
     * @param keywords keywords to be searched.
     * @param type     type to be searched.
     */
    public PartialKeyContainsKeywordsPredicate(List<String> keywords, String type) {
        super(keywords, type);
        this.keywords = keywords;
        this.type = type;
    }

    /**
     * Checks if attributes matches or partially matches a name (by prefix) of the person profile.
     * Attributes that allow for partial search are Name, Student ID, NUSNet ID, Github ID, Phone and Address.
     * @param person Person to be checked.
     * @return boolean
     */
    public boolean test(Person person) {
        String attributeValue;
        switch (type) {
        case NAME_TYPE:
            attributeValue = person.getName().fullName;
            break;
        case STUDENT_ID_TYPE:
            attributeValue = person.getStudentId().value;
            break;
        case NUSNET_ID_TYPE:
            attributeValue = person.getNusNetworkId().value;
            break;
        case EMAIL_TYPE:
            attributeValue = person.getEmail().value;
            break;
        case GITHUB_ID_TYPE:
            attributeValue = person.getGitHubId().value;
            break;
        case PHONE_TYPE:
            attributeValue = person.getPhone().value;
            break;
        case ADDRESS_TYPE:
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
        assert(valueList.length >= 1);
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
                || (other instanceof PartialKeyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PartialKeyContainsKeywordsPredicate) other).keywords)); // state check
    }
}

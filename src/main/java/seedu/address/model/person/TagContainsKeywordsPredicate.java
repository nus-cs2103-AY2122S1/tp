package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

public class TagContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {
    private final List<String> keywords;
    private final String type;

    /**
     * Constructor for the class.
     *
     * @param keywords keywords to be searched.
     * @param type     type to be searched.
     */
    public TagContainsKeywordsPredicate(List<String> keywords, String type) {
        super(keywords, type);
        this.keywords = keywords;
        this.type = type;
    }

    /**
     * Checks if a searched tag is part of the person profile.
     * @param person Person to be checked.
     * @return boolean
     */
    public boolean test(Person person) {
        Set<Tag> tags = person.getTags();
        List<String> keywordsCopy = new ArrayList<>(keywords);
        Iterator<Tag> values = tags.iterator();
        StringBuilder s = new StringBuilder();
        String key = keywordsCopy.get(0);
        if (key.contains("full/")) {
            keywordsCopy.add(key.substring(5));
            while (values.hasNext()) {
                String nextValue = values.next().toString();
                String nextTag = nextValue.substring(1, nextValue.length() - 1);
                s.append(nextTag).append(" ");
            }
            final String str = s.toString();
            return keywordsCopy.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(str, keyword));
        } else {
            while (values.hasNext()) {
                String nextValue = values.next().toString();
                s.append(allPrefixes(nextValue));
            }
            final String str = s.toString();
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(str, keyword));
        }
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
                allKeysValueList.append(s, 1, j).append(" ");
            }
        }
        return allKeysValueList.toString();
    }
}

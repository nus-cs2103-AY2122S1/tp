package seedu.address.model.person;

import java.util.List;

import seedu.address.commons.util.StringUtil;

public class TutorialIdOrRoleContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {
    public static final String TUTORIAL_ID_TYPE = "T/";
    public static final String ROLE_TYPE = "r/";
    private final List<String> keywords;
    private final String type;

    /**
     * Constructor for the class.
     *
     * @param keywords keywords to be searched.
     * @param type     type to be searched.
     */
    public TutorialIdOrRoleContainsKeywordsPredicate(List<String> keywords, String type) {
        super(keywords, type);
        this.keywords = keywords;
        this.type = type;
    }

    /**
     * Checks if the Tutorial ID or Role matches the person's attributes.
     * @param person Person to be checked.
     * @return boolean
     */
    public boolean test(Person person) {
        if (type.equals(TUTORIAL_ID_TYPE)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getTutorialId().value, keyword));
        } else if (type.equals(ROLE_TYPE)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getType().value, keyword));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialIdOrRoleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TutorialIdOrRoleContainsKeywordsPredicate) other).keywords)); // state check
    }
}

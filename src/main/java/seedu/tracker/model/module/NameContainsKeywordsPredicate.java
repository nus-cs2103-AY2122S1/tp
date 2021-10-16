package seedu.tracker.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.tracker.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code Code} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;
    private final String optionalFilter;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        optionalFilter = keywords.get(0);
    }

    @Override
    public boolean test(Module module) {
        switch (optionalFilter) {

        case "code":
            return (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getCode().value, keyword)));

        case "title":
            return (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTitle().value, keyword)));

        case "description":
            return (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getDescription().value, keyword)));

        case "mc":
            return (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getMc().toString(), keyword)));

        case "tag":
            return (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTags().toString(), keyword)));

        default:
            return (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getCode().value, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTitle().value, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getDescription().value, keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getMc().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTags().toString(), keyword)));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}

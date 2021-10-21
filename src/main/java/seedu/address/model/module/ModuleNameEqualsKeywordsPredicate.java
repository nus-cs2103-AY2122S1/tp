package seedu.address.model.module;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code moduleName} matches any of the keywords given.
 */
public class ModuleNameEqualsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;

    public ModuleNameEqualsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Module module) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getName().getModuleName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleNameEqualsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleNameEqualsKeywordsPredicate) other).keywords)); // state check
    }

}

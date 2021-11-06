package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code ModuleCode}s matches all of the keywords given.
 */
public class ModuleCodesContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ModuleCodesContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .allMatch(keyword -> person.getModuleCodes()
                        .stream().anyMatch(moduleCode ->
                                StringUtil.containsWordIgnoreCase(moduleCode.value, keyword)
                        )
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodesContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleCodesContainsKeywordsPredicate) other).keywords)); // state check
    }
}

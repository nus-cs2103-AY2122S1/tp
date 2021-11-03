package seedu.address.model.module;

import java.util.function.Predicate;

/**
 * Tests that a {@code Module}'s {@code moduleName} matches the keyword given.
 */
public class ModuleNameEqualsKeywordPredicate implements Predicate<Module> {
    private final String keyword;

    public ModuleNameEqualsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Module module) {
        return keyword.equals(module.getName().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleNameEqualsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((ModuleNameEqualsKeywordPredicate) other).keyword)); // state check
    }

}

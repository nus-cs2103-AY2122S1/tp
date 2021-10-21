package seedu.tracker.model.module;

import java.util.function.Predicate;

import seedu.tracker.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code Code} matches any of the keywords given.
 */
public class ModuleByTagNamePredicate implements Predicate<Module> {
    private final String tagName;

    public ModuleByTagNamePredicate(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean test(Module module) {
        return module.getTags().stream().anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.toString(), tagName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleByTagNamePredicate // instanceof handles nulls
                && tagName.equals(((ModuleByTagNamePredicate) other).tagName)); // state check
    }

}


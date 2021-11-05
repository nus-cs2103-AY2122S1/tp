package seedu.tracker.model.module;

import java.util.function.Predicate;

import seedu.tracker.commons.util.StringUtil;

/**
 * Tests that a {@code Module}'s {@code Tag} matches any of the keywords given.
 */
public class ModuleByTagNamePredicate implements Predicate<Module> {
    private final String tagName;

    public ModuleByTagNamePredicate(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean test(Module module) {
        return module.getTags().stream().anyMatch(tag -> StringUtil.containsTagNameIgnoreCase(tag, tagName));
    }
}

